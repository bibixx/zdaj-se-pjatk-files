#include "Struna.h"
#include "inc/world.h"



//-----------------------------------------------------------------------------
// EULER
//-----------------------------------------------------------------------------
// zmienne:
//  float Ro		- liniowa gêstoœæ masy struny (potrzebne przy wyliczeniu Ek)
//  float T0		- si³a naci¹gu (potrzebne do wyliczenia Ep)
//  NPoint		- rozmiar siatki
//  L			- dlugosc struny
//
// 	float Amp[NPoint];  // tablica wychylenia punktow
//	float Vel[NPoint];  // tablica predkosci punktow 
// ZADANIE
//  rozwiazujemy rownanie struny postaci
//  d^2 y(x,t)/dt^2 = v^2(x) d^2 y(x,t)/dx^{2} (gdzie d to pochodna czastkowa)
//  y(x,t) wychylenie struny w punkcie x w chwili t
//  v(x) - predkosc rozchodzenia siê fali poprzecznych 
//			(przyjeta wartoœæ 1 dla uproszczenia obliczen)
// zmienne pomocnicze
// d2y - tablica pomocnicza do aproksymacji drugiej pochodnej
//  d^2y/dx^2 = (y(x-1,t)+y(x+1,t)-2y(x,t))/dx^2 (przyspieszenie)
//-----------------------------------------------------------------------------
void Struna::solveEuler(float dt){
	float d2y[NPoint];
	float dx=L/((float)NPoint-1.);
	float dxdx=L/(float)(NPoint-1)*L/(float)(NPoint-1);
	
	d2y[0]=0;
	d2y[NPoint-1]=0;
	Ep=Ek=0.;
	for(int i=1;i<(NPoint-1);i++)
		d2y[i] = (Amp[i+1] + Amp[i-1] - 2 * Amp[i]) / dxdx;
	Amp[0]=0.;
	Amp[NPoint-1]=0.;
	int i;
	for(i=1;i<NPoint;i++)
	{
		Vel[i] = Vel[i] + d2y[i]*dt;
		Amp[i] = Amp[i] + Vel[i]*dt;
		
		Ek += dx * Ro * Vel[i] * Vel[i] / 2;
		Ep += T0*((Amp[i]-Amp[i-1])*(Amp[i]-Amp[i-1]))/(2*dx);
	}
}
//--------------------------------------------------------------------------------






// RESZTA KODU
//---------------------------------------------------------------
Struna::Struna():SceneObject(){
	solver=1;
	// dodatkowe rzeczy dotyczace efektu motion blur oraz wykresu
	counter=0;
	motion_blur=0;
	
	c1 = gluNewQuadric();
	
	gr = new Graph2D(50,50,300,300);
	gr->setMaxLength(100,0.003);
	gr->addDynamicSerie();
	gr->addDynamicSerie();
	gr->addDynamicSerie();	 

	this->g = 9.81;
	float x=10.;
	for (int i=0;i<NPoint;i++, x+=(20.)/(NPoint-1.))
	{		
		Amp[i]=0;
	}
	for (int i=0;i<10;i++)	
	{
		A[i]=0.;
		Fi[i]=0.;
	}	
	A[0]=2.;
	t=0;		// czas
	L=20.;		// d³ugoœæ struny
	V=1;		//prêdkoœæ rozchodz
	Ro=1;
	T0=1;
	dt=0.001;
	
	CWorld::helpInfo->addHelp("Struna warunki poczatkowe");
	CWorld::helpInfo->addHelp("  t - trojkat gora");
	CWorld::helpInfo->addHelp("  T - trojkat lewo");
	CWorld::helpInfo->addHelp("  i - impuls - srodek");
	CWorld::helpInfo->addHelp("  I - impuls - na poczatku");
	CWorld::helpInfo->addHelp("  f - polokres");
	CWorld::helpInfo->addHelp("  F - polokres");
	CWorld::helpInfo->addHelp("< - zmniejsz dt o polowe");
	CWorld::helpInfo->addHelp("> - zwieksz dt razy dwa");
	
	
}


// rysowanie
void Struna::draw(){
	glPushMatrix();
	glDisable(GL_TEXTURE_2D);
	glDisable(GL_BLEND);

	if (!shadow){		
		glColor4f(0,0,1,0.5);
	}
		glLineWidth(4);
		glEnable(GL_LINE_SMOOTH);
		float x=-10;
		glBegin(GL_LINES);
			for (int i=0;i<NPoint-1;i++, x+=(20.)/(NPoint-1.))
			{		
						glVertex3f(x,Amp[i]*2+3,0);
						glVertex3f(x+(20.)/(NPoint-1.),Amp[i+1]*2+3,0);
			}	
		glEnd();		
	//}
	glPopMatrix();
	
	return ;
	
}
void Struna::solveAnalitic(float dt){
	
	// dane wejœciowe
	// t - czas
	// A[n] amplitudan n-tej sk³adowej
	//Amp[n] - amplituda chwilowa w punkcie n )0 .. NPoint-1 
	// L - d³ugoœæ struny
	// V Prêdkoœæ rozchodzenia siê
	// Fi[n] przesuniêcie fazowe sk³adowej n
	//
	float x=0.;
	float dx=L/((float)NPoint-1.);
	Ep=Ek=0.;
	for (int i=0;i<NPoint;i++, x+=dx)
	{		
		float a=0;
		for (int n=0;n<10;n++)
		{
			a+=A[n]*sin((n+1)*M_PI/L*x)*cos((n+1)*M_PI/L*V*t + Fi[n]) ;
		}
		Vel[i]=(a-Amp[i])/dt;
		Amp[i]=a;
		Ek+= dx * Ro * Vel[i]*Vel[i] /2.;
		if(i>0) 
			Ep+=T0*(Amp[i]-Amp[i-1])*(Amp[i]-Amp[i-1])/(2*dx);
	}
	
}

void Struna::doStep(){
	
	for(int i=0;i<20;i++)
	{
		t+=dt;
		switch (solver){
			case 0: 
				solveAnalitic(dt);				// wywolanie solvera
				
				break;
			case 1:
				solveEuler(dt);
				break;
			default:
				solveAnalitic(dt);
		}
	}
	float Ec=Ep+Ek;
	gr->addNextPoint(Ep,0);
	gr->addNextPoint(Ek,1);
	gr->addNextPoint(Ec,2);	
	
}




void Struna::keyboard(int key){
	
	if ((key>='0' && key<='9') || key=='q'){
		int i;
		
		//(n+1)*M_PI/L*V*t
		
		if ( key=='q' )
			i=1;
		else
			i=key-'0';
		
		if ( A[i] > 0 )
			A[i]=0.;
			
		else{
			A[i]=2.;
			Fi[i]=-(i+1)*M_PI/L*V*t+M_PI/2;
		}		
	}
	
	
	if (key=='t'){ // warunki pocz - trójk¹t
			for (int i=0;i<NPoint/2;i++)
			{
					Amp[i]=Amp[NPoint-i-1]=2.0 * (float)i/(NPoint/2.0);
					Vel[i]=Vel[NPoint-i-1]=0.0;	
			}
	}
	if (key=='T'){ // warunki pocz - trójk¹t
		int i;
			for (i=0;i<NPoint/4;i++)
			{
					Amp[i]=2.0 * (float)i/(NPoint/4.0);
					Vel[i]=0.0;	
			}
			for (;i<NPoint-1;i++)
			{
					Amp[i]=2.0 * (float)(NPoint-1.-i)/(3*NPoint/4.0);
					Vel[i]=0.0;	
			}
	}
	if (key=='i'){ // warunki impuls - œrodek

			for (int i=0;i<NPoint;i++)
								Amp[i]=Vel[i]=0.0;	
			Amp[NPoint/2]=2.;
			Amp[NPoint/2-1]=2.;
			Amp[NPoint/2+1]=2.;
	}
if (key=='I'){ // warunki impuls - na pocz¹tku
			for (int i=0;i<NPoint;i++)
								Amp[i]=Vel[i]=0.0;	
			Amp[10]=2.;
			Amp[10-1]=2.;
			Amp[10+1]=2.;
	}
	if (key=='f'){ // warunki pocz pó³okres 
		float x=0,a=3.;
		float dx=L/((float)NPoint-1.);
		for (int i=0;i<NPoint;i++, x+=dx)
		{		
			int n=9;
			Amp[i]=a*sin((n+1)*M_PI/L*x)*cos(0.) ;
			Vel[i]=0;
			if ((n+1)/L*x>1.) 
			{
					a=0;
					Amp[i]=0;
			}
		}
	}
	if (key=='F'){ // warunki pocz pó³okres 
		float x=0,a=3.;
		float dx=L/((float)NPoint-1.);
		for (int i=0;i<NPoint;i++, x+=dx)
		{		
			int n=9;
			Amp[i]=a*sin((n+1)*M_PI/L*x)*cos(0.) ;
			Vel[i]=0;
			if ((n+1)/L*x>2.) 
			{
					a=0;
					Amp[i]=0;
			}
		}
	}

	if (key=='a'){
		solver=0;
	}
	if (key=='e'){
		solver=1;
	}
	if (key=='c'){
		solver=2;
	}
	if (key=='v')
		drawvector=!drawvector;
	if (key=='>'){
		dt=dt*2;
		if (dt >1)
				dt=1;
	}
	if (key=='<'){
		dt=dt/2;
		if (dt < 0.001)
				dt=0.001;
	}
	
}


