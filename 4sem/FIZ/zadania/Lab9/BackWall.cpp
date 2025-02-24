#include "BackWall.h"

BackWall::BackWall():SceneObject(){
	txt.LoadBMPFile("dat/wood.bmp");
	isShadowPlane = 1;
	//txt.LoadBMPFile("b.bmp");
	
}

void BackWall::draw(){
		glDisable(GL_BLEND);
		glBindTexture(GL_TEXTURE_2D, txt.ID);
		glEnable(GL_TEXTURE_2D);
		//glDisable(GL_BLEND);
		
		//glColor3f(1,2,1);
		glColor4f(1,1,1,0.9);
		//glEnable(GL_BLEND);
		double xs=-26;
		double ys=-5;
		double delt=4.0;
		double zz=-26;
		glBegin(GL_QUADS);
			glNormal3f(0.0,1.0,0.0);
			for (int j=0; j<(20*1.0)/delt; j++){
				for (int i=0; i<(26*2.0)/delt; i++){		
					glTexCoord2f(0.0f, 1.0f); glVertex3f(xs,ys,zz);
					glTexCoord2f(0.0f, 0.0f); glVertex3f(xs, ys+delt,zz);
					glTexCoord2f(1.0f, 0.0f); glVertex3f(xs+delt,ys+delt,zz);
					glTexCoord2f(1.0f, 1.0f); glVertex3f(xs+delt,ys,zz);
					xs+=delt;
				}
				xs=-26;
				ys+=delt;
			}
		glEnd();
		
		
		/*
		glBegin(GL_QUADS);
			glNormal3f(0,0,1);
			
			glColor3f(0.6,0.6,0.6);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(-26,20,-26);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(-26,-5,-26);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(26,-5,-26);
			
			glTexCoord2f(1.0f, 1.0f); glVertex3f(26,20,-26);
		*/

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
