#ifndef __PENDULUM_H
#define __PENDULUM_H
#include "soleng.h"

class Pendulum : public SceneObject{
public:
	Pendulum();
	void draw();	
	void doStep();
	bool getCastsShadows() const {return true;}
	void keyboard(int key);
private:
	void solveEuler(double  dt);
	void solveMidPoint(double dt);
	void solveRK4(double dt);
	double calcPoptencialEnergy();
	double calcKineticEnergy();
	
	void derivatives(double* in, double* out);
private:
	CTexture txt;
	double alpha, omega, mass, radius;
	Vector3d r;
	double ox[10];
	double oy[10];
	int counter;
	int motion_blur;
	GLUquadric *c1;
	Graph2D *gr;
	
	int solver;		// 0 - euler. 1 - midpoint. 2 - RK4
	int drawvector;
	double g;
};

#endif

