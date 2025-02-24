#include "BackWall.h"

BackWall::BackWall():SceneObject(){
	txt.LoadBMPFile("dat/brick.bmp");
	
}

void BackWall::draw(){
		
		glBindTexture(GL_TEXTURE_2D, txt.ID);
		glEnable(GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		glBegin(GL_QUADS);
			glNormal3f(0,0,-1);
			
			glColor3f(0.6,0.6,0.6);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(-26,6,-26);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(-26,-5,-26);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(26,-5,-26);
			
			glTexCoord2f(1.0f, 1.0f); glVertex3f(26,6,-26);

/*
			glColor3f(0.6,0.6,0.6);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(-26,20,-26);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(-26,-5,-26);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(0,-5,-26);
			
			glTexCoord2f(1.0f, 1.0f); glVertex3f(0,20,-26);

			glTexCoord2f(0.0f, 1.0f); glVertex3f(0,20,-26);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(0,-5,-26);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(26,-5,-26);
			
			glTexCoord2f(1.0f, 1.0f); glVertex3f(26,20,-26);
			*/
			
		glEnd();
		glDisable(GL_TEXTURE_2D);
}

void BackWall::doStep(){
	
}
