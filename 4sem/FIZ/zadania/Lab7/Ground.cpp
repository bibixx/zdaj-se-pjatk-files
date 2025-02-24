#include "Ground.h"


Ground::Ground():SceneObject(){
	//txt.LoadBMPFile("wall2.bmp");
	//txt.LoadBMPFile("grass.bmp");
	//txt.LoadBMPFile("ground.bmp");
	txt.LoadBMPFile("dat/a.bmp");
	isShadowPlane = 1;
	
		double siz=512.0*1.5;
		glColor3f(1,2,1);
		double xs=-siz;
		double ys=-siz;
		double delt=32.0;
	
	/*
			for (int j=0; j<(siz*2.0)/delt; j++){
				for (int i=0; i<(512.0*2.0)/delt; i++){		
					//glTexCoord2f(0.0f, 0.0f); glVertex3f(xs,-5,ys);
					//glTexCoord2f(1.0f, 0.0f); glVertex3f(xs,-5, ys+delt);
					//glTexCoord2f(1.0f, 1.0f); glVertex3f(xs+delt,-5,ys+delt);
					//glTexCoord2f(0.0f, 1.0f); glVertex3f(xs+delt,-5,ys);
					map[i][j][0]=xs;
					map[i][j][1]=-5;
					map[i][j][2]=ys;
					xs+=delt;
				}
				xs=-siz;
				ys+=delt;
			}
	*/
		
		/*
	glNewList(listid=glGenLists(1), GL_COMPILE);
		glBindTexture(GL_TEXTURE_2D, txt.ID);
		//glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);
		glEnable(GL_TEXTURE_2D);
		glColor3f(1,2,1);
		glDisable(GL_BLEND);
		glPushMatrix();
		
			glNormal3f(0.0,1.0,0.0);
			for (int j=0; j<32-1; j++){
				glBegin(GL_TRIANGLE_STRIP);
				
				//glTexCoord2f(0.0f, 0.0f);
				//glVertex3f(xs,-5,ys);
				//glTexCoord2f(1.0f, 0.0f); 
				//glVertex3f(xs,-5, ys+delt);
				for (int i=0; i<32-1; i++){		
					//glTexCoord2f(0.0f, 0.0f); 
						
					//glTexCoord2f(1.0f, 0.0f); 
						
					//glTexCoord2f(1.0f, 1.0f); 
						//glVertex3f(xs+delt,-5,ys+delt);
					//glTexCoord2f(0.0f, 1.0f); 
						//glVertex3f(xs+delt,-5,ys);
					glTexCoord2f(0.0f, 0.0f);
					glVertex3f(xs,-5,ys);
					
					glTexCoord2f(1.0f, 0.0f);
					glVertex3f(xs+delt,-5,ys);
					
					glTexCoord2f(0.0f, 1.0f);
					glVertex3f(xs,-5,ys+delt);
					
					glTexCoord2f(1.0f, 1.0f);
					glVertex3f(xs+delt,-5,ys+delt);
					ys+=delt;
				}
				ys=-siz;
				xs+=delt;
				glEnd();
			}
		
		glPopMatrix();
		glDisable(GL_TEXTURE_2D);
	glEndList();
		*/		
		
		
	
	glNewList(listid=glGenLists(1), GL_COMPILE);
		glBindTexture(GL_TEXTURE_2D, txt.ID);
		glEnable(GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		glPushMatrix();
		glColor3f(1,1,1);
		glBegin(GL_QUADS);
			glNormal3f(0.0,1.0,0.0);
			for (int j=0; j<(siz*2.0)/delt; j++){
				for (int i=0; i<(siz*2.0)/delt; i++){		
					glTexCoord2f(0.0f, 0.0f); 
						glVertex3f(xs,-5,ys);
					glTexCoord2f(1.0f, 0.0f); 
					glVertex3f(xs,-5, ys+delt);
					glTexCoord2f(1.0f, 1.0f); 
						glVertex3f(xs+delt,-5,ys+delt);
					glTexCoord2f(0.0f, 1.0f); 
						glVertex3f(xs+delt,-5,ys);
					xs+=delt;
				}
				xs=-siz;
				ys+=delt;
			}
		glEnd();
		glPopMatrix();
		glDisable(GL_TEXTURE_2D);
	glEndList();


return;


			GLuint idxA[32*32*6];
	float texC[32*32][2];	
	
	float vertA[32*32][3];
	int cV;
	int i,j;
			for (j=0; j<32; j++){
				for (i=0; i<32; i++){		
				/*
					glTexCoord2f(0.0f, 0.0f);
					glVertex3f(xs,-5,ys);
					
					glTexCoord2f(1.0f, 0.0f);
					glVertex3f(xs+delt,-5,ys);
					
					glTexCoord2f(0.0f, 1.0f);
					glVertex3f(xs,-5,ys+delt);
					
					glTexCoord2f(1.0f, 1.0f);
					glVertex3f(xs+delt,-5,ys+delt);
					*/
					//vertA[j][i][0]=xs;
					//vertA[j][i][1]=-5;
					//vertA[j][i][2]=ys;
					vertA[i+j*32][0]=xs;
					vertA[i+j*32][1]=-5;
					vertA[i+j*32][2]=ys;
					ys+=delt;
					cV=j*32+i;
texC[cV][0]=i;
texC[cV][1]=j;
				}
				ys=-siz;
				xs+=delt;	

			}
	int idx=0;
			for (j=0; j<32-1; j++){
				for (i=0; i<32; i++){	
							cV=j*32+i;
							idxA[idx++]=cV+32;
							idxA[idx++]=cV;
							//idxA[idx++]=cV;
							//idxA[idx++]=cV+1;
							//idxA[idx++]=cV+32;
							//idxA[idx++]=cV+1+32;
							
				}
			}
			
//glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);	
glNewList(listid=glGenLists(1), GL_COMPILE);
glPushMatrix();
		glBindTexture(GL_TEXTURE_2D, txt.ID);
		//glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);
		glEnable(GL_TEXTURE_2D);	
	glEnableClientState(GL_VERTEX_ARRAY);
	glEnableClientState(GL_TEXTURE_COORD_ARRAY);
	//glEnableClientState(GL_COLOR_ARRAY);
glVertexPointer(3,GL_FLOAT,0,vertA);			
glTexCoordPointer(2,GL_FLOAT,0,texC);

	for (i=0; i<32-1; i++){
		
			glDrawElements(GL_TRIANGLE_STRIP,32*2,GL_UNSIGNED_INT,&idxA[i*32*2]);
	}	

glPopMatrix();			
glEndList();	
		/*
	glNewList(listid=glGenLists(1), GL_COMPILE);
		glBindTexture(GL_TEXTURE_2D, txt.ID);
		//glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);
		glEnable(GL_TEXTURE_2D);
		glColor3f(1,2,1);
		glDisable(GL_BLEND);
		glPushMatrix();
		
			glNormal3f(0.0,1.0,0.0);
			for (int j=0; j<32-1; j++){
				glBegin(GL_TRIANGLE_STRIP);
				
				//glTexCoord2f(0.0f, 0.0f);
				//glVertex3f(xs,-5,ys);
				//glTexCoord2f(1.0f, 0.0f); 
				//glVertex3f(xs,-5, ys+delt);
				for (int i=0; i<32-1; i++){		
					//glTexCoord2f(0.0f, 0.0f); 
						
					//glTexCoord2f(1.0f, 0.0f); 
						
					//glTexCoord2f(1.0f, 1.0f); 
						//glVertex3f(xs+delt,-5,ys+delt);
					//glTexCoord2f(0.0f, 1.0f); 
						//glVertex3f(xs+delt,-5,ys);
					glTexCoord2f(0.0f, 0.0f);
					glVertex3f(xs,-5,ys);
					
					glTexCoord2f(1.0f, 0.0f);
					glVertex3f(xs+delt,-5,ys);
					
					glTexCoord2f(0.0f, 1.0f);
					glVertex3f(xs,-5,ys+delt);
					
					glTexCoord2f(1.0f, 1.0f);
					glVertex3f(xs+delt,-5,ys+delt);
					ys+=delt;
				}
				ys=-siz;
				xs+=delt;
				glEnd();
			}
		
		glPopMatrix();
		glDisable(GL_TEXTURE_2D);
	glEndList();
		*/
}

void Ground::draw(){
	glDisable(GL_BLEND);
	glPushMatrix();
	//glTranslatef(camPos.x,0,camPos.z);
	glCallList(listid);
	glPopMatrix();

	
}

void Ground::doStep(){
	
}
