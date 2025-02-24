#ifndef __PLATE_H
#define __PLATE_H

#include "inc/SceneObject.h"

class Plate : public SceneObject{
public:
	Plate();
	void draw();
	void doStep();
	
private:

	int listid;
	CTexture txt;
};

#endif
