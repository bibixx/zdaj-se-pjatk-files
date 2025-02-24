#include "universe.h"

Universe::Universe():SceneObject(){
	sphere=gluNewQuadric();	
	gluQuadricDrawStyle(sphere, GLU_FILL);
	gluQuadricNormals(sphere, GL_SMOOTH);
	gluQuadricOrientation(sphere, GLU_INSIDE);
	gluQuadricTexture(sphere, GL_TRUE);
	
	//tex.LoadBMPFile("u2.bmp");
	//tex.LoadBMPFile("gc.bmp");
	//tex.LoadBMPFile("stars.bmp");	
	//tex.LoadBMPFile("dome.bmp");	
	tex.LoadBMPFile("dat/dome.bmp");	

	
}

void Universe::draw(){
	static double yy=0;
		glColor3f(0.5,0.5,0.5);
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, tex.ID);
		
		glDisable(GL_COLOR_MATERIAL);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glDisable(GL_BLEND);
	glPushMatrix();	
		glRotatef(90,1,0,0);
		glRotatef(yy,0,0,1);
		glDepthMask(GL_FALSE);
		gluSphere(sphere, 400,30,30);
		glDepthMask(GL_TRUE);
		glDisable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glEnable(GL_COLOR_MATERIAL);
	glPopMatrix();
	yy-=0.1;
}
