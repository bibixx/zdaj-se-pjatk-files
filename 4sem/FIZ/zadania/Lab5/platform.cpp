#include "platform.h"

Platform::Platform():SceneObject(){
		drawplatform=1;
		CWorld::getWorld()->helpInfo->addHelp("p - rysuj platforme (on/off)");
		model = new MeshObject();
		model->LoadObject("dat/tow.obj",50);
}

void Platform::draw(){
	if (!drawplatform)
		return;
	
	

	//glEnable(GL_DEPTH_TEST);
	glEnable(GL_NORMALIZE);
	glPushMatrix();
	glTranslatef(0,2,-0.5);
	glScalef(8.6,8.6,8.6);
	
	model->draw();
	glPopMatrix();
	return;
	
	glLineWidth(2.0);
	float xx=3;
	float aa=0.4;
	glDisable(GL_BLEND);
	//glDisable(GL_COLOR_MATERIAL);
	if (1){//!shadow){
		//glDisable(GL_DEPTH_TEST);
			glEnable(GL_BLEND);
		glLineWidth(3);
		glEnable(GL_LINE_SMOOTH);
		if (!shadow)
			glColor4f(0.1,0.2,0.2,0.6);
		//if (shadow)
		//	glColorMask(GL_FALSE, GL_FALSE, GL_FALSE, GL_TRUE);
		//else
		//	glColorMask(GL_TRUE, GL_TRUE, GL_TRUE, GL_TRUE);

		glBegin(GL_LINE_STRIP);
			glVertex3f(xx,9,-xx);
			glVertex3f(xx-aa,9,-xx);			
			glVertex3f(xx-aa,-5,-xx);
			glVertex3f(xx,-5,-xx);
			glVertex3f(xx,9,-xx);
			
		glEnd();

		glBegin(GL_LINE_STRIP);
			glVertex3f(xx,9,-xx+aa);
			glVertex3f(xx-aa,9,-xx+aa);			
			glVertex3f(xx-aa,-5,-xx+aa);
			glVertex3f(xx,-5,-xx+aa);
			glVertex3f(xx,9,-xx+aa);
			
		glEnd();
		
			glDisable(GL_BLEND);
		
		glBegin(GL_QUADS);
			glNormal3f(0,1,0);
			glVertex3f( xx,9,-xx);
			glVertex3f(xx-aa,9, -xx);
			glVertex3f( xx-aa,9, -xx+aa);
			glVertex3f( xx,9,-xx+aa);
		glEnd();
		
		glBegin(GL_QUADS);
			glNormal3f(0,1,0);
			glVertex3f( xx,-5+0.01,-xx);
			glVertex3f(xx-aa,-5+0.01, -xx);
			glVertex3f( xx-aa,-5+0.01, -xx+aa);
			glVertex3f( xx,-5+0.01,-xx+aa);
		glEnd();
		glEnable(GL_BLEND);


//
		glBegin(GL_LINE_STRIP);
			glVertex3f(-xx,9,-xx);
			glVertex3f(-xx+aa,9,-xx);			
			glVertex3f(-xx+aa,-5,-xx);
			glVertex3f(-xx,-5,-xx);
			glVertex3f(-xx,9,-xx);
			
		glEnd();

		glBegin(GL_LINE_STRIP);
			glVertex3f(-xx,9,-xx+aa);
			glVertex3f(-xx+aa,9,-xx+aa);			
			glVertex3f(-xx+aa,-5,-xx+aa);
			glVertex3f(-xx,-5,-xx+aa);
			glVertex3f(-xx,9,-xx+aa);
			
		glEnd();

			glDisable(GL_BLEND);
		glBegin(GL_QUADS);
			glNormal3f(0,1,0);
			glVertex3f( -xx,9,-xx);
			glVertex3f(-xx+aa,9, -xx);
			glVertex3f( -xx+aa,9, -xx+aa);
			glVertex3f( -xx,9,-xx+aa);
		glEnd();
		
		glBegin(GL_QUADS);
			glNormal3f(0,1,0);
			glVertex3f( -xx,-5+0.01,-xx);
			glVertex3f(-xx+aa,-5+0.01, -xx);
			glVertex3f( -xx+aa,-5+0.01, -xx+aa);
			glVertex3f( -xx,-5+0.01,-xx+aa);
		glEnd();
		glEnable(GL_BLEND);
	
//---
		glBegin(GL_LINE_STRIP);
			glVertex3f(-xx,9,xx);
			glVertex3f(-xx+aa,9,xx);			
			glVertex3f(-xx+aa,-5,xx);
			glVertex3f(-xx,-5,xx);
			glVertex3f(-xx,9,xx);
			
		glEnd();

		glBegin(GL_LINE_STRIP);
			glVertex3f(-xx,9,xx-aa);
			glVertex3f(-xx+aa,9,xx-aa);			
			glVertex3f(-xx+aa,-5,xx-aa);
			glVertex3f(-xx,-5,xx-aa);
			glVertex3f(-xx,9,xx-aa);
			
		glEnd();		
		
		
			glDisable(GL_BLEND);

		glBegin(GL_QUADS);
		glNormal3f(0,1,0);
			glVertex3f( -xx,9,xx);
			glVertex3f(-xx+aa,9, xx);
			glVertex3f( -xx+aa,9, xx-aa);
			glVertex3f( -xx,9,xx-aa);
		glEnd();
		
		glBegin(GL_QUADS);
		glNormal3f(0,1,0);
			glVertex3f( -xx,-5+0.01,xx);
			glVertex3f(-xx+aa,-5+0.01, xx);
			glVertex3f( -xx+aa,-5+0.01, xx-aa);
			glVertex3f( -xx,-5+0.01,xx-aa);
		glEnd();
		glEnable(GL_BLEND);	

//-----------------

		glBegin(GL_LINE_STRIP);
			glVertex3f(xx,9,xx);
			glVertex3f(xx-aa,9,xx);			
			glVertex3f(xx-aa,-5,xx);
			glVertex3f(xx,-5,xx);
			glVertex3f(xx,9,xx);
			
		glEnd();

		glBegin(GL_LINE_STRIP);
			glVertex3f(xx,9,xx-aa);
			glVertex3f(xx-aa,9,xx-aa);			
			glVertex3f(xx-aa,-5,xx-aa);
			glVertex3f(xx,-5,xx-aa);
			glVertex3f(xx,9,xx-aa);
			
		glEnd();			
		
		
		glDisable(GL_BLEND);
		glBegin(GL_QUADS);
			glNormal3f(0,1,0);
			glVertex3f( xx,9,xx);
			glVertex3f(xx-aa,9, xx);
			glVertex3f( xx-aa,9, xx-aa);
			glVertex3f( xx,9,xx-aa);
		glEnd();
		
		glBegin(GL_QUADS);
			glNormal3f(0,1,0);
			glVertex3f( xx,-5+0.01,xx);
			glVertex3f(xx-aa,-5+0.01, xx);
			glVertex3f( xx-aa,-5+0.01, xx-aa);
			glVertex3f( xx,-5+0.01,xx-aa);
		glEnd();
		glEnable(GL_BLEND);		

	//if (!shadow)
	//	glColor3f(1,0,0);
	
	glBegin(GL_LINE_STRIP);
		glVertex3f(-xx,9,-xx);
		glVertex3f(xx,9,-xx);
		glVertex3f(xx,9,xx);
		glVertex3f(-xx,9,xx);
		glVertex3f(-xx,9,-xx);
	glEnd();



	
		/*
			glVertex3f(xx,9,-xx);
			glVertex3f(xx,9,-xx+aa);			
			glVertex3f(xx,-5,-xx+aa);
			glVertex3f(xx,-5,-xx);
			
			
			glVertex3f(xx,9,-xx+aa);
			glVertex3f(xx-aa,9,-xx+aa);			
			glVertex3f(xx-aa,-5,-xx+aa);
			glVertex3f(xx,-5,-xx+aa);
			
			
//
			glVertex3f(xx,9,xx);
			glVertex3f(xx-aa,9,xx);
			
			glVertex3f(xx-aa,-5,xx);
			glVertex3f(xx,-5,xx);

			*/
		glEnable(GL_COLOR_MATERIAL);
		//glDisable(GL_DEPTH_TEST);
	if (!shadow)		
		glDisable(GL_DEPTH_TEST);		
		if (!shadow)
			glColor4f(0.3,0.3,0.3,0.5);
		glEnable(GL_BLEND);
		glBegin(GL_QUADS);
			glNormal3f(0,1,0);
			glVertex3f(-xx,9-0.01,-xx);
			glVertex3f(-xx,9-0.01, xx);
			glVertex3f( xx,9-0.01, xx);
			glVertex3f( xx,9-0.01,-xx);
		glEnd();
		
		float top,bottom;
		top=10;
		bottom=8;
		
	//if (!shadow)
	//	glColor4f(0.2,0.2,0.5,0.2);
		/*
		glBegin(GL_QUADS);
			glVertex3f(-xx,bottom,-xx);
			glVertex3f(-xx,top,-xx);
			glVertex3f( xx,top,-xx);
			glVertex3f( xx,bottom,-xx);
		glEnd();	

		glBegin(GL_QUADS);
			glVertex3f(-xx,bottom,xx);
			glVertex3f(-xx,top,xx);
			glVertex3f( xx,top,xx);
			glVertex3f( xx,bottom,xx);
		glEnd();	

		glBegin(GL_QUADS);
			glVertex3f(-xx,bottom,-xx);
			glVertex3f(-xx,top,-xx);
			glVertex3f( -xx,top,xx);
			glVertex3f( -xx,bottom,xx);
		glEnd();	

		glBegin(GL_QUADS);
			glVertex3f(xx,bottom,-xx);
			glVertex3f(xx,top,-xx);
			glVertex3f( xx,top,xx);
			glVertex3f( xx,bottom,xx);
		glEnd();	
		*/
		
		//glEnd();
		glEnable(GL_DEPTH_TEST);
	}	
	glDisable(GL_BLEND);
	glDisable(GL_LINE_SMOOTH);
	
}

void Platform::doStep(){
}

void Platform::keyboard(int key){
	if (key=='p')
		drawplatform=!drawplatform;
}
