#ifndef __RIGID_BODY_H
#define __RIGID_BODY_H

#include "soleng.h"

class RigidBody{
public:
	RigidBody();
	RigidBody(RigidBody const &b);
	
	float 		mass;				// masa bryly
	float 		inertia;			// moment bezwladnosci w lokalnym ukladzie (zwiazanym z bryla)
	float 		inertiaInverse;		// odwrotnosc momentu bezwladnosci
	Vector3d 	position;			// polozenie ciala w ukladzie swiata
	Vector3d	velocity;			// predkosc w ukladzie swiata
	Vector3d	velocityBody;		// predkosc w ukladzie bryly
	Vector3d	angularVelocity;	// predkosc katowa w ukladzie ciala
	float 		speed;				// wartosc wektora predkosci
	float		orientation;		// kierunek
	Vector3d	forces;				// calkowita sila dzialajaca na cialo
	Vector3d	moment;				// calkowity moment sily dzialajacy na cialo (tylko wzgledem osi obrotu 2D)
	
	Vector3d	CD;					// polozenie srodka ciezkosci
	Vector3d	CT;					// polozenie srodka ciagu wzgledem srodka ciezkosci
	Vector3d	CPT;				// polozenie lewego silnika wzgledem srodka ciezkosci
	Vector3d	CST;				// polozenie prawego silnika
	
	float 		projectedArea;		// powierzchcnia sredniego przekroju poprzecznego
	float		thrustForce;		// wielkosc sily ciagu
	
	Vector3d	pThrust;
	Vector3d	sThrust;
	
	float		width;
	float		length;
	
	void Initialize();
	void CalcLoads();
	
	void solveEuler();
	void solveMidPoint();
	void solveRK4();
};

#endif
