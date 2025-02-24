#include "UkladPlanet.h"
#include "sun.h"
#include "moon.h"
#include "math.h"
double ff=0.0;
double factor=1495978.87;

//to jest sta³a grawitacji, czytaj: http://pl.wikipedia.org/wiki/Sta%C5%82a_grawitacji
long double GG=6.6742e-11;	

//----------------------------------------------------------------------------------
// kod do napisania
//----------------------------------------------------------------------------------
// metoda
//   calcAcceleration()
//		wyznacza wypadkowe przyspieszenie dzialajace na planete 
//		na zasadzie superpozycji
//		planety znajduja sie w skladowej planets
//      sposoby dostepu do koljnych planet:
//      1. planets[0]  planets[1] itd
//		   planets.size() - zwraca informacje o ilosci planet
//      2. np petla for (planetsIterator iter1=planets.begin(); iter1!=planets.end(); iter1++)
//         (*iter) zawiera aktualny obiekt typu Planet
//         odwolanie do skladowych np (*iter)->pos
//
//  klasa planeta zawiera nastepujace skladowe z ktorych trzeba skorzystac:
//   Vector3d pos  - polozenie planety w ukladzie 3d
//   Vector3d v    - predkosc
//   double   mass - masa planety
//   Vector3d a    - przyspieszeie 
//   zamiast wyliczac sile wypadkowa F=ma F=(G*m1*m2)/(r*r)
//   a=F/m -> a = G*m1/(r*r)
//
//   planets[0] - slonce znajduje sie w srodku polozenie (0,0,0)
//   czyli dlugosc wektora pos jest odlegloscia planety od slonca
//
//   metoda calcAcceleration powinna wyznaczyc przyspieszenie wypadkowe planety
//   wynik zapisujac w skladowej a
//
//---------------------------------------------------------------------------------
void UkladPlanet::calcAcceleration(){
	ZeroAcceleration();

	//##############################################################################################################
	//F=(G*m1*m2)/(r*r)
	//a = G*m1/(r*r)
	//dla ka¿dej planety
	for(int i = 0; i < planets.size(); i++)
	{
		Vector3d sumaPrzyspieszen = 0;
		for(int j = 0; j < planets.size(); j++)
		{			
			Vector3d roznicaWektorow; Vector3d przyspieszenie;
			//oprócz samej siebie
			if(i != j)
			{				
				roznicaWektorow = planets[j]->pos - planets[i]->pos;
				double r = roznicaWektorow.len();
				//i wyliczamy przyspieszenie. Dziêki drugiemu nawiasowi powstaje wektor				
				przyspieszenie = ((GG*planets[j]->mass)/pow(r,2))*(roznicaWektorow/r);
			}
			//suma sk³adowa przyspieszeñ (superpozycja) dla ka¿dej planety
			sumaPrzyspieszen = sumaPrzyspieszen + przyspieszenie;
		}
		//zapamiêtanie przyspieszenia w klasie Planety
		planets[i]->a = sumaPrzyspieszen;
	}
	//##############################################################################################################
}

//-------------------------------------------------
// rozwiazanie rownania ruchu metoda Eulera
// pierwszy krok wyliczenie przyspieszenia
// potem z rownania ruchu v oraz pos
//-------------------------------------------------
void UkladPlanet::solveEuler(){
	double dt = 0.001;//0.1/factor;
	
	calcAcceleration();
	//##############################################################################################################
	//i = 1, bo nie interesuje nas zmiana po³o¿enia S³oñca
	for(int i = 1; i < planets.size(); i++)
	{
		//metoda Eulera
		planets[i]->v=planets[i]->v+planets[i]->a*dt;		
		planets[i]->pos=planets[i]->pos+planets[i]->v*dt;				
	}
	//##############################################################################################################
}

//===========================================================================
//                                 RRESZTA KODU
//===========================================================================
void UkladPlanet::ZeroAcceleration(){
	for (planetsIterator iter=planets.begin(); iter!=planets.end(); iter++){
		(*iter)->a=Vector3d(0.0,0.0,0.0);
	}
}
void UkladPlanet::setPlanetsVelocity(){
	// slonce
	planets[0]->v=Vector3d(0.0,0.0,0.0);
	
	// ziemia
	Vector3d rr=planets[1]->pos-planets[0]->pos;
	double d=rr.len();
	double vsy = sqrt( (GG*planets[0]->mass)/d );
	//vsy=vsy*1.05;
	planets[1]->v=Vector3d(0.0,-vsy,0.0);
	
	//std::cout << std::endl;
	//std::cout << vsy  << std::endl;	
	
	
	rr=planets[2]->pos-planets[0]->pos;
	d=rr.len();
	

	
	//vsy = sqrt( (GG*planets[0]->mass)/d );
	
	rr=planets[2]->pos-planets[1]->pos;
	d=rr.len();
	
	//std::cout << std::endl;
	//std::cout << d  << std::endl;	
	
	double vky=sqrt( (GG*planets[1]->mass)/(d) );
	
	
	//planets[2]->v=Vector3d(0.0,-vsy*1.038011,0.0);
	//planets[2]->v=Vector3d(0.0,-vsy-vky*sin((3.14/180.0)*85.0),-vky*cos((3.14/180.0)*85.0));
	planets[2]->v=Vector3d(0.0,(-vsy-vky*sin((3.14/180.0)*95.0)*1.0825),-vky*cos((3.14/180.0)*95.0));
	//planets[2]->v=Vector3d(0.0,-vsy*1.0,0.0); 1.034011
		//*1.246

	rr=planets[3]->pos-planets[0]->pos;
	d=rr.len();
	
	vsy = sqrt( (GG*planets[0]->mass)/d );
	planets[3]->v=Vector3d(0.0,-vsy,0.0);
	
	
	rr=planets[4]->pos-planets[0]->pos;
	d=rr.len();
	
	vsy = sqrt( (GG*planets[0]->mass)/d );
	planets[4]->v=Vector3d(0.0,-vsy,0.0);	

	rr=planets[5]->pos-planets[0]->pos;
	d=rr.len();
	
	vsy = sqrt( (GG*planets[0]->mass)/d );
	planets[5]->v=Vector3d(0.0,-vsy,0.0);		
	
	

	rr=planets[6]->pos-planets[0]->pos;
	d=rr.len();
	
	vsy = sqrt( (GG*planets[0]->mass)/d );
	planets[6]->v=Vector3d(0.0,-vsy*1.016,0.0);		
	
	
	rr=planets[7]->pos-planets[0]->pos;
	d=rr.len();
	
	vsy = sqrt( (GG*planets[0]->mass)/d );
	planets[7]->v=Vector3d(0.0,-vsy*1.0170,0.0);			
	
/*
	rr=planets[6]->pos-planets[0]->pos;
	d=rr.len();
	
	vsy = sqrt( (GG*planets[0]->mass)/d );
	planets[6]->v=Vector3d(0.0,-vsy,0.0);		
*/
}

void UkladPlanet::keyboard(int key){
	if (key=='t'){
		rysujTrajektorie=!rysujTrajektorie;
		idx = 0;
		idx1 = 0;
		idx2=0;
		idx3=0;
		idx4=0;
		idx5=0;
		idx6=0;
		idx7=0;
		lis=0;
	}
	if (key=='z'){
		lis=!lis;
		rysujTrajektorie=0;
		idx = 0;
		idx1 = 0;
		idx2=0;
		idx3=0;
		idx4=0;
		idx5=0;		
		idx6=0;
		idx7=0;
	}
	if (key=='0')
		camidx=0;
	if (key=='1')
		camidx=1;
	if (key=='4'){
		camidx=3;
	}
	if (key=='5'){
		camidx=4;
	}
	if (key=='6'){
		camidx=5;
	}	
	
	
}

void UkladPlanet::addPlanet(Planeta *p){
	planets.push_back(p);
}

void UkladPlanet::draw(){

	for (planetsIterator iter=planets.begin(); iter!=planets.end(); iter++){
		(*iter)->draw();
	}
	if (rysujTrajektorie){
		glDisable(GL_LIGHTING);	
		glDisable(GL_LIGHT0);
		glDisable(GL_TEXTURE_2D);
		glEnable(GL_COLOR_MATERIAL);
		glEnable(GL_LINE_SMOOTH);	
		glPushMatrix();	
			glEnable(GL_BLEND);
			glLineWidth(1);
			glRotatef(90,1,0,0);			
			glColor4f(1,1,0,0.8);
			glBegin(GL_LINES);
			for (int i=1; i<idx; i++){
				glVertex2f(trackPos[i-1].x,trackPos[i-1].y);
				glVertex2f(trackPos[i].x,trackPos[i].y);
			}
			glEnd();
			glColor4f(1,0,1,0.8);
			glBegin(GL_LINES);
			for (int i=1; i<idx1; i++){
				glVertex3f(trackPos1[i-1].x,trackPos1[i-1].y,trackPos1[i-1].z);
				glVertex3f(trackPos1[i].x,trackPos1[i].y,trackPos1[i].z);
			}
			glEnd();	

			glColor4f(0,1,0,0.8);
			glBegin(GL_LINES);
			for (int i=1; i<idx2; i++){
				// CIEKAWE !!
				//glVertex3f(trackPos2[i-1].x,trackPos1[i-1].y,trackPos2[i-1].z);
				//glVertex3f(trackPos2[i].x,trackPos1[i].y,trackPos2[i].z);
				glVertex3f(trackPos2[i-1].x,trackPos2[i-1].y,trackPos2[i-1].z);
				glVertex3f(trackPos2[i].x,trackPos2[i].y,trackPos2[i].z);
				
			}
			glEnd();	

			glColor4f(1,0,0,0.8);
			glBegin(GL_LINES);
			for (int i=1; i<idx3; i++){
				glVertex3f(trackPos3[i-1].x,trackPos3[i-1].y,trackPos3[i-1].z);
				glVertex3f(trackPos3[i].x,trackPos3[i].y,trackPos3[i].z);
				
			}
			glEnd();				

			glColor4f(0,1,1,0.8);
			glBegin(GL_LINES);
			for (int i=1; i<idx4; i++){
				glVertex3f(trackPos4[i-1].x,trackPos4[i-1].y,trackPos4[i-1].z);
				glVertex3f(trackPos4[i].x,trackPos4[i].y,trackPos4[i].z);
				
			}
			glEnd();				

			
			glColor4f(1,1,1,0.8);
			Vector3d p1,p2;
			glBegin(GL_LINES);
			for (int i=1; i<idx5; i++){
				//glVertex3f(trackPos5[i-1].x,trackPos5[i-1].y,trackPos5[i-1].z);
				//glVertex3f(trackPos5[i].x,trackPos5[i].y,trackPos5[i].z);
				
				
				p1= trackPos5[i-1]-trackPos1[i-1];
				p2= trackPos5[i]-trackPos1[i];
				
				p1=planets[1]->pos*(1.0/factor)+p1;
				p2=planets[1]->pos*(1.0/factor)+p2;
				
				//std::cout << p1 << std::endl;
				glVertex3f(p1.x,p1.y,p1.z);
				glVertex3f(p2.x,p2.y, p2.z);

				
			}
			glEnd();			


			glBegin(GL_LINES);
			for (int i=1; i<idx6; i++){
				//glVertex3f(trackPos5[i-1].x,trackPos5[i-1].y,trackPos5[i-1].z);
				//glVertex3f(trackPos5[i].x,trackPos5[i].y,trackPos5[i].z);
				
				
				p1= trackPos6[i-1]-trackPos3[i-1];
				p2= trackPos6[i]-trackPos3[i];
				
				p1=planets[4]->pos*(1.0/factor)+p1;
				p2=planets[4]->pos*(1.0/factor)+p2;
				
				//std::cout << p1 << std::endl;
				glVertex3f(p1.x,p1.y,p1.z);
				glVertex3f(p2.x,p2.y, p2.z);

				
			}
			glEnd();			
			
			glColor4f(0.5,1,0.5,0.8);
			glBegin(GL_LINES);
			for (int i=1; i<idx7; i++){
				//glVertex3f(trackPos5[i-1].x,trackPos5[i-1].y,trackPos5[i-1].z);
				//glVertex3f(trackPos5[i].x,trackPos5[i].y,trackPos5[i].z);
				
				
				p1= trackPos7[i-1]-trackPos3[i-1];
				p2= trackPos7[i]-trackPos3[i];
				
				p1=planets[4]->pos*(1.0/factor)+p1;
				p2=planets[4]->pos*(1.0/factor)+p2;
				
				//std::cout << p1 << std::endl;
				glVertex3f(p1.x,p1.y,p1.z);
				glVertex3f(p2.x,p2.y, p2.z);

				
			}
			glEnd();			
			
			
			
		glPopMatrix();
	}
	
	if (lis){
		glDisable(GL_LIGHTING);	
		glDisable(GL_LIGHT0);
		glDisable(GL_TEXTURE_2D);
		glEnable(GL_COLOR_MATERIAL);
		glEnable(GL_LINE_SMOOTH);	
		glPushMatrix();	
			glEnable(GL_BLEND);
			glLineWidth(1);
			glRotatef(90,1,0,0);			
			glColor4f(1,1,0,0.8);
			glBegin(GL_LINES);
			for (int i=1; i<idx; i++){
				glVertex2f(trackPos2[i-1].x,trackPos1[i-1].y);
				glVertex2f(trackPos2[i].x,trackPos1[i].y);
			}
			glEnd();
		glPopMatrix();
	}			
}

void UkladPlanet::doStep(){
	for (int i=0; i<500; i++){
		solveEuler();
	}

	if (camidx!=0){
		camPos=planets[camidx]->pos*(1.0/factor);
		camPos.z=camPos.y;	
		camPos.y=140.0;
	}
	
	if (rysujTrajektorie || lis){
		Vector3d rr=planets[2]->pos-planets[1]->pos;
		rr.norm();
		rr=rr*1.4;
		trackPos[idx]=planets[2]->pos*(1.0/factor)+rr;
		idx++;
		if (idx>=3999) idx=0;

		trackPos1[idx1]=planets[1]->pos*(1.0/factor);
		idx1++;
		if (idx1>=3999) idx1=0;	
		
		trackPos2[idx2]=planets[3]->pos*(1.0/factor);
		idx2++;
		if (idx2>=3999) idx2=0;	
		
		trackPos3[idx3]=planets[4]->pos*(1.0/factor);
		idx3++;
		if (idx3>=3999) idx3=0;		

		trackPos4[idx4]=planets[5]->pos*(1.0/factor);
		idx4++;
		if (idx4>=3999) idx4=0;		
		
		rr=planets[2]->pos-planets[1]->pos;
		rr.norm();
		rr=rr*15.0;
		
		trackPos5[idx5]=planets[2]->pos*(1.0/factor)+rr;
		idx5++;
		if (idx5>=3999) idx5=0;		
		
		//------------------------------------------
		rr=planets[6]->pos-planets[4]->pos;
		rr.norm();
		rr=rr*6.0;
		
		trackPos6[idx6]=planets[6]->pos*(1.0/factor)+rr;
		idx6++;
		if (idx6>=3999) idx6=0;			

		rr=planets[7]->pos-planets[4]->pos;
		rr.norm();
		rr=rr*10.0;
		
		trackPos7[idx7]=planets[6]->pos*(1.0/factor)+rr;
		idx7++;
		if (idx7>=3999) idx7=0;			

		
	}
	
}

UkladPlanet::UkladPlanet():SceneObject(){
	//  1.0e12 1.0e11 1.0e4
	addPlanet(new Sun(Vector3d(  0.0,0.0,0.0), 1.9891e30, 20.0, "dat/u2.bmp")); // slonce
	addPlanet(new Planeta(Vector3d(100.0*factor,0.0,0.0), 5.9736e24,  9.0, "dat/earthc.bmp")); // ziemia
	addPlanet(new Moon(Vector3d((100.0+100*0.002338)*factor,0.0,0.0), 7.347673e22,   3.0, "dat/moon.bmp",planets[1]));  // ksie¿yc
//3101
	addPlanet(new Planeta(Vector3d(100.0*0.7233*factor,0.0,0.0), 4.8685e24,   8.5, "dat/venus.bmp")); 
	addPlanet(new Planeta(Vector3d(100.0*1.5237*factor,0.0,0.0), 0.6419e24,   4.5, "dat/mars.bmp")); 
	addPlanet(new Planeta(Vector3d(100.0*0.3871*factor,0.0,0.0), 0.3302e24,   4.0, "dat/mercury.bmp")); 
	
	addPlanet(new Moon(Vector3d(100.0*1.5237*factor+100.0*0.002*factor,0.0,0.0), 0.6419e1,   1, "dat/moon.bmp",planets[4],6.0)); 
	addPlanet(new Moon(Vector3d(100.0*1.5237*factor+100.0*0.0022*factor,0.0,0.0), 0.6419e1,   1, "dat/moon.bmp",planets[4],10.0)); 
	
	//addPlanet(new Planeta(Vector3d(100.0*4.2034*factor,0.0,0.0), 1.8987e27 ,   15, "dat/jupiter.bmp")); 
	
	//addPlanet(new Sun(Vector3d(  0.0,0.0,0.0), 1.0e12, 27.0, "u2.bmp")); // slonce
	//addPlanet(new Planeta(Vector3d(120.0*factor,0.0,0.0), 1.0e12,  9.0, "earth.bmp")); // ziemia
	//addPlanet(new Planeta(Vector3d(135.0*factor,0.0,0.0), 1.0e9,   4.0, "moon.bmp",1));  // ksie¿yc

	
	//addPlanet(new Planeta(Vector3d(55.0/factor,0.0,0.0), 1.0e4,   6.0, "venus.bmp")); 
	
	//addPlanet(new Planeta(Vector3d(-160.0,0.0,0.0), 1.0e11,   4.0, "moon.bmp"));
	setPlanetsVelocity();
	idx=0;
	idx1=0;
	idx2=0;
	idx3=0;
	idx4=0;
	idx5=0;
	idx6=0;
	idx7=0;
	camidx=0;
	rysujTrajektorie=false;
	lis=false;
	CWorld::helpInfo->addHelp("t - trajektorie (on/off)");
	CWorld::helpInfo->addHelp("Lissajous:");
	CWorld::helpInfo->addHelp(" z - Ziemia/Mars");
	CWorld::helpInfo->addHelp("Kamera:");
	CWorld::helpInfo->addHelp(" 0 - uklad");
	CWorld::helpInfo->addHelp(" 1 - widok z gory/ziemia");
	CWorld::helpInfo->addHelp("     4 - Wenus");
	CWorld::helpInfo->addHelp("     5 - Mars");
	CWorld::helpInfo->addHelp("     6 - Merkury");
	solver  = 0;
}




void UkladPlanet::drawControl(int id, int w, int h){
	double kx,ky;
	double f;
	Vector3d fw;
	Vector3d k;
	Vector3d r21;
	double d;
	int ii=0;
	int jj=15;
glEnable(GL_BLEND);
	glPointSize(4);
	glBegin(GL_POINTS);
	for(kx=-150;kx<150;kx+=4){
		for(ky=-150;ky<150;ky+=4)
		{
			f=0.0f;
			fw=Vector3d(0,0,0);
			k=Vector3d(kx,ky,0);

			#define GP 0.06
  				r21 = planets[0]->pos*(1.0/factor) - k;
				d = r21.len();
				r21.norm();
				if(d!=0)
				fw=fw - r21 * GP *(60000.0/(d*d));


  				r21 = planets[1]->pos*(1.0/factor) - k;
				d = r21.len();
				r21.norm();
				if(d!=0)
				fw=fw - r21 * GP *(3000.0/(d*d));
				

				Vector3d rr=planets[2]->pos-planets[1]->pos;
				rr.norm();
				rr=rr*15.0;
				
				
  				r21 = planets[2]->pos*(1.0/factor)+rr - k;
				d = r21.len();
				r21.norm();
				if(d!=0)
				fw=fw - r21 * GP *(300.0/(d*d));

  				r21 = planets[3]->pos*(1.0/factor)+ff - k;
				d = r21.len();
				r21.norm();
				if(d!=0)
					fw=fw - r21 * GP *(2500.0/(d*d));
				
  				r21 = planets[4]->pos*(1.0/factor)+ff - k;
				d = r21.len();
				r21.norm();
				if(d!=0)
					fw=fw - r21 * GP *(1200.0/(d*d));				
				
  				r21 = planets[5]->pos*(1.0/factor)+ff - k;
				d = r21.len();
				r21.norm();
				if(d!=0)
					fw=fw - r21 * GP *(600.0/(d*d));				
				
				
			f = fw.len();
			
			//glColor4f(f*0.5,f*0.4,f*0.4,0.7);
			double r,g,b;
			//std::cout << f << std::endl;
			//f=f*0.8;
			/*
			if (f<=1){
				r=f;
				g=0;
				b=0;
			}
			else if(f<=2){
				r=1;
				g=f-1;
				b=0;
			}
			else{
				r=1;
				g=1;
				b=f-2;
			}
			*/
			r=f;
			//r=r-1.0;
			
			if (r>1)
				g=r-1;
			else
				g=0;
			if (g>1)
				b=g-1;
			else
				b=0;
			//glColor3f(r,g,b);
			
			//setColor1(r);
			f=f;
			glColor4f(r,g,b,0.6);
				r=f;
				g=f-1.5;
				b=f-2.5;
			glColor4f(r,g,b,0.5);
			glVertex3f(1+ii*4,jj*4-28-14,0);
			jj++;			 
			//printf("%f\n",f);

		}
		jj=15;
		ii++;
	}


	glEnd();
//printf("%f\n",);
//std::cout << pos2;	
}

void UkladPlanet::armagedon(){
	addPlanet(new Planeta(Vector3d(0.0,-150.0,0.0), 2.0e11,   7.0, "venus.bmp")); 
	planets[planets.size()-1]->v=Vector3d(-2.2,0.01,0);
}