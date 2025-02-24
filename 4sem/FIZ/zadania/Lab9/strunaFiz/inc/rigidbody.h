#ifndef __RIGID_BODY_H
#define __RIGID_BODY_H

#include "matrix3.h"
#include "vector3d.h"
#include "quaternion.h"

class RigidBody{
public:
	Vector3d 	x;				// x(t)
	Quaternion 	q;
	Vector3d	P;				// ped P=Mv
	Vector3d	L;				// moment pedu L=I omega
		
	Matrix3		Ibody;		// tensor momentu bezwladnosci
	Matrix3		Ibodyinv, Iinv;	// Ibody^-1, I^-1
		
	Matrix3		R, Rdot;
		
	Vector3d	v;				// predkosc liniowa
	Vector3d	omega;			// predkosc katowa
		
	Vector3d	force;
	Vector3d	mforce;			// moment sily M=r x F
		
	double 		mass;				// masa
public:
	RigidBody();
	void doStep(double dt);
private:
	void calcAuxiliary();
	void computeForceAndTorque();
	void dydt();
	
};

#endif

