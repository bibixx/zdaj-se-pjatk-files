#ifndef __PLATE_H
#define __PLATE_H

#include "inc/SceneObject.h"
#include "inc/world.h"

class Plate : public SceneObject{
public:
	Plate();
	void draw();
	void doStep();
	void setColor(double c);
	void keyboard( int key);
	
private:
	float width;
	float height;
	int draw3D;
	int wireframe;
	float deltax;
	float deltay;
	
	int divx;
	int divy;
	#define NX 26
	
	double grid[NX][NX];
	
	double m[(NX-2)*(NX-2)][(NX-2)*(NX-2)];
	double mat[(NX-2)*(NX-2)][(NX-2)*(NX-2)];
	double b[(NX-2)*(NX-2)];
	double v[6];
	
	void test();
};

#endif
