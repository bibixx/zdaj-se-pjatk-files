#include "Rectangle.h"

Rectanglem::Rectanglem():SceneObject(){
}

void Rectanglem::draw(){
	glColor3f(1,0,0);
	glPushMatrix();
	glRotatef(90,1,0,0);
	glBegin(GL_QUADS);
		glVertex3f(0,0,0);
		glVertex3f(-5,0,0);
		glVertex3f(-5,-5,0);
		glVertex3f(0,-5,0);
	glEnd();	
	glPopMatrix();
}
