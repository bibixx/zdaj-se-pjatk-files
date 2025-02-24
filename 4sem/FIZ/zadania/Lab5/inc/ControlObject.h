#ifndef __CONTROL_OBJECT_H
#define __CONTROL_OBJECT_H
#include <windows.h>
#include <GL/gl.h>
#include "SceneObject.h"

class ControlObject{
public:
	ControlObject(SceneObject *owner);
	virtual ~ControlObject() {};
	virtual void draw()=0;
	SceneObject *getOwner() {return owner;}
	void setOwner(SceneObject *obj){ owner=obj;}
	void moveX(int x) {this->x=this->x+x;};
	void moveTo(int x, int y) {this->x=x; this->y=y;}
	void resizeTo(int w, int h) {this->w=w; this->h=h;}
	int getWidth() {return this->w;}
	int getHeight() {return this->h;}
	int hitTest(int x, int y);
	int getX() {return this->x;}
	int getY() {return this->y;}
	int getIsMouseOver() {return this->isMouseOver;}
	void setIsMouseOver(int b) { this->isMouseOver=b;}
	void setIsMouseDown(int b) {this->isMouseDown=b;}
	int getIsMouseDown() {return this->isMouseDown;}
	int getState() {return this->state;}
protected:
		SceneObject *owner;
		int id;
		int x,y,w,h;
		int isMouseOver;
		int isMouseDown;
		int state;
};

#endif
