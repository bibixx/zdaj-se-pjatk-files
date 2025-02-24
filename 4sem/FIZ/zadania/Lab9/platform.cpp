#include "platform.h"

Platform::Platform():SceneObject(){
		drawplatform=1;
		CWorld::getWorld()->helpInfo->addHelp("p - rysuj platforme (on/off)");
		txt.LoadBMPFile("dat/brick.bmp");
}

void Platform::draw(){
	if (!drawplatform)
		return;
	glLineWidth(2.0);
	float xx=3;
	float aa=0.4;
	
	float x0=0.0;
	float dx=10;
	
	float y0=-5;
	float dy=15;
	
	float z0=0;
	float dz=1;
	
	glDisable(GL_BLEND);
	//glDisable(GL_COLOR_MATERIAL);
	if (1){//!shadow){
		//glDisable(GL_DEPTH_TEST);
			//glEnable(GL_BLEND);
		glLineWidth(3);
		glEnable(GL_LINE_SMOOTH);
		if (!shadow)
			glColor4f(1,0,0,0.6);
		//if (shadow)
		//	glColorMask(GL_FALSE, GL_FALSE, GL_FALSE, GL_TRUE);
		//else
		//	glColorMask(GL_TRUE, GL_TRUE, GL_TRUE, GL_TRUE);

		// oœwietlenie 5,7,20,
		
		glDisable(GL_BLEND);
		
		glBindTexture(GL_TEXTURE_2D, txt.ID);
		glEnable(GL_TEXTURE_2D);
		
		glBegin(GL_QUADS);
			glNormal3f(1,0,0);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(x0+dx,y0,		z0-dz);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(x0+dx,y0+dy,	z0-dz);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(x0+dx,y0+dy,	z0+dz);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(x0+dx,y0,		z0+dz);
		glEnd();
		glBegin(GL_QUADS);
			glNormal3f(1,0,0);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(x0+dx+.5,y0,		z0-dz);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(x0+dx+.5,y0+dy,	z0-dz);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(x0+dx+.5,y0+dy,	z0+dz);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(x0+dx+.5,y0,		z0+dz);
		glEnd();
		if (!shadow)
			glColor4f(0.5,0,0,0.6);
		glBegin(GL_QUADS);
			glNormal3f(0,0,1);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(x0+dx,	  	y0,		z0-dz);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(x0+dx	,	y0+dy,	z0-dz);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(x0+dx+.5,	y0+dy,	z0-dz);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(x0+dx+.5,	y0,		z0-dz);
		glEnd();
		
		glBegin(GL_QUADS);
			glNormal3f(0,0,1);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(x0+dx,	  	y0,		z0+dz);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(x0+dx	,	y0+dy,	z0+dz);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(x0+dx+.5,	y0+dy,	z0+dz);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(x0+dx+.5,	y0,		z0+dz);
		glEnd();
		
		glBegin(GL_QUADS);
			glNormal3f(0,1,0);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(x0+dx,	  	y0+dy,	z0+dz);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(x0+dx	,	y0+dy,	z0-dz);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(x0+dx+.5,	y0+dy,	z0-dz);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(x0+dx+.5,	y0+dy,	z0+dz);
		glEnd();
		
		if (!shadow)
			glColor4f(0,1,0,0.6);
		
		glBegin(GL_QUADS);
			glNormal3f(-1,0,0);
			glTexCoord2f(0.0f, 0.0f);glVertex3f(x0-dx,y0,	z0-dz);
			glTexCoord2f(1.0f, 0.0f);glVertex3f(x0-dx,y0+dy,	z0-dz);
			glTexCoord2f(1.0f, 1.0f);glVertex3f(x0-dx,y0+dy,	z0+dz);
			glTexCoord2f(0.0f, 1.0f);glVertex3f(x0-dx,y0,	z0+dz);
		glEnd();
		glBegin(GL_QUADS);
			glNormal3f(1,0,0);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(x0-dx-.5,y0,		z0-dz);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(x0-dx-.5,y0+dy,	z0-dz);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(x0-dx-.5,y0+dy,	z0+dz);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(x0-dx-.5,y0,		z0+dz);
		glEnd();
		
		if (!shadow)
			glColor4f(0,0.5,0,0.6);
		glBegin(GL_QUADS);
			glNormal3f(0,0,1);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(x0-dx,	  	y0,		z0-dz);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(x0-dx	,	y0+dy,	z0-dz);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(x0-dx-.5,	y0+dy,	z0-dz);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(x0-dx-.5,	y0,		z0-dz);
		glEnd();
		
		glBegin(GL_QUADS);
			glNormal3f(0,0,1);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(x0-dx,	  	y0,		z0+dz);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(x0-dx	,	y0+dy,	z0+dz);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(x0-dx-.5,	y0+dy,	z0+dz);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(x0-dx-.5,	y0,		z0+dz);
		glEnd();
		
		glBegin(GL_QUADS);
			glNormal3f(0,1,0);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(x0-dx,	  	y0+dy,	z0+dz);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(x0-dx	,	y0+dy,	z0-dz);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(x0-dx-.5,	y0+dy,	z0-dz);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(x0-dx-.5,	y0+dy,	z0+dz);
		glEnd();
		
		
		if (!shadow)
			glColor4f(1,1,1,0.6);
		
		glBegin(GL_QUADS);
			glNormal3f(-1,0,0);
			glVertex3f(x0-dx,3+.1,	z0-.1);
			glVertex3f(x0-dx,3-.1,	z0-.1);
			glVertex3f(x0-dx,3-.1,	z0+.1);
			glVertex3f(x0-dx,3+.1,	z0+.1);
		glEnd();
		glBegin(GL_QUADS);
			glNormal3f(-1,0,0);
			glVertex3f(x0+dx,3+.1,	z0-.1);
			glVertex3f(x0+dx,3-.1,	z0-.1);
			glVertex3f(x0+dx,3-.1,	z0+.1);
			glVertex3f(x0+dx,3+.1,	z0+.1);
		glEnd();
		

		glEnable(GL_BLEND);
		

		glEnable(GL_COLOR_MATERIAL);
		
		
		float top,bottom;
		top=10;
		bottom=8;
		

		glEnable(GL_DEPTH_TEST);
	}	
	glDisable(GL_BLEND);
	glDisable(GL_LINE_SMOOTH);
	glDisable(GL_TEXTURE_2D);
	
}

void Platform::doStep(){
}

void Platform::keyboard(int key){
	if (key=='p')
		drawplatform=!drawplatform;
}
