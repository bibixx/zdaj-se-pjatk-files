#include "LeftWall.h"

LeftWall::LeftWall():SceneObject(){
	txt.LoadBMPFile("dat/brick.bmp");
	
}

void LeftWall::draw(){
		
		glBindTexture(GL_TEXTURE_2D, txt.ID);
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		glNormal3f(1,0,0);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(-26,-5,-26);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(-26,-5,26);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(-26,10,26);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(-26,10,-26);
		glEnd();
		glDisable(GL_TEXTURE_2D);
}

void LeftWall::doStep(){
	
}
