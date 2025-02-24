#include "RightWall.h"

RightWall::RightWall():SceneObject(){
	txt.LoadBMPFile("dat/brick.bmp");
	txt1.LoadBMPFile("dat/door.bmp");
	isShadowPlane = 1;
	
}

void RightWall::draw(){
		glDisable(GL_BLEND);
		glBindTexture(GL_TEXTURE_2D, txt.ID);
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(26,-5,0);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(26,-5,26);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(26,15,26);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(26,15,0);
		glEnd();
		
		glBindTexture(GL_TEXTURE_2D, txt1.ID);
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(26,-5,-10);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(26,-5,0);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(26,15,0);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(26,15,-10);
		glEnd();

		glBindTexture(GL_TEXTURE_2D, txt.ID);
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(26,-5,-26);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(26,-5,-10);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(26,15,-10);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(26,15,-26);
		glEnd();


		
		glDisable(GL_TEXTURE_2D);
}

void RightWall::doStep(){
	
}

