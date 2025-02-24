#ifndef __UNIVERSE_H
#define __UNIVERSE_H
#include "inc/SceneObject.h"
#include "inc/texture.h"
#include "inc/vector3d.h"
#include <GL/glu.h>

class Universe : public SceneObject{
public:
	Universe();
	void draw();
	void doStep() {}
private:
	CTexture tex;
	double r;
	GLUquadricObj * sphere;
};


#endif
