#ifndef __DISPLAY_CONTROL_H
#define __DISPLAY_CONTROL_H
#include "GL/gl.h"
#include "ControlObject.h"

class DisplayControl : public ControlObject{
public:
	DisplayControl(SceneObject *owner,int x, int y, int w, int h, int id);	
	void draw();	
};

#endif
