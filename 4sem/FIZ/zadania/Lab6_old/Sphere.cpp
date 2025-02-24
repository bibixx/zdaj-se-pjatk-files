#include "Sphere.h"

Sphere::Sphere(double m, double r, Vector3d pos):Body(){
	this->m = m;
	this->r = r;
	this->pos = pos;
	this->sphere = gluNewQuadric();
	
	gluQuadricDrawStyle(sphere, GLU_FILL);
	gluQuadricNormals(sphere, GL_SMOOTH);
	gluQuadricOrientation(sphere, GLU_OUTSIDE);
	gluQuadricTexture(sphere, GL_TRUE);	
}

void Sphere::draw(){
	glPushMatrix();				
		glRotatef(90,1,0,0);
		//double z=this->r1/cos(Alfa);
		//double de=z-this->r1;
		//static float  oom;
		//static Vector3d ov1;
				
		glTranslatef(this->pos.x,this->pos.y,this->pos.z);	
		//glTranslatef(0,0,-de);	
		glRotatef(-180.0*alpha/M_PI,0,1,0);
			
		gluSphere(sphere, this->r,150,150);
			
	glPopMatrix();	
}

