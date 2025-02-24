#include "simulation.h"
#include "param.h"


// po obrocie ukladu dla uproszczenia obracamy wektor przyspieszenia
// zamiast g w obliczeniach stosujemu gg
Vector3d gg(9.81*cos(Alfa),0,9.81*sin(Alfa));	

//-------------------------------------------
// Zadanie:
//-------------------------------------------
// zaimplementowac metody:
// 
// solveMidpoint
// solveRK4
//-------------------------------------------
// tym razem nie dostarczono procedury
// derivatives, oczywiscie mozna napisac
// wlasna (usprawni to pisanie kodu)
// nalezy tylko pamietac, ze rozwiazujemy
// dwa rownania osobno dla ruchu postepowego
// oraz dla ruchu obrotowego
//-------------------------------------------


//--------------------------------------------
// kula
// 	Wektor3d pos1		- polozenie
//	float    Omega1		- predkosc katowa
//  float	 angle1		- kat
//  Wektor3d V1			- predkosc liniowa	
//	float    r1			- pronien 
// walec
// 	Wektor3d pos2		- polozenie
//	float    Omega2		- predkosc katowa
//  float	 angle2		- kat
//  Wektor3d V2			- predkosc liniowa	
//  float    r2         - promien
//  
//--------------------------------------------

void simulation::solveEuler(double dt){
	// przyspieszenie liniowe
	// a = (g sin(Alfa))/(1+(I/mr^2))
	// I dla kuli = 2mr^2/5
	// I dla walca = mr^2/2
	Vector3d a;
	
	//-----------------------
	// Kula
	//-----------------------
	// po podstawieniu I dla kuli
	a=gg*sin(Alfa)/(1.0+2.0/5.0); 
	
	// V1 - predkosc liniowa kuli
	// pos1 - polozenie kuli
	V1=V1+a*dt;
	pos1=pos1+V1*dt;
	
	// przyspieszenie katowe epislon=|a|/r
	float Epsil1=((a).len())/r1;
	Omega1=Omega1+Epsil1*dt;
	angle1=angle1+Omega1*dt;
	
	//-----------------------
	// walec
	//-----------------------
	// po podstawieniu I dla walca
	a=gg*sin(Alfa)/(1.0+0.5);
	
	V2=V2+a*dt;
	pos2=pos2+V2*dt;
	float Epsil2=((a).len())/r2;
	Omega2=Omega2+Epsil2*dt;
	angle2=angle2+Omega2*dt;	
}

void simulation::solveMidPoint(double dt) {
	Vector3d a;
	
	//Kula
	a = gg*sin(Alfa)/(1.0 + 2.0/5.0);
	
	float v = sqrt(pow(V1.x,2)+pow(V1.z,2));
	float s;
	float k1_s; 
	float k1_v;
	float k2_s; 
	float k2_v;
	k1_v = dt * ((a).len());
	k1_s = dt * v; 
	k2_v = k1_v;
	k2_s = dt * (v + 0.5 * k1_v); 

	v += k2_v;
	s += k2_s;
	
	V1.x = v * cos(Alfa);
	V1.z = v * sin(Alfa);
	pos1.x += V1.x * dt;//polozenie 
	pos1.z += V1.z * dt;

	
//	float Epsil1 = g*sin(Alfa)/(R*(1.0+ 2.0/5.0));
	float Epsil = ((a).len())/r1;
	
	float k1_angle; 
	float k1_omega;
	float k2_angle; 
	float k2_omega;
	k1_omega = dt * Epsil ;
	k1_angle = dt * Omega1; 
	k2_omega = k1_omega ;
	k2_angle = dt * (Omega1 + 0.5 * k1_omega); 
	
	Omega1 += k2_omega;
	angle1 += k2_angle;
	
	Rot1.y = 180.0 * angle1/M_PI;

	//Walec
	Vector3d a_w;
	a_w = gg*sin(Alfa)/(1.0 + 0.5);
	
	float v_w=sqrt(pow(V2.x,2)+pow(V2.z,2));
	float s_w;
	float k1_s_w; 
	float k1_v_w;
	float k2_s_w; 
	float k2_v_w;
	k1_v_w = dt * ((a_w).len());
	k1_s_w = dt * v_w ; 
	k2_v_w = k1_v_w;
	k2_s_w = dt * (v_w + 0.5 * k1_v_w); 
	
	v_w += k2_v_w;
	s_w += k2_s_w;
	
	V2.x = v_w * cos(Alfa);
	V2.z = v_w * sin(Alfa);
	pos2.x += + V2.x*dt;
	pos2.z += + V2.z*dt;

	//float Epsil1 = g*sin(Alfa)/(R*(1.0+ 2.0/5.0));
	float Epsil_w = ((a_w).len())/r2;
	
	float k1_angle_w; 
	float k1_omega_w;
	float k2_angle_w; 
	float k2_omega_w;
	k1_omega_w = dt * Epsil_w ;
	k1_angle_w = dt * Omega2; 
	k2_omega_w = k1_omega_w;
	k2_angle_w = dt * (Omega2 + 0.5 * k1_omega_w); 

	Omega2 += k2_omega_w;
	angle2 += k2_angle_w;
	
	Rot2.y = 180.0 * angle2/M_PI;

}


void simulation::solveRK4(double dt) {
	Vector3d a;
	
	//Kula
	a = gg*sin(Alfa)/(1.0 + 2.0/5.0);
	
	float v = sqrt(pow(V1.x,2)+pow(V1.z,2));
	float s;
	float k1_s; 
	float k1_v;
	float k2_s; 
	float k2_v;
	float k3_s; 
	float k3_v;
	float k4_s; 
	float k4_v;
	
	k1_v = dt * ((a).len());
	k1_s = dt * v; 
	k2_v = k1_v;
	k2_s = dt * (v + 0.5 * k1_v); 
	k3_v = k1_v;
	k3_s = dt * (v + 0.5 * k2_v); 
	k4_v = k1_v;
	k4_s = dt * (v + k3_v); 
	
	v += (k1_v / 6) + (k2_v / 3) + (k3_v / 3) + (k4_v / 6); 
	s += (k1_s / 6) + (k2_s / 3) + (k3_s / 3) + (k4_s / 6);

	
	V1.x = v * cos(Alfa);
	V1.z = v * sin(Alfa);
	pos1.x += V1.x*dt;
	pos1.z += V1.z*dt;

	
//	float Epsil1 = g*sin(Alfa)/(R*(1.0+ 2.0/5.0));
	float Epsil = ((a).len())/r1;
	
	float k1_angle; 
	float k1_omega;
	float k2_angle; 
	float k2_omega;
	float k3_angle; 
	float k3_omega;
	float k4_angle; 
	float k4_omega;
	
	k1_omega = dt * Epsil ;
	k1_angle = dt * Omega1; 
	k2_omega = k1_omega;
	k2_angle = dt * (Omega1 + 0.5 * k1_omega); 
	k3_omega = k1_omega;
	k3_angle = dt * (Omega1 + 0.5 * k2_omega); 
	k4_omega = k1_omega;
	k4_angle = dt * (Omega1 + k3_omega); 
	
	Omega1 += (k1_omega / 6) + (k2_omega / 3) + (k3_omega / 3) + (k4_omega / 6); 
	angle1 += (k1_angle / 6) + (k2_angle / 3) + (k3_angle / 3) + (k4_angle / 6);

	Rot1.y = 180.0*angle1/M_PI;
	
	
	
	//Walec
	Vector3d a_w;
	a_w = gg * sin(Alfa) / (1.0 + 0.5);
	
	float v_w=sqrt(pow(V2.x,2)+pow(V2.z,2));
	float s_w;
	float k1_s_w; 
	float k1_v_w;
	float k2_s_w; 
	float k2_v_w;
	float k3_s_w; 
	float k3_v_w;
	float k4_s_w; 
	float k4_v_w;
	
	k1_v_w = dt * ((a_w).len());
	k1_s_w = dt * v_w ; 
	k2_v_w = k1_v_w;
	k2_s_w = dt * (v_w + 0.5 * k1_v_w); 
	k3_v_w = k1_v_w;
	k3_s_w = dt * (v_w + 0.5 * k2_v_w); 
	k4_v_w = k1_v_w;
	k4_s_w = dt * (v_w + k3_v_w); 
	
	v_w += (k1_v_w / 6) + (k2_v_w / 3) + (k3_v_w / 3) + (k4_v_w / 6); 
	s_w += (k1_s_w / 6) + (k2_s_w / 3) + (k3_s_w / 3) + (k4_s_w / 6);


	V2.x = v_w * cos(Alfa);
	V2.z = v_w * sin(Alfa);
	this->pos2.x += V2.x*dt; 
	this->pos2.z += V2.z*dt;

	//float Epsil1 = g*sin(Alfa)/(R*(1.0+ 2.0/5.0));
	float Epsil_w = ((a_w).len())/r2;
	
	float k1_angle_w; 
	float k1_omega_w;
	float k2_angle_w; 
	float k2_omega_w;
	float k3_angle_w; 
	float k3_omega_w;
	float k4_angle_w; 
	float k4_omega_w;

	k1_omega_w = dt * Epsil_w ;
	k1_angle_w = dt * Omega2; 
	k2_omega_w = k1_omega_w;
	k2_angle_w = dt * (Omega2 + 0.5 * k1_omega_w); 
	k3_omega_w = k1_omega_w;
	k3_angle_w = dt * (Omega2 + 0.5 * k2_omega_w); 
	k4_omega_w = k1_omega_w;
	k4_angle_w = dt * (Omega2 + k3_omega_w); 
	
	Omega2 += (k1_omega_w / 6) + (k2_omega_w / 3) + (k3_omega_w / 3) + (k4_omega_w / 6); 
	angle2 += (k1_angle_w / 6) + (k2_angle_w / 3) + (k3_angle_w / 3) + (k4_angle_w / 6);

	Rot2.y = 180.0*angle2/M_PI;
}



//-----------------------------
// RESZTA KODU
//-----------------------------
void simulation::reset(){
	pos1 = Vector3d(-20.0,7.5,-HH-r1-0.4);
	pos2 =  Vector3d(-20,-r2,-HH-r2-0.4);
	V1=0; Omega1=0; Rot1=0; angle1=0;
	V2=0; Omega2=0; Rot2=0; angle2=0;		
	
}
void simulation::doStep(){
	if ( pos1.x < 30.0 )
	{	
		switch (solver){
		case 0:
			solveEuler(.055);
			break;
		case 1:
			solveMidPoint(.055);
			break;
		case 2:
			solveRK4(.055);
			break;
		default:
			solveEuler(.055);
		}
	}
	else{
		reset();
	}
}

void simulation::keyboard(int key){
int res=0;
	if (key=='e'){
		solver=0;
		res=1;
		printf("Solver --> Euler\n");
	}
	if (key=='m'){
		solver=1;
		res=1;
		printf("Solver --> MidPoint\n");
	}
	if (key=='r'){
		solver=2;
		res=1;
		printf("Solver --> RK4\n");
	}

	
if (res){
	this->V1 = Vector3d(0,0,0);
	this->Rot1 = Vector3d(0,0,0);
	this->V2 = Vector3d(0,0,0);
	this->Rot2 = Vector3d(0,0,0);
	
	this->Omega1=0.0;
	this->angle1 = 0.0;
	this->Omega2=0.0;
	this->angle2 = 0.0;	
	reset();
}	
	
}	
	
//----------------------------------------------------------------------	

simulation::simulation(Vector3d pos1, Vector3d pos2):SceneObject(){
	txt1.LoadBMPFile("dat/earth.bmp");
	txt2.LoadBMPFile("dat/Ball.bmp");
	solver=0;


	sphere1=gluNewQuadric();
	tube=gluNewQuadric();
	disk1=gluNewQuadric();
	disk2=gluNewQuadric();
	
	gluQuadricDrawStyle(sphere1, GLU_FILL);
	gluQuadricNormals(sphere1, GL_SMOOTH);
	gluQuadricOrientation(sphere1, GLU_OUTSIDE);
	gluQuadricTexture(sphere1, GL_TRUE);
	
	gluQuadricDrawStyle(tube, GLU_FILL);
	gluQuadricNormals(tube, GL_SMOOTH);
	gluQuadricOrientation(tube, GLU_OUTSIDE);
	gluQuadricTexture(tube, GL_TRUE);
	
	gluQuadricDrawStyle(disk1, GLU_FILL);
	gluQuadricNormals(disk1, GL_SMOOTH);
	gluQuadricOrientation(disk1, GLU_INSIDE);
	gluQuadricTexture(disk1, GL_TRUE);
	
	gluQuadricDrawStyle(disk2, GLU_FILL);
	gluQuadricNormals(disk2, GL_SMOOTH);
	gluQuadricOrientation(disk2, GLU_INSIDE);
	gluQuadricTexture(disk2, GL_TRUE);
	
	this->pos1 = pos1;
	this->pos2 = pos2;

	this->r1=2.5;
	this->r2=2.5;

	
	this->V1 = Vector3d(0,0,0);
	this->Rot1 = Vector3d(0,0,0);
	this->V2 = Vector3d(0,0,0);
	this->Rot2 = Vector3d(0,0,0);
	
	this->Omega1=0.0;
	this->angle1 = 0.0;
	this->Omega2=0.0;
	this->angle2 = 0.0;
	
	CWorld::helpInfo->addHelp("Solver:");
	CWorld::helpInfo->addHelp(" e - Euler");
	CWorld::helpInfo->addHelp(" m - MidPoint");
	CWorld::helpInfo->addHelp(" r - RK4");	
}

void simulation::draw(){
	if (!shadow){
		glColor3f(0.5,0.5,0.5);
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, txt1.ID);
		
		glDisable(GL_COLOR_MATERIAL);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glDisable(GL_BLEND);
			
	}
	else{
		glDisable(GL_LIGHTING);
		glDisable(GL_TEXTURE_2D);
	}
	
	
	glPushMatrix();				
		glRotatef(90,1,0,0);
		static float  oom;
		static Vector3d ov1;
				
		glTranslatef(this->pos1.x,this->pos1.y,this->pos1.z);	

		if (5.0-pos1.z-r1<-0.3){
			V1=ov1;
			Omega1=oom;				
		}
		else{
			ov1=V1;
			oom=Omega1;
		}
		if (pos1.z-this->r1>0.0){
			glTranslatef(0,0,-pos1.z+this->r1);			
		}
				

		glRotatef(-180.0*angle1/M_PI,0,1,0);
		gluSphere(sphere1, this->r1,150,150);
			
	glPopMatrix();

	if (!shadow){
		glColor3f(0.5,0.5,0.5);
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, txt2.ID);
		
		glDisable(GL_COLOR_MATERIAL);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glDisable(GL_BLEND);
			
	}
	else{
		glDisable(GL_LIGHTING);
		glDisable(GL_TEXTURE_2D);
	}		
		
	
	glPushMatrix();			
		glRotatef(90,1,0,0);
		glTranslatef(this->pos2.x,this->pos2.y,this->pos2.z);	
			
		if (pos2.z-this->r2>0.0){			
			glTranslatef(0,0,-pos2.z+this->r2);
		}
			
		glRotatef(-180.0*angle2/M_PI,0,1,0);
			
		glRotatef(90,1,0,0);
		gluCylinder( tube, this->r1, this->r1, 8, 40,40 );
			
			
		gluDisk( disk1, 0, this->r1, 30,30 );
		glTranslatef(0,0,8);	
		gluDisk( disk2, 0, this->r1, 30,30 );
	glPopMatrix();
		
	glDisable(GL_TEXTURE_2D);
	glEnable(GL_COLOR_MATERIAL);				
}



