#include "Sphere.h"

Sphere::Sphere(Vector3d pos, double r, char *fname):SceneObject(){
	this->pos=pos;
	this->r=r;
	tex.LoadBMPFile(fname);
	sphere=gluNewQuadric();
	
	gluQuadricDrawStyle(sphere, GLU_FILL);
	gluQuadricNormals(sphere, GL_SMOOTH);
	gluQuadricOrientation(sphere, GLU_OUTSIDE);
	gluQuadricTexture(sphere, GL_TRUE);

	shadow=0;
	yy=1;
}

void Sphere::draw(){
	//static double yy=1;
	//glPushMatrix();
	
	
	if (!shadow){
		glColor3f(0.5,0.5,0.5);
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, tex.ID);
		
		glDisable(GL_COLOR_MATERIAL);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glDisable(GL_BLEND);
	
		
		//glEnable(GL_DEPTH_TEST);
	}
	else{
		glDisable(GL_LIGHTING);
		//glDisable(GL_DEPTH_TEST);
		
	}
		glPushMatrix();
						glRotatef(90,1,0,0);
				glRotatef(yy,0,0,1);
				yy+=0.1;
				
			glTranslatef(this->pos.x,this->pos.y,this->pos.z);	

			gluSphere(sphere, this->r,40,40);
		glPopMatrix();
	
	glDisable(GL_TEXTURE_2D);
	glEnable(GL_COLOR_MATERIAL);
	
	//glPopMatrix();
}
