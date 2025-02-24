#include "LeftWall.h"

LeftWall::LeftWall():SceneObject(){
	//txt.LoadBMPFile("brick.bmp");
	txt.LoadBMPFile("dat/a.bmp");
	isShadowPlane = 1;
}

void LeftWall::draw(){
		//glPushAttrib(GL_COLOR);
		glDisable(GL_BLEND);
		glBindTexture(GL_TEXTURE_2D, txt.ID);
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(-26,-5,-26);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(-26,-5,0);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(-26,15,0);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(-26,15,-26);
			
			glTexCoord2f(0.0f, 0.0f); glVertex3f(-26,-5,0);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(-26,-5,26);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(-26,15,26);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(-26,15,0);			
		glEnd();
		glDisable(GL_TEXTURE_2D);
		/*
		glColor4f(0,0,1,0.5);
		glBegin(GL_QUADS);
			glVertex3f(-10,5,-10);
			glVertex3f(-10,5,10);
			glVertex3f(10,5,10);
			glVertex3f(10,5,-10);
		glEnd();
		glAccum(GL_LOAD,0.7);
		glPushMatrix();
			for (int idx=1; idx<5; idx++){
				glRotatef(angle+0.1*(float)idx,0,0,1);
				glBegin(GL_QUADS);
					glVertex3f(-10,5,-10);
					glVertex3f(-10,5,10);
					glVertex3f(10,5,10);
					glVertex3f(10,5,-10);
				glEnd();
		glAccum(GL_ACCUM,0.1);		
			}
		
		glAccum(GL_RETURN,1.0);
		angle+=0.1;
		glPopAttrib();
		glPopMatrix();
		*/
}

void LeftWall::doStep(){
	
}
