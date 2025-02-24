#include "hover.h"


void HoverCraft::drawControl(int id, int w, int h){
		
	double scale=h/2.0;
	scale/=2000.0;
	
	glBegin(GL_LINES);
		glVertex2f(0,h/2+15+1);
		glVertex2f(w,h/2+15+1);
	glEnd();
	double dr=1.0;
	float opt=1.0;
	double p=0.0;
	double p1=1.25;
	glColor3f(0,1,0);
	if (craft->thrustForce<0){
		opt=-1.0;
		glColor3f(1,0,0);
		p1=-1.25;
	}
	while (p<fabs(craft->thrustForce)){
	if (dr){
	glBegin(GL_QUADS);
		glVertex2f(5,h/2+15-p1);
		glVertex2f(w-5,h/2+15-p1);
		glVertex2f(w-5,h/2+2+15-p1);
		glVertex2f(5,h/2+2+15-p1);
	glEnd();
	}
	p=p+50;
	p1=p1+opt*(1.25);
	dr=!dr;
	}

}

HoverCraft::HoverCraft():SceneObject(){
	
	craft = new RigidBody();
	this->solver=0;
	hoverModel = new MeshObject();
	hoverModel->LoadObject("dat/hc.obj",50);
	
	towerModel = new MeshObject();
	towerModel->LoadObject("dat/mtwr.obj",0);
	
	roverModel = new MeshObject();
	roverModel->LoadObject("dat/r.obj",0);
			
	CWorld::helpInfo->addHelp("Solver");			
	CWorld::helpInfo->addHelp("  e - Euler");		
	CWorld::helpInfo->addHelp("  m - MidPoint");		
	CWorld::helpInfo->addHelp("  r - RK4");	

	CWorld::helpInfo->addHelp("1 - kamera widok z gory");	
	CWorld::helpInfo->addHelp("0 - kamera perspektywa");	
	
	CWorld::helpInfo->addHelp("Sterowanie kursory");	
	CWorld::helpInfo->addHelp("  left  - wlaczenie silnika na prawym boku");	
	CWorld::helpInfo->addHelp("  right - wlaczenie silnika na lewym boku");	
	CWorld::helpInfo->addHelp("  up    - zwiekszenie ciagu silnika tylnego");	
	CWorld::helpInfo->addHelp("  down  - zmniejszenie ciagu silnika tylnego");	
}

void HoverCraft::draw(){
	glDisable(GL_TEXTURE_2D);
	glEnable(GL_DEPTH_TEST);
	glEnable(GL_NORMALIZE);
	
	glPushMatrix();
		static double s=0.0;
		glTranslatef(craft->position.x,sin(s)/2.0,craft->position.y);
		s=s+0.02;
			if (s>6.28){
				s=0.0;
			}
		glRotatef(-craft->orientation,0,1,0);	


		glRotatef(90,0,1,0);
		glScalef(12,12,12);

		hoverModel->draw();
	 glPopMatrix();
 
	glPushMatrix();
		static double posz=-130;
		static int dir=1;
		glTranslatef(40,3,posz);
		glScalef(12,12,12);
		roverModel->draw();
		posz+=(dir*0.1);
		if (posz>0)
			dir=-dir;
		if (posz<-130)
			dir=-dir;
	glPopMatrix();
	glPushMatrix();
		glTranslatef(100,55,-100);
		glScalef(70,60,70);
		towerModel->draw();
	glPopMatrix();
	glPushMatrix();
		glTranslatef(-100,55,-100);
		glScalef(70,60,70);
		towerModel->draw();
	glPopMatrix();
	glEnable(GL_COLOR_MATERIAL);
	
	camPos.x=craft->position.x;
	camPos.z=craft->position.y;
	camPos.y=0.0;
	glDisable(GL_NORMALIZE);
}

void HoverCraft::doStep(){
	
	//craft->UpdateBody();
	switch (solver){
	case 0:		
		craft->solveEuler();
		break;
	case 1:
		craft->solveMidPoint();
		break;
	case 2:
		craft->solveRK4();
		break;
	}
	craft->pThrust = Vector3d(0.0,0.0,0.0);
	craft->sThrust = Vector3d(0.0,0.0,0.0);	
	
//	printf("%f %f %f\n", craft->position.x,craft->position.y,craft->position.z);
//	printf("%f ",craft->speed);
	
	if (craft->position.len()>490)
		craft->position = Vector3d(0,0,0);
}

void HoverCraft::specialkey(int key, int x, int y){
	craft->pThrust = Vector3d(0.0,0.0,0.0);
	craft->sThrust = Vector3d(0.0,0.0,0.0);	

	if (key==GLUT_KEY_UP){
		craft->thrustForce = craft->thrustForce + 50.0f; 
		if (craft->thrustForce>2000)
			craft->thrustForce=2000.0f;
	}

	if (key==GLUT_KEY_DOWN){
		craft->thrustForce = craft->thrustForce - 50.0f; 
		if (craft->thrustForce<-2000)
			craft->thrustForce=-2000.0;
	}	

	if (key==GLUT_KEY_LEFT){
		//lewy silnik pTrust
		craft->pThrust.y = -700.0f;
		
	}
	if (key==GLUT_KEY_RIGHT){
		// prawy sTrust
		craft->sThrust.y = 700.0f;
	}		
}

void HoverCraft::keyboard(int key){
		
	switch (key){
	case 'e':
		solver=0;
		std::cout << "solver -> Euler" << std::endl;
		break;
	case 'm':
		solver=1;
		std::cout << "solver -> MidPoint" << std::endl;
		break;
	case 'r':
		solver=2;
		std::cout << "solver -> RK4" << std::endl;
		break;
	}

}
