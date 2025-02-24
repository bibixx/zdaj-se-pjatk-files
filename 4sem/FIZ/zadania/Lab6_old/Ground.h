#ifndef __GROUND_H
#define __GROUND_H

#include "inc/SceneObject.h"

class Ground : public SceneObject{
public:
	Ground();
	void draw();
	void doStep();
	
private:
	CTexture txt;	
	int listid;
};

#endif
