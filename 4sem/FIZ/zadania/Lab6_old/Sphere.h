#ifndef __SPHERE_H
#define __SPHERE_H
#include "Body.h"

class Sphere : public Body{
public:
	Sphere(double m, double r, Vector3d pos);
	void draw();
private:
	GLUquadricObj *sphere;
};


#endif
