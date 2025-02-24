#ifndef __PLANETA_H
#define __PLANETA_H

#include "soleng.h"

class Planeta : public SceneObject{
	public:
		Planeta(Vector3d pos, double m, double r, char *tname);
		void draw();
		void doStep() {}
		
		Vector3d pos;
		Vector3d a;
		double avalue;
		double mass;	
		Vector3d v;
	protected:
		CTexture tex;
		double r;
		GLUquadricObj * sphere;	
		GLuint listID;		
		double rot;
};

#endif
