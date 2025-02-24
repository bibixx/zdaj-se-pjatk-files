#ifndef __PLATFORM_H
#define __PLATFORM_H
#include "soleng.h"
#include "inc/meshObject.h"

class Platform : public SceneObject{
public:
	Platform();
	void draw();
	void doStep();
	bool getCastsShadows() const {return true;}
	void keyboard(int key);
private:
	CTexture txt;
	int drawplatform;
	MeshObject *model;
};

#endif

