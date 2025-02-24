#include "Body.h"

void Body::solveEuler(){
}

void Body::solveMidPoint(){
}

void Body::solveRK4(){
}



Body::Body():SceneObject(){
	m = 0;
	r = 0;
	I = 0;
	pos  = Vector3d(0,0,0);
	v = Vector3d(0,0,0);
	omega = 0;
	alpha = 0;
	rot = 0;
	
}

void Body::doStep(){
		solveEuler();
}
