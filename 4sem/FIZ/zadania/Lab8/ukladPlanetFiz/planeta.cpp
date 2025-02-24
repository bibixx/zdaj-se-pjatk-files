#include "planeta.h"

Planeta::Planeta(Vector3d pos, double m, double r, char* tname):SceneObject(){
	this->pos = pos;
	this->mass = m;
	this->r = r;
	rot = 0.0;
	sphere=gluNewQuadric();	
	gluQuadricDrawStyle(sphere, GLU_FILL);
	gluQuadricNormals(sphere, GL_SMOOTH);
	gluQuadricOrientation(sphere, GLU_OUTSIDE);
	gluQuadricTexture(sphere, GL_TRUE);
	
	tex.LoadBMPFile(tname);	

	listID=glGenLists(1);
	glNewList(listID,GL_COMPILE);
		gluSphere(sphere, this->r,200,30);
	glEndList(); 	
}

void Planeta::draw(){
	glPushMatrix();
		glRotatef(90,1,0,0);
		Vector3d p=pos;
		double factor=1495978.87;
		//double factor=1.0;
		//if (z==1)
		//	p=p+350000;
		
		p=p*(1.0/factor);
		glTranslatef(p.x, p.y, p.z);
		
		glRotatef(-rot++,0,0,1);
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, tex.ID);	
		
		glCallList(listID);
		glDisable(GL_TEXTURE_2D);
	glPopMatrix();
}

