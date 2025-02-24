#ifndef __RECTANGLE_H
#define __RECTANGLE_H

#include "inc/SceneObject.h"

class Rectanglem : public SceneObject{
public:
	Rectanglem();
	void draw();
	void doStep(){}
	bool getCastsShadows() const {return true;}
};

#endif

