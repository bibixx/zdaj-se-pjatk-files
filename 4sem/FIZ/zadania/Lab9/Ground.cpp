#include "Ground.h"

Ground::Ground():SceneObject(){
	//txt.LoadBMPFile("wall2.bmp");
	//txt.LoadBMPFile("grass.bmp");
	txt.LoadBMPFile("dat/ground.bmp");
	isShadowPlane = 1;
	glNewList(listid=glGenLists(1), GL_COMPILE);
		glBindTexture(GL_TEXTURE_2D, txt.ID);
		glEnable(GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		glPushMatrix();
	
		glColor3f(1,2,1);
		double xs=-26;
		double ys=-26;
		double delt=4.0;
		glBegin(GL_QUADS);
			glNormal3f(0.0,1.0,0.0);
			for (int j=0; j<(26*2.0)/delt; j++){
				for (int i=0; i<(26*2.0)/delt; i++){		
					glTexCoord2f(0.0f, 0.0f); glVertex3f(xs,-5,ys);
					glTexCoord2f(1.0f, 0.0f); glVertex3f(xs,-5, ys+delt);
					glTexCoord2f(1.0f, 1.0f); glVertex3f(xs+delt,-5,ys+delt);
					glTexCoord2f(0.0f, 1.0f); glVertex3f(xs+delt,-5,ys);
					xs+=delt;
				}
				xs=-26;
				ys+=delt;
			}
		glEnd();
		glPopMatrix();
		glDisable(GL_TEXTURE_2D);
	glEndList();
}

void Ground::draw(){
	glDisable(GL_BLEND);
	//GLfloat pos1[]={0,0,0,1};
	//glLightfv(GL_LIGHT0,GL_POSITION,pos1);
	glCallList(listid);
	glDisable(GL_BLEND);
}

void Ground::doStep(){
	
}
