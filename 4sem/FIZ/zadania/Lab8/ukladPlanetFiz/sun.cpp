#include "sun.h"

Sun::Sun(Vector3d pos, double m, double r, char *tname):Planeta(pos,m,r,tname){
	sphere1=gluNewQuadric();	
	gluQuadricDrawStyle(sphere1, GLU_FILL);
	gluQuadricNormals(sphere1, GL_SMOOTH);
	gluQuadricOrientation(sphere1, GLU_OUTSIDE);
	gluQuadricTexture(sphere1, GL_TRUE);	
}

void Sun::draw(){
	GLfloat mat_emission[] = {1.0F,1.0F,0.0F,0.0F};
	GLfloat no_mat[] = { 0.0F,0.0F,0.0F,1.0F };	

	glMaterialfv(GL_FRONT,GL_EMISSION,mat_emission);	
	
	Planeta::draw();
/*
	glEnable(GL_DEPTH_TEST);
			glEnable(GL_BLEND);
			glColor4f(1,0,0,0.3);

			glDisable(GL_TEXTURE_2D);
			//glBlendFunc(GL_SRC_ALPHA,GL_ONE);
			//glBlendFunc(GL_ONE_MINUS_SRC_ALPHA,GL_SRC_ALPHA);
			glBlendFunc(GL_SRC_ALPHA,GL_ONE);
			

			glColor4f(1,1,1,0.1);
			
			double rc=1;
			double gc=1;
			double bc=1;
			glMaterialfv(GL_FRONT,GL_EMISSION,no_mat);
			glEnable(GL_COLOR_MATERIAL);
			//glDisable(GL_DEPTH_TEST);
			glDepthMask(GL_FALSE);
			GLfloat mat[]={1.0,1.0,1.0,0.05};
			int tes=20;
			//glMaterialfv(GL_FRONT,GL_EMISSION,mat);	
			static double r_cyc=0.0;
			double rr=this->r;			
			rr=rr+2;
			rr=rr+r_cyc;
			static int dir=1;
			r_cyc+=0.1*dir;
			if ((r_cyc>3) || (r_cyc<0)){
				//r_cyc=0;
				dir=-dir;
			}
			for (int i=0; i<24; i++){
				rr+=1.8;
				glColor4f(rc,gc,bc,0.05);
				
				gluSphere(sphere1, rr,tes,tes);
				//tes+=1;
				//mat[0]-=0.03;
				mat[1]-=0.03;
				mat[2]-=0.05;
		
				glMaterialfv(GL_FRONT,GL_EMISSION,mat);
				//rc-=0.01;
				gc-=0.07;
				bc-=0.1;
				if (gc<0)
					rc-=0.1;
			}
			glColor4f(1,1,1,1);	
			glDisable(GL_BLEND);
			glDepthMask(GL_TRUE);
	*/			
			
	glMaterialfv(GL_FRONT,GL_EMISSION,no_mat);	
	
}
