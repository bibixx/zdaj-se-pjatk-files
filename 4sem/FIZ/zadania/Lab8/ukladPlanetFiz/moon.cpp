#include "moon.h"

Moon::Moon(Vector3d pos, double m, double r, char *tname, Planeta *p,double f):Planeta(pos,m,r,tname){
	this->planeta = p;
	this->f=f;
}

void Moon::draw(){
	glPushMatrix();
		glRotatef(90,1,0,0);
		Vector3d p=pos;
		double factor=1495978.87;
		
		p=p*(1.0/factor);
		Vector3d rr=this->pos-this->planeta->pos;		
		rr.norm();
		rr=rr*f;
		p=p+rr;			
		glTranslatef(p.x, p.y, p.z);
		
		glRotatef(-rot++,0,0,1);
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, tex.ID);	
		
		glCallList(listID);
		glDisable(GL_TEXTURE_2D);
	glPopMatrix();	
}
