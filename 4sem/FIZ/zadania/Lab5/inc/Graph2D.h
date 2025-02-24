#ifndef __GRAPH2D_H
#define __GRAPH2D_H

#include "SceneObject.h"
#include "ControlObject.h"
#include "DisplayControl.h"

#include <vector>

using namespace std;
class Serie{
public:
	Serie();
private:
	float rgb[3];
	vector<double> values;
	int count;
};

class Graph2D : public SceneObject{
public:
	Graph2D(int x, int y, int w, int h);
	void draw();
	void drawControl(int id, int w, int h);
	void setXRange(float minx, float maxx) {this->minx = minx; this->maxx = maxx;}
	void addValues(const double *val, int l, float st);
	void addNextPoint(double value, int serie);
	void setMaxLength(int l, float st);
	void addDynamicSerie();
private:
	ControlObject *graph;
	int x,y,w,h;
	float minx, maxx;
	float miny, maxy;
	
	double *values[3];
	vector<Serie*> a;
	int len;
	int count;
	float xstep;
	int pcount[3];
	int maxlen;
};

#endif
