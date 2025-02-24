#ifndef __BODY_H
#define __BODY_H

#include "inc/SceneObject.h"

class Body : public SceneObject{
public:
	Body();
	//void draw();
	void doStep();
	
	// solvery
	
	void solveEuler();
	void solveMidPoint();
	void solveRK4();
	void setI(double I) { this->I = I;}
	double I;
	double r;
	double m;
	
	Vector3d pos;	
	Vector3d v;
	double omega;
	double alpha;
			
private:
	CTexture txt;
	double rot;
};


#endif
