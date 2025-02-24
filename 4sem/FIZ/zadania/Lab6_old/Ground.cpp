#include "Ground.h"
#define XX 26
Ground::Ground():SceneObject(){
	//txt.LoadBMPFile("wall2.bmp");
	txt.LoadBMPFile("dat/grass.bmp");
	isShadowPlane=1;
	glNewList(listid=glGenLists(1), GL_COMPILE);
		glBindTexture(GL_TEXTURE_2D, txt.ID);
		glEnable(GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		glPushMatrix();
	
		glColor3f(1,2,1);
		double xs=-XX;
		double ys=-XX;
		double delt=8.0;
		glBegin(GL_QUADS);
			glNormal3f(0.0,1.0,0.0);
			for (int j=0; j<(XX*2.0)/delt; j++){
				for (int i=0; i<(26*2.0)/delt; i++){		
					glTexCoord2f(0.0f, 0.0f); glVertex3f(xs,-5,ys);
					glTexCoord2f(1.0f, 0.0f); glVertex3f(xs,-5, ys+delt);
					glTexCoord2f(1.0f, 1.0f); glVertex3f(xs+delt,-5,ys+delt);
					glTexCoord2f(0.0f, 1.0f); glVertex3f(xs+delt,-5,ys);
					xs+=delt;
				}
				xs=-XX;
				ys+=delt;
			}
		glEnd();
	glEndList();
}

void Ground::draw(){
	glDisable(GL_BLEND);
	glCallList(listid);
}

void Ground::doStep(){
	
}
