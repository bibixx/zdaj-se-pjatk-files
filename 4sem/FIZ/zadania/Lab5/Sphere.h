#ifndef __SPHERE_H
#define __SPHERE_H
#include "inc/SceneObject.h"
#include "inc/texture.h"
#include "inc/vector3d.h"
#include <GL/glu.h>

class Sphere : public SceneObject{
public:
	Sphere(Vector3d pos, double r, char *fname);
	void draw();
	bool getCastsShadows() const {return true;}
	void doStep() {}
private:
	CTexture tex;
	Vector3d pos;
	double r;
	GLUquadricObj * sphere;
	double yy;
};


#endif
