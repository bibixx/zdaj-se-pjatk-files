#ifndef __SCENE_OBJECT_H
#define __SCENE_OBJECT_H
#include "GL/gl.h"
#include "texture.h"
#include "vector3d.h"

class SceneObject{
public:
	SceneObject();
	virtual ~SceneObject() {};
	virtual void draw()=0;
	virtual void drawShadow() {shadow=1; draw(); shadow=0;}
	virtual void doStep() {};
	virtual void keyboard(int /*key*/) {};
	virtual void specialkey(int /*key*/, int /*x*/, int /*y*/) {}
	
	virtual void drawControl(int /*id*/, int /*w*/, int /*h*/) {}
	// klasa dziedziczaca musi przedefiniowac jesli ma rzucac cien
	virtual bool getCastsShadows(void) const { return false; }	
	int isShadowPlane;
protected:
	int shadow;
	
};

#endif
