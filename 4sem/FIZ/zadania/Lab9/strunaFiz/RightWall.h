#ifndef __RIGHT_WALL_H
#define __RIGHT_WALL_H
#include "inc/SceneObject.h"

class RightWall : public SceneObject{
public:
	RightWall();
	void draw();
	void doStep();
	
	
private:
	CTexture txt;
	CTexture txt1;
};

#endif

