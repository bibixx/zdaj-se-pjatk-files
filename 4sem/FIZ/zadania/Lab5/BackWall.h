#ifndef __BACK_WALL_H
#define __BACK_WALL_H
#include "inc/SceneObject.h"

class BackWall : public SceneObject{
public:
	BackWall();
	void draw();
	void doStep();
	
private:
	CTexture txt;
};

#endif

