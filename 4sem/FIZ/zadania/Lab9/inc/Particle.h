#ifndef __PARTICLE_H
#define __PARTICLE_H

#include "SceneObject.h"
#include "vector3d.h"

class Particle : public SceneObject{
public:
	Particle(float m, Vector3d pos, Vector3d v);
	Particle(float m, Vector3d pos);
	void draw();
	void doStep();
	bool getCastsShadows(void) const { return true; }
private:
	Vector3d pos;
	Vector3d v;
	Vector3d f;
	
	Vector3d dr;
	Vector3d dv;
	
	double r;
	double m;
	int flag;
	
	float c[3];
	GLUquadricObj * sphere;
};

#endif
