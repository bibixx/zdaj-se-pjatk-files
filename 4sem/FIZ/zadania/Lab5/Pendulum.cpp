#include "Pendulum.h"
#include "soleng.h"

#define START_ANGLE 45.0f
#define PI 3.141592653589793238462643383279 
double met_min=1000.0;
double met_max=0.0;

double last=0.0;

extern Vector3d camPos;
//-------------------------------------------------------------------------
// w klasie Pendulum dostepna jest skladowa g (przyspieszenie grawitacyjne)
//-------------------------------------------------------------------------
// KOD DO NAPISANIA JAKO ZADANIE
//--------------------------------------------------------------
//
//  solvery
//		solveEuler
//		solveMidPoint
//		solveRK4
//	energia
//		calcPoptencialEnergy();
//		calcKineticEnergy();
//
//	omega  - predkosc katowa)
//  alpha  - aktualny kont wychylenia) 
//  radius - dlugosc linki
//       g - przyspieszenie ziemskie
//--------------------------------------------------------------

//--------------------------------------------------------------
// EULER
//--------------------------------------------------------------
void Pendulum::solveEuler(double dt){
	// przyspieszenie grawitacyjne this->g	
	alpha = alpha + omega * dt;	//[0]
	omega= omega + (g/radius)*sin(alpha)*dt; //[1]
				 
}
//--------------------------------------------------------------

//--------------------------------------------------------------
// MidPoint ///////////////////////////////////////////////////
//--------------------------------------------------------------
void Pendulum::derivatives(double* in, double* out){
	// przyspieszenie grawitacyjne this->g
	// ten kod tez mozna wyciac i dac do napisania
	// ewentualnie zostawic
	out[0]=in[1]; 					// d alpha/dt = omega
	out[1]=(g/radius)*sin(in[0]);	// d2 alpha/ dt2 = (g/r)*sin(alpha)
}
void Pendulum::solveMidPoint(double dt){
	// przyspieszenie grawitacyjne this->g
	// dopisac kod obliczeniowy
	double start[2],k1[2],k2[2],t1[2];
	
	
	start[0] = alpha;
	start[1] = omega;
	
	derivatives(start,k1);
	
	t1[0] = start[0]+k1[0]*dt/2.0;
	t1[1] = start[1]+k1[1]*dt/2.0;
	
	derivatives(t1,k2);
	
	omega = start[1]+k2[1]*dt;
	alpha = start[0]+k2[0]*dt; 
}
//--------------------------------------------------------------


//--------------------------------------------------------------
// RK4 ////////////////////////////////////////////////////////
//--------------------------------------------------------------
void Pendulum::solveRK4(double dt){
	// przyspieszenie grawitacyjne this->g
	// dopisac kod obliczeniowy

	// przyspieszenie grawitacyjne this->g
	// dopisac kod obliczeniowy
	double start[2],k1[2],k2[2],k3[2],k4[2],t1[2],t2[2],t3[2];
	
	
	start[0] = alpha;
	start[1] = omega;
	
	derivatives(start,k1);
	
	t1[0] = start[0]+k1[0]*dt/2.0;
	t1[1] = start[1]+k1[1]*dt/2.0;
	
	derivatives(t1,k2);
	
	t2[0] = start[0]+k2[0]*dt/2.0;
	t2[1] = start[1]+k2[1]*dt/2.0;
	
	derivatives(t2,k3);
	
	t3[0] = start[0]+k3[0]*dt;
	t3[1] = start[1]+k3[1]*dt;
	
	derivatives(t3,k4);
	
	omega = start[1]+dt/6.0*(k1[1]+2*k2[1]+2*k3[1]+k4[1]);
	alpha = start[0]+dt/6.0*(k1[0]+2*k2[0]+2*k3[0]+k4[0]);
	
	
}
//--------------------------------------------------------------

double Pendulum::calcPoptencialEnergy(){
	// przyspieszenie grawitacyjne this->g

	double h=radius+r.y+0.5;
	// dopisac kod	
	return mass*g*h;
}


double Pendulum::calcKineticEnergy(){
	// dopisac kod
	return mass*(omega*omega*radius*radius)/2.0;
}
//---------------------------------------------------------------







//---------------------------------------------------------------
// RESZTA KODU
//---------------------------------------------------------------
Pendulum::Pendulum():SceneObject(){
	//double xx=2.0*acos(0)/180.0;		// PI/180
	double xx=PI/180.0;
	radius = 6.0;					// dlugosc linki
	omega=0.0;						// startowa predkosc katowa
	alpha=(180.0-START_ANGLE)*xx;	// kat wychylenia
	mass = 1.5f;					// masa punktu
	solver=0;
	// dodatkowe rzeczy dotyczace efektu motion blur oraz wykresu
	counter=0;
	motion_blur=0;
	
	CWorld::getWorld()->helpInfo->addHelp("m - motion blur (on/off)");
	CWorld::getWorld()->helpInfo->addHelp("v - wektor Vstyczna (on/off)");
	c1 = gluNewQuadric();
	
	gr = new Graph2D(50,50,300,300);
	gr->setMaxLength(100,0.05/10.0);
	gr->addDynamicSerie();
	gr->addDynamicSerie();
	gr->addDynamicSerie();	
	drawvector=0;
	this->g = 9.81;
}


// rysowanie
void Pendulum::draw(){

	glPushMatrix();
	
		glDisable(GL_TEXTURE_2D);
		if (!shadow){		
			glDisable(GL_BLEND);
			glColor4f(0,0,1,0.5);
		}
		glEnable(GL_BLEND);
		glLineWidth(1);
		glEnable(GL_LINE_SMOOTH);


	
		if (!shadow){	
			glDisable(GL_BLEND);
			glColor3f(0.5f,0.5f,0.0f);
		}
		double x1,x2,y1,y2;
		x1=r.x;
		y1=r.y;
		x2=-y1;
		y2=x1;
		
        glPushMatrix();
			glTranslatef(x1,y1,0);
			glTranslatef(0,9,0);
			glutSolidSphere(0.5f,20,20);		
		glPopMatrix();	
		
		if (!shadow){
			glColor4f(0,0,1,0.5);
			glEnable(GL_BLEND);
		}		
		glPushMatrix();
			glTranslatef(0,9.0,0);
			glBegin(GL_LINES);			
				glVertex3f(0,0,0);
				glVertex3f(r.x,r.y,0);
			glEnd();	
		glPopMatrix();		
		
		
		if (!shadow && drawvector){		
								
			glPushMatrix();
				glColor4f(0,0.4,0,0.5);
				glTranslatef(x1,y1+9,0);
				Vector3d vv;
				if (y1>0)
					vv=Vector3d(-y1,x1,0);
				else
					vv=Vector3d(y1,-x1,0);
				vv.norm();
				vv=vv*omega*1.5f;
				glBegin(GL_LINES);
					glVertex3f(0,0,0);
					glVertex3f(vv.x,vv.y,0);
				glEnd();
			
				Vector3d v1(vv.x,vv.y,0);
				Vector3d v2(-1.0,0.0,0.0);			
				v1.norm();
				v2.norm();
				double ang=(v1*v2);
				//double xx=2.0*acos(0)/180.0;
				double xx=PI/180.0;
				ang=acos(ang)/xx;	
					
				glTranslatef(vv.x,vv.y,0);
				if (vv.x<0)
					glRotatef(-90,0,1,0);
				else
					glRotatef( 90,0,1,0);
				if (vv.x<0 ){
					if (vv.y>0)
						glRotated(-ang,1,0,0);
					else
						glRotated(ang,1,0,0);
				}
				else{
					if (vv.y<0)
						glRotated(180-ang,1,0,0);
					else
						glRotated(180+ang,1,0,0);
				}
			
				glutSolidCone(0.1f, 0.4,20, 20);
			glPopMatrix();
		
			glPushMatrix();
				glTranslatef(x1,y1+9,0);
				if (vv.x<0)
					glRotatef(-90,0,1,0);
				else
					glRotatef( 90,0,1,0);

				if (vv.x<0 ){
					if (vv.y>0)
						glRotated(-ang,1,0,0);
					else
						glRotated(ang,1,0,0);
					}
					else{
						if (vv.y<0)
							glRotated(180-ang,1,0,0);
						else
							glRotated(180+ang,1,0,0);
					}

				double l=vv.len();
				gluCylinder(c1, 0.05, 0.05,l,20,20);
			glPopMatrix();
					
		} // if(!shadow)
		
		if (!shadow && motion_blur){		
			glClear(GL_ACCUM_BUFFER_BIT);
			for (int idx=counter-1; idx>=0; idx--){
				glLineWidth(1);
				glEnable(GL_LINE_SMOOTH);
				if (!shadow){
				glColor4f(0,0,1,0.5);
				glEnable(GL_BLEND);
			}
			
			glPushMatrix();
				glTranslatef(0,9,0);
				glBegin(GL_LINES);
					glVertex3f(0,0,0);
					glVertex3f(ox[idx],oy[idx],0);
				glEnd();	
			glPopMatrix();
	
			glPushMatrix();
				glTranslatef(ox[idx],oy[idx],0);
				glTranslatef(0,9,0);
				glColor3f(0.5f,0.5f,0.0f);
				glutSolidSphere(0.4f,20,20);
				glAccum(GL_ACCUM,0.2);		
			glPopMatrix();
		}
		for (int a=counter; a<5; a++)
			glAccum(GL_ACCUM,0.2);
			glAccum(GL_RETURN,1.0);
		}	    
		glDisable(GL_LINE_SMOOTH);
	glPopMatrix();
	
}

void Pendulum::doStep(){
	
	double dt=0.02;				// krok czasowy dla metody numerycznej
	static int step=0;			// zmienna pomocnicza dla efektu motion blur

	switch (solver){
		case 0: 
			solveEuler(dt);				// wywolanie solvera
			break;
		case 1:
			solveMidPoint(dt);
			break;
		case 2:
			solveRK4(dt);
			break;
		default:
			solveEuler(dt);
	}
		
	r.x = radius*sin(alpha);	// wyznaczenie x polozenia kulki
	r.y = radius*cos(alpha);	// wyznaczenie y polozenia kulki
	
	if (step==3){	
	if (counter>4){
		counter=4;
		for (int idx=1; idx<5; idx++){
			ox[idx-1]=ox[idx];
			oy[idx-1]=oy[idx];
		}
	}
	ox[counter]=r.x;
	oy[counter]=r.y;
	counter++;
	step=0;
	}
	step++;
	
	
	double ep,ek,ec;
	ep=calcPoptencialEnergy();
	ek=calcKineticEnergy();
	ec=ep+ek;
	gr->addNextPoint(ep,0);
	gr->addNextPoint(ek,1);
	gr->addNextPoint(ec,2);	
	ec=alpha;
	if (ec<met_min)
		met_min=ec;
	if (ec>met_max)
		met_max=ec;
	if (last!=met_max){
		last=met_max;
		//printf("%f ",met_max-met_min);
		//printf("%f ",met_max);
	}
	//camPos.x=r.x;
	//camPos.y=r.y;
	
	camPos.x=r.x;
	camPos.y=r.y+4;
	camPos.z=1;
}




void Pendulum::keyboard(int key){
	if (key=='m'){
		motion_blur=!motion_blur;
		if (motion_blur){
			
			counter=0;
		}
	}
	//double xx=2.0*acos(0)/180.0;		// PI/180
	double xx=PI/180.0;
	if (key=='z'){
		solver=0;
		omega=0.0;
		alpha=(180.0-START_ANGLE)*xx;	
		met_min=1000.0;
		met_max=0.0;
	}
	if (key=='x'){
		solver=1;
		omega=0.0;
		alpha=(180.0-START_ANGLE)*xx;	
		//alpha=(180.0-START_ANGLE);	
		met_min=1000.0;
		met_max=0.0;
		
	}
	if (key=='c'){
		solver=2;
		omega=0.0;
		alpha=(180.0-START_ANGLE)*xx;	
		met_min=1000.0;
		met_max=0.0;
		
	}
	if (key=='v')
		drawvector=!drawvector;
	
	
}


