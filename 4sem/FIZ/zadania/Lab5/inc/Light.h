#ifndef __LIGHT_H
#define __LIGHT_H

#include <GL/gl.h>
#include "vector3d.h"



class Light{
public:
	Light();
	~Light();
	GLfloat getRed() {return this->r;}
	GLfloat getGreen() {return this->g;}
	GLfloat getBlue() {return this->b;}
	GLfloat getAlpha() {return this->a;}
	GLfloat *getColor();
	int getLightId() {return this->id;}
	void setLightId(int id) {this->id = id;}
	void setRed( GLfloat r ) {this->r = r;}
	void setGteen( GLfloat g ) {this->g = g;}
	void setBlue( GLfloat b ) {this->b = b;}
	void setAlpha( GLfloat a ) {this->a = a;}
	GLfloat *getLightPos() {return this->pos;}
	void setLightPos(GLfloat  pos[4]); 
	
	void setAmbientLight(GLfloat ambient[4]);
	GLfloat *getAmbientLight() { return this->ambientLight;}
	
private:
	GLfloat r;		// red
	GLfloat g;		// green
	GLfloat b;		// blue
	GLfloat a;		// alpha
	GLfloat c[4];
	int id;
	GLfloat pos[4];
	
	GLfloat ambientLight[4];
	GLfloat diffuseLight[4];
	GLfloat specularLight[4];
};

#endif
