#ifndef __LEFT_WALL_H
#define __LRFT_WALL_H
#include "inc/SceneObject.h"

class LeftWall : public SceneObject{
public:
	LeftWall();
	void draw();
	void doStep();
	
private:
	CTexture txt;
};

#endif

