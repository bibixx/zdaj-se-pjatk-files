#ifndef __PENDULUM_H
#define __PENDULUM_H
#include "soleng.h"
#define NPoint 100
class Struna : public SceneObject{
public:
	Struna();
	void draw();	
	void doStep();
	void keyboard(int key);
	bool getCastsShadows() const {return true;}
private:
	// struna
	void solveAnalitic(float  dt);
	void solveEuler(float  dt);
private:
	CTexture txt;
	Vector3d r;
	char info[1024];
	float ox[10];
	float oy[10];
	
	int counter;
	int motion_blur;
	GLUquadric *c1;
	Graph2D *gr;
	
	int solver;		// 0 - euler. 1 - midpoint. 2 - RK4
	int drawvector;
	double g;
	
	
	//Struna
	float Amp[NPoint];  // warto�ci stablicowane 
	float Vel[NPoint];  // warto�ci stablicowane 
	float A[10];		// amplitudy kolejnych sk�adowych
	float Fi[10];		// Przesuni�cia k�towe sk�adowych 
	float t;			// czas
	float L;		// d�ugo�� struny
	float V;			// pr�dko�� rozchodzenia ski� fali
	float Ek;			// energia kinetyczna
	float Ep;			// energia pot�cjalna
	float Ro;			// liniowa g�sto�� masy struny	
	float T0;			// si�a naci�gu		
	float dt;
};

#endif

