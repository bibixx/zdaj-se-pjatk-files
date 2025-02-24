#include "RightWall.h"

RightWall::RightWall():SceneObject(){
	txt.LoadBMPFile("dat/brick.bmp");
	
}

void RightWall::draw(){
		
		glBindTexture(GL_TEXTURE_2D, txt.ID);
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(26,-5,-26);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(26,-5,26);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(26,20,26);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(26,20,-26);
		glEnd();
		glDisable(GL_TEXTURE_2D);
}

void RightWall::doStep(){
	
}

