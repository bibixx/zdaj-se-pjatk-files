#include "Plate.h"
#include "param.h"

float ll=-20.0;


Plate::Plate():SceneObject(){
	isShadowPlane=1;
	txt.LoadBMPFile("dat/wood.bmp");
}

void Plate::draw(){
	glEnable(GL_DEPTH_TEST);
	
	glPushMatrix();
	
	Vector3d v1(-15,HH,-15);
	Vector3d v2(-15,HH,15);
	Vector3d v3(19,-10,15);
	
	Vector3d ww=(v2-v1)%(v2-v3);
	//double zz=10.0/tan((M_PI/7.2));	
	//double zz=10.0/tan(Alfa);	
	double zz=(-1.0*(-5-HH))/tan(Alfa);
	ww.norm();
	#define LENG_PLAf 15.0
		glBindTexture(GL_TEXTURE_2D, txt.ID);
		glEnable(GL_TEXTURE_2D);	
	glBegin(GL_QUADS);						// Draw A Quad
		//glVertex3f(-LENG_PLAf, LENG_PLAf, 0.0f);				// Top Left
		//glVertex3f( LENG_PLAf, LENG_PLAf, 0.0f);				// Top Right
		//glVertex3f( LENG_PLAf,-LENG_PLAf, 0.0f);				// Bottom Right
		//glVertex3f(-LENG_PLAf,-LENG_PLAf, 0.0f);				// Bottom Left
	glColor3f(1,1,1);
		glNormal3f(-ww.x,-ww.y,-ww.z);
		
		/*
		glTexCoord2f(0.0f, 0.0f);
		glVertex3f(-15,5,-15);
		
		glTexCoord2f(1.0f, 0.0f);
		glVertex3f(-15,5,15);
		
		glTexCoord2f(1.0f, 1.0f);
		glVertex3f(19,-10,15);
		
		glTexCoord2f(0.0f, 1.0f);
		glVertex3f(19,-10,-15);
		*/
		
		glTexCoord2f(0.0f, 0.0f);
		glVertex3f(ll,HH,-15);
		
		glTexCoord2f(1.0f, 0.0f);
		glVertex3f(ll,HH,15);
		
		glTexCoord2f(1.0f, 1.0f);
		glVertex3f(ll+zz,-5,15);
		
		glTexCoord2f(0.0f, 1.0f);
		glVertex3f(ll+zz,-5,-15);
		
		
		//
		glTexCoord2f(0.0f, 0.0f);
		glVertex3f(ll,HH,-15);
		
		glTexCoord2f(1.0f, 0.0f);
		glVertex3f(ll,-5,-15);
		
		glTexCoord2f(1.0f, 1.0f);
		glVertex3f(ll,-5,15);
		
		glTexCoord2f(0.0f, 1.0f);
		glVertex3f(ll,HH,15);
		
		
		
		//
		glTexCoord2f(0.0f, 0.0f);
		glVertex3f(-26,HH,-15);
		
		glTexCoord2f(1.0f, 0.0f);
		glVertex3f(-26,HH,15);
		
		glTexCoord2f(1.0f, 1.0f);
		glVertex3f(ll,HH,15);
		
		glTexCoord2f(0.0f, 1.0f);
		glVertex3f(ll,HH,-15);		
		
		
		
		//
		glTexCoord2f(0.0f, 0.0f);
		glVertex3f(-26,HH,-15);
		
		glTexCoord2f(1.0f, 0.0f);
		glVertex3f(-26,-5,-15);
		
		glTexCoord2f(1.0f, 1.0f);
		glVertex3f(ll,-5,-15);
		
		glTexCoord2f(0.0f, 1.0f);
		glVertex3f(ll,HH,-15);		
		
	//
		glTexCoord2f(0.0f, 0.0f);
		glVertex3f(-26,HH,15);
		
		glTexCoord2f(1.0f, 0.0f);
		glVertex3f(-26,-5,15);
		
		glTexCoord2f(1.0f, 1.0f);
		glVertex3f(ll,-5,15);
		
		glTexCoord2f(0.0f, 1.0f);
		glVertex3f(ll,HH,15);		
				
		
	glEnd();							// Done Drawing The Quad
	
	
	//glColor3f(0.1,0.3,.2);
	glBegin(GL_TRIANGLES);
	glNormal3f(0,0,-1);
	
	glTexCoord2f(0.0f, 0.0f);
		glVertex3f(ll,HH,15);
		
	glTexCoord2f(1.0f, 0.0f);	
		glVertex3f(ll,-5,15);
		
	glTexCoord2f(1.0f, 1.0f);	
		glVertex3f(ll+zz,-5,15);
		
		glNormal3f(0,0,-1);
		
		glTexCoord2f(0.0f, 0.0f);
		glVertex3f(ll,HH,-15);
		
		glTexCoord2f(1.0f, 0.0f);	
		glVertex3f(ll,-5,-15);
		
		glTexCoord2f(1.0f, 1.0f);	
		glVertex3f(ll+zz,-5,-15);
		
	glEnd();
	glDisable(GL_TEXTURE_2D);
	glPopMatrix();	
}

void Plate::doStep(){
	
}
