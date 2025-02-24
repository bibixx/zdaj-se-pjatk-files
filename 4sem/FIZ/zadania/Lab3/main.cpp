#include <stdio.h>
#include <math.h>
#include <stdlib.h>

#include <GL\glut.h>
#include <math.h>
#include <time.h>
#include "struktury.h"

#define YS 1.0f
#define PI 3.1415


double rot=0.0f;
int solver=0;

//--------------------------------------------------------------
//
//	do rozwiazania uklad rownan 
//	{dr/dt=v
//  {d2r/dt2=F/m
//
//	Solvery:
//		solveEuler
//		solveMidpoint
//		solveRK4
//
//	podobnie jak w pendulum
//--------------------------------------------------------------

void derivatives(Wektor *in, Wektor *out, Punkt *p){
	// dr/dt =v
	// d2r/dt2=F/m
	out[0]=in[1];
	out[1]=p->f*(1.0/p->m);
}

/*void calcForce(Punk *p, Wektor v){
	//p->f=p->m*g-
}*/

void solveEuler(Punkt  *p,  float dt){
	//f*(1.0/p->m)->dzielenie wektora
    // p->v=p->v+p->f*(1.0/p->m)*dt;
    // p->r=p->r+p->v*dt;
	 
	Wektor stan_poczatkowy[2];
	Wektor stan_aktualny[2];
	
	
	stan_poczatkowy[0]=p->r;
	stan_poczatkowy[1]=p->v;
	
	derivatives(stan_poczatkowy, stan_aktualny, p);
	
	
	p->r=p->r+stan_aktualny[0]*dt;
	p->v=p->v+stan_aktualny[1]*dt;
	 
}

void solveMidPoint(Punkt *p, float dt){
	//r,v,f ->Wektor
	Wektor k1[2],k2[2];
	Wektor y_k[2];
	Wektor y_h[2];
	Wektor yout[2];
	
	y_h[0]=p->r;
	y_h[1]=p->v;
	derivatives(y_h,yout,p);
	
	k1[1]=yout[1];
	k1[0]=yout[0];
	y_k[0]=y_h[0]+k1[0]*0.5*dt;
	y_k[1]=y_h[1]+k1[1]*0.5*dt;
	derivatives(y_k,yout,p);
	k2[1]=yout[1];
	k2[0]=yout[0];
	p->r=p->r+k2[0]*dt;
	p->v=p->v+k2[1]*dt;	
}

void solveRK4(Punkt *p, float dt){
	//r,v,f ->Wektor
	Wektor k1[2],k2[2],k3[2],k4[2];
	Wektor y_k[2];
	Wektor y_h[2];
	Wektor y_a[2];
	Wektor y_b[2];
	Wektor yout[2];
	
	y_h[0]=p->r;
	y_h[1]=p->v;
	derivatives(y_h,yout,p);
	
	k1[1]=yout[1];
	k1[0]=yout[0];
	y_k[0]=y_h[0]+k1[0]*0.5*dt;
	y_k[1]=y_h[1]+k1[1]*0.5*dt;
	derivatives(y_k,yout,p);
	k2[1]=yout[1];
	k2[0]=yout[0];
	y_a[0]=y_k[0]+k2[0]*0.5*dt;
	y_a[1]=y_k[1]+k2[1]*0.5*dt;
	derivatives(y_a,yout,p);
	k3[1]=yout[1];
	k3[0]=yout[0];
	y_b[0]=y_a[0]+k3[0]*dt;
	y_b[1]=y_a[0]+k3[0]*dt;
	derivatives(y_b,yout,p);
	p->r=p->r+y_b[0]+yout[0]*dt;
	p->v=p->v+y_b[0]+yout[1]*dt;	
}


//--------------------------------------------------------------
// RESZTA KODU
//--------------------------------------------------------------




struct SferaN{
       Wektor r1;
       float r;
       float t;
       float color[3];
       SferaN *right;
};



Wektor g(0,-1.0,0);
 Punkt *root=NULL;
 SferaN *sroot=NULL;
 int loop=0;
SferaN *SphereAlloc(float R, Wektor r1, float t, float c[3])
{
       SferaN *tmp;
       if (!(tmp=(SferaN*)malloc(sizeof(SferaN))))
          return NULL;

        tmp->right=NULL;
        tmp->r=R;
        tmp->r1=r1;
        tmp->t=t;
	tmp->color[0]=c[0];
	tmp->color[1]=c[1];
	tmp->color[2]=c[2];

        return tmp;
}

void SphereTest(SferaN *s, Punkt *p)
{
 float d;
 Wektor n;
 float z;
 d=(s->r1-p->r).len();
 if (d-s->r<0)
 {
    n=(s->r1-p->r);
    n.norm();
    z=d-s->r;
    p->r=p->r+n*z;

    Wektor vs,vn;
    vn=n*(n*p->v);
    vs=p->v-vn;
    p->v=(vs-vn*s->t);
 }


}

void AddSphere(SferaN *ro, float R, Wektor r1, float t, float c[3])
{
     SferaN *tmp;
     for (tmp=ro; tmp->right!=NULL; tmp=tmp->right);

        tmp->right=SphereAlloc(R,r1,t,c);
}


Punkt *PointAlloc(float m, int flaga, Wektor r, Wektor v)
{
      Punkt *tmp;
      if (!(tmp=(Punkt*)malloc(sizeof(Punkt))))
         return NULL;

      tmp->m=m;
      tmp->flag=flaga;
      tmp->r=r;
      tmp->v=v;
      tmp->right=NULL;
      return tmp;
}

void AddPoint(Punkt *ro, float m, int flag, Wektor r, Wektor v)
{
     Punkt *tmp;
     for (tmp=ro; tmp->right!=NULL; tmp=tmp->right);

        tmp->right=PointAlloc(m,flag,r,v);
}

Wektor W_Euler(Wektor f, float h)
{
       return (f*h);
}

void calcDeriv(Punkt *p){
	
}

void Solver(Punkt *ro, float dt)
{
	//0 euler, 1 midpoint, 2 RK4
     Punkt *tmp;
     for (tmp=ro;tmp!=NULL;tmp=tmp->right)
     {
		 switch (solver){
		 case 0:
			 solveEuler(tmp,dt);
			 break;
		 case 1:
			 solveMidPoint(tmp,dt);
			 break;
		 case 2:
			 solveRK4(tmp,dt);
			 break;
		default:
			solveEuler(tmp,dt);
		 }
	/*
     tmp->dv=W_Euler(tmp->f*(1/tmp->m),dt);
     tmp->v=tmp->v+tmp->dv;

     tmp->dr=tmp->v*dt;
     tmp->r=tmp->r+tmp->dr;
	*/
	
		/*
	Wektor a=tmp->f*(1.0/tmp->m);		 
		 //--------------

	Wektor k1=W_Euler(a,dt);
	Wektor k2=W_Euler(a+0.5*k1,dt);
	Wektor k3=W_Euler(a+0.5*k2,dt);
	Wektor k4=W_Euler(a+k3,dt);
	Wektor kk=(1.0/6.0)*(k1+2.0*k2+2.0*k3+k4);
	tmp->v=tmp->v+kk;
	tmp->r=tmp->r+tmp->v*dt;
	*/
	
     }
}

void Sily(Punkt *ro)
{
     Punkt *tmp;

     for (tmp=ro;tmp!=NULL; tmp=tmp->right)
     {
     	tmp->f=g*tmp->m;
     }
}




void AnimateScene(void)
{
         glClearColor(0.0f,0.0f,0.0f,0.0f);

     //if (loop==0)
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        Punkt *tmp;
        //Sily(root);
        Solver(root,0.008);	
        Sily(root);
	//	glRotatef(0.1,0.0f,1.0f,0.0f);

rot+=0.01;
//if (rot>36) rot=0.0f;
        glPointSize(5);
  glDisable(GL_LIGHTING); 
  glDisable(GL_LIGHT0); 
        for(tmp=root;tmp!=NULL;tmp=tmp->right)
        {
        //glBegin(GL_POINTS);

               //glColor3f(rand()/(float)RAND_MAX,rand()/(float)RAND_MAX,rand()/(float)RAND_MAX);
               glColor3f(1,1,1);
              // glVertex3f(tmp->r.x/1.0,tmp->r.y/1.0,tmp->r.z/1.0);
            
            glPushMatrix();
            glTranslatef(tmp->r.x/1.0,tmp->r.y/1.0,tmp->r.z/1.0);
            glutSolidSphere(0.011,5,5);
            glPopMatrix();
               SferaN *stmp;

               for(stmp=sroot;stmp!=NULL;stmp=stmp->right)
               {

                  SphereTest(stmp,tmp);
               }
               //glVertex2f(0,0);
               //printf("%f %f\n",tmp->r.x, tmp->r.y);
       // glEnd();

        glBegin(GL_LINES);

                glColor3f(1,0,0);
                double x1,x2,y1,y2,z1,z2;
                x1=tmp->r.x;
                y1=tmp->r.y;
                x2=tmp->v.x;
                y2=tmp->v.y;
		z1=tmp->r.z;
		z2=tmp->v.z;


                glVertex3f(x1,y1,z1);
                glVertex3f(x1+(x2)*0.05,y1+(y2)*0.05,z1+(z2)*0.05);
        glEnd();

        }
  glEnable(GL_LIGHTING); 
  glEnable(GL_LIGHT0);         
        SferaN *stmp;
        for(stmp=sroot;stmp!=NULL;stmp=stmp->right)
        {
         glPushMatrix();
         glTranslatef(stmp->r1.x,stmp->r1.y,stmp->r1.z);
	glColor3fv(stmp->color);

         glutSolidSphere(stmp->r,50,50);
         glPopMatrix();
        }


      //  printf("****\n");

        glFlush();
        glutSwapBuffers();
        loop++;


        if (loop>2000)
        {
           double vx,vy,vz;
 double zz=0.01;

           for(tmp=root;tmp!=NULL;tmp=tmp->right)
           {
 	    vx=0.5-rand()/(float)RAND_MAX;
 	    vy=1.0-rand()/(float)RAND_MAX;
 	    vz=0.5-rand()/(float)RAND_MAX;
		vz=0.0f;
 	    vy*=YS;
            vy+=zz;
            zz+=0.001;
            tmp->r.x=0;
            tmp->r.y=0;
            tmp->v=Wektor(vx,vy,vz);
           }
        	loop=0;
        }
}

void InitGraphics()
{
  GLfloat mat_specular[] = { 1.0, 1.0, 1.0, 1.0 }; 
  GLfloat mat_shininess[] = { 90.0 }; 
  GLfloat light_position[] = {1.0, 1.0, 1.0, 0.0}; 

  glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular); 
  glMaterialfv(GL_FRONT, GL_SHININESS, mat_shininess); 
  glLightfv(GL_LIGHT0, GL_POSITION, light_position); 
  glEnable(GL_LIGHTING); 
  glEnable(GL_LIGHT0); 
  glDepthFunc(GL_LEQUAL); 
  glEnable(GL_DEPTH_TEST); 


        glShadeModel(GL_SMOOTH);




	glEnable(GL_COLOR_MATERIAL);


}


void myReshape(GLsizei w, GLsizei h) 
{ 
  glViewport(0, 0, w, h); 
  glMatrixMode(GL_PROJECTION); 
  glLoadIdentity(); 

	double p1=1.0f;

  if(w <= h) 
     glOrtho(-p1,p1,-p1*(GLfloat)h/(GLfloat)w,p1*(GLfloat)h/(GLfloat)w,-10.0,10.0); 
  else 
     glOrtho(-p1*(GLfloat)w/(GLfloat)h,p1*(GLfloat)w/(GLfloat)h,-p1,p1,-10.0,10.0); 

  glMatrixMode(GL_MODELVIEW); 
  glLoadIdentity(); 
} 
void keyboard(unsigned char key, int x, int y){
	switch (key){
	case 'e':
		solver=0;
		printf("Solver --> Euler\n");
		break;
	case 'm':
		solver=1;
		printf("Solver --> MidPoint\n");
		break;
	case 'r':
		solver=2;
		printf("Solver --> RK4\n");
		break;
		
	}
}
void idle(void)
{
        glutPostRedisplay();
}

int main(int argc, char *argv[])
{
 double vx,vy,vz;
 int i;
 srand(time(NULL));

 double zz=0.01;

 for (i=1; i<620; i++)
 {
        vx=0.5-rand()/(float)RAND_MAX;
        vy=1.0-rand()/(float)RAND_MAX;
        vz=0.5-rand()/(float)RAND_MAX;
	vz=0.0f;

        vy*=YS;
        vy+=zz;
        zz+=0.001;

 	if (!root)
 	   root=PointAlloc(1,0,Wektor(0,0,0),Wektor(vx,vy,vz));
 	else
 	    AddPoint(root,1,0,Wektor(0,0,0),Wektor(vx,vy,vz));
 }
 zz=-0.2;

 for (i=1; i<140; i++)
 {

       AddPoint(root,10,0,Wektor(zz,1,0),Wektor(0,0,0));
       zz+=0.01;
 }

	float c1[] = {0,0,1};
	float c2[] = {0,1,0};
	float c3[] = {1,1,0};
	float c4[] = {1,0,1};
	float c5[] = {0.6,0.5,1};
	float c6[] = {0.6,0.5,0.3};

  sroot=SphereAlloc(0.5,Wektor(0,-0.5,0),0.9,c1);
  AddSphere(sroot,0.1,Wektor(-0.5,0.5,0),0.8,c2);
  AddSphere(sroot,0.1,Wektor(0.5,0.5,0),0.3,c3);
  AddSphere(sroot,0.1,Wektor(1,0.0),0.3,c4);
  AddSphere(sroot,0.15,Wektor(0,0.7,0),0.9,c5);
  AddSphere(sroot,0.2,Wektor(-1,-0.2,0),1.0,c6);

        Sily(root);

        glutInit(&argc, argv);
        glutInitWindowSize(600,600);
        glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
        glutCreateWindow("GLUT example");

         

        InitGraphics();

        glutDisplayFunc(AnimateScene);
        glutIdleFunc(idle);
	glutKeyboardFunc(keyboard);
	glutReshapeFunc(myReshape); 
        glutMainLoop();

return 0;
}
