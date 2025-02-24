#ifndef __SHADOW_PLANE_H
#define __SHADOW_PLANE_H
#include "vector3d.h"

class ShadowPlane{
public:
	ShadowPlane(Vector3d p, Vector3d n);
	Vector3d getPlanePoint() {return this->p;};
	Vector3d getNormal() {return this->n;}
protected:
	Vector3d p;
	Vector3d n;
};

#endif
