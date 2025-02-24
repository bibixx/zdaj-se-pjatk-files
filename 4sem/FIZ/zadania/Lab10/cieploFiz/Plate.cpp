#include "Plate.h"
#include <iostream>

//---------------------------------------------------------------------------------
// napisac procedure ktora na weksciu dostanie macierz kwadratowa
// rozmiwatu nmat x nmat
// przekazywana w parametrze a
// b - wektor wymuszen
// x - wektor wynikowy
//---------------------------------------------------------------------------------
void solve_gauss_elim(int nmat, double a[][(NX-2)*(NX-2)], double b[],  double x[])
{
 int n = nmat;
 double tmp, sum;
 for (int k=0;k<n-1;k++)
 {
   if ( fabs(a[k][k])>=1.e-6)
   {
     for (int i=k+1;i<n;i++)
     {
       tmp = a[i][k]/a[k][k];
       for (int j=k+1;j<n;j++) a[i][j] = a[i][j] -a[k][j]*tmp;
         b[i] = b[i] - b[k]*tmp;
     }
   }
   else
   {
     cout << "Pivot=0 in line:" << k << endl;
     exit(1);
   }
 }

 b[n-1]=b[n-1]/a[n-1][n-1];
 for ( int i = n-2; i >= 0; i--)
 {
   sum = b[i];
     for (int j = i+1; j < n; j++)
       sum = sum - a[i][j]*b[j];
   b[i] = sum/a[i][i];
 }
 
 for(int w = 0; w < n; w++)
 {
     x[w] = b[w];
 }

}
//=======================================================================
//  RESZTA KODU
//=======================================================================














Plate::Plate():SceneObject(){
	// grid ->4 ^5
	//
	// 04 14 24 34
	// 03 13 23 33
	// 02 12 22 32
	// 01 11 21 31
	// 00 10 20 30
	std::cout << "----------------------" << std::endl;
	std::cout << "OBLICZENIA" << std::endl;
	width=20.0;
	height=20.0;
	draw3D=0;
	divx=NX;
	divy=NX;	
	test();

	double x[(NX-2)*(NX-2)];
	
	solve_gauss_elim((NX-2)*(NX-2),mat,b,x);
	
	//for (int i=0; i<(NX-2)*(NX-2); i++){
	//	std::cout << x[i] << std::endl;
	//}
	int ii=0;
	for (int i=1; i<=divy-2; i++)
		for (int j=1;j<=divx-2; j++)
		{
			grid[j][i]=x[ii++];
		}
		
		
	std::cout << "OK" << std::endl;
	
	CWorld::helpInfo->addHelp("d - widok 3d (on/off)");
	CWorld::helpInfo->addHelp("s - siatka (on/off)");


}



#define S 0.8
void Plate::draw(){
	/*
	glPushMatrix();
	glDisable(GL_LIGHTING);
	

	glPopMatrix();
	return;
	*/
	glPushMatrix();
	glDisable(GL_LIGHTING);
	
	if (wireframe)
		glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);
	else
		glPolygonMode(GL_FRONT_AND_BACK,GL_FILL);
	//
	// i , j ->    i+1, j
	//    ^           |
	// i , j+1 <- i+1, j+1
	glTranslatef(-(13.0*S),-(13.0*S),0);
	//glShadeModel(GL_FLAT);
	
	for (int j=0; j<divy-1; j++){
		for (int i=0; i<divx-1; i++){
			glBegin(GL_QUADS);
				//setColor(i,j);
				setColor(grid[i][j]);
				if (draw3D){
					glVertex3f(i*S,j*S,grid[i][j]/30.0);
					setColor(grid[i+1][j]);
					glVertex3f((i+1)*S,j*S,grid[i+1][j]/30.0);
				
					setColor(grid[i+1][j+1]);
					glVertex3f((i+1)*S,(j+1)*S,grid[i+1][j+1]/30.0);
				
					setColor(grid[i][j+1]);
					glVertex3f(i*S,(j+1)*S,grid[i][j+1]/30.0);
				}
				else{
					glVertex2f(i*S,j*S);
					setColor(grid[i+1][j]);
					glVertex2f((i+1)*S,j*S);
				
					setColor(grid[i+1][j+1]);
					glVertex2f((i+1)*S,(j+1)*S);
				
					setColor(grid[i][j+1]);
					glVertex2f(i*S,(j+1)*S);
					
				}
			glEnd();
		}
	}

	glPopMatrix();	
}

void Plate::keyboard( int key){
	if (key=='d') draw3D=!draw3D;
	if (key=='s') wireframe=!wireframe;
}



void Plate::doStep(){
	
}



void Plate::test(){


	for (int i=0; i<divy; i++){
		grid[0][i]=100.0;
		grid[divx-1][i]=50.0;
	}
	
	for (int i=1; i<divx-1; i++){
		grid[i][divy-1]=200.0;
		grid[i][0]=150.0;
	}
	
	
	double rs=0.0;
	int idx=0;
	
	//std::cout << "-------------" << std::endl;
	for (int c=0; c< (NX-2)*(NX-2); c++)
		for (int b=0; b<(NX-2)*(NX-2); b++)
			mat[b][c]=0.0;
	for (int j=1; j<divy-1; j++){
		for (int i=1; i<divx-1; i++){
			rs=0.0;
			
			if ( (i-1)==0)
				rs=rs+grid[i-1][j];
			else
				mat[idx][(i-1)+(j-1)*(divx-2)-1]=1.0;
			if ( (i+1)==divx-1)
				rs=rs+grid[i+1][j];
			else
				mat[idx][i+(j-1)*(divx-2)]=1.0;
			if ( (j-1)==0)
				rs=rs+grid[i][j-1];
			else
				mat[idx][(i-1)+(j-1)*(divx-2)-(divx-2)]=1.0;
			if ((j+1)==divy-1)
				rs=rs+grid[i][j+1];
			else
				mat[idx][(i-1)+(j)*(divx-2)]=1.0;
			
			mat[idx][idx]=-4.0;
			
			
			b[idx]=-rs;
			//std::cout << b[idx] << std::endl;
			
			//for (int a=0; a<((divx-2)*(divy-2)); a++)
			//	std::cout << mat[idx][a] << " ";
			//std::cout << std::endl;
			idx++;
		}
		
		
	}
	//std::cout << "-------------" << std::endl;
}

void Plate::setColor(double c)
{
	double r,g,b;
	double r1,g1,b1;
	double r2,g2,b2;

	//c=c+1.0;	// zakres (-1,1) -> (0,2)
	//c=c+200;
	
	c=c/100.0;
	

	double factor;

	
	

	//return;

	if (c<0.3)
	{
		r1=1;	// fiolet
		g1=0;
		b1=1;
		
		r2=0;
		g2=0;
		b2=1;	// niebieski

		factor=c/0.3;
	}
	else
	if (c<0.6)
	{
		r1=0;	// niebieski
		g1=0;
		b1=1;

		r2=0;
		g2=1;
		b2=1;	// blekitny

		factor=(c-0.3)/(0.3);
		//factor=pow(factor,3.0);
	}
	else
	if (c<0.9)
	{
		r1=0;	// blekitny
		g1=1;
		b1=1;

		r2=0.8;	// czarny
		g2=1;
		b2=1;
		
		factor=(c-0.6)/(0.3);
		//factor=pow(factor,2.0);
		
	}
	else
	if (c<1.2)
	{
		r1=0.8;	// czarny
		g1=1;
		b1=1;

		r2=0;	// zielony
		g2=1;//0.7;
		b2=0;
		factor=(c-0.9)/(0.2);

	}
	else
	if (c<1.4)
	{
		r1=0;	// zielony
		g1=1;//0.7;
		b1=0;

		r2=1;	// zolty
		g2=1;
		b2=0;
		factor=(c-1.2)/(0.2);
		
		//factor=pow(factor,3.0);
	}
	else
	{
		r1=1;
		g1=1;
		b1=0;

		r2=1;
		g2=0;
		b2=0;

		factor=(c-1.4)/(0.6);
	}


	r = r1 +  ((r2 - r1) * factor);
	g = g1 +  ((g2 - g1) * factor);
	b = b1 +  ((b2 - b1) * factor);

	if ((2.000001>=c)&& (1.999999<=c))
	{
	//	r=g=b=0;
	}


	glColor3f(r,g,b);
}
