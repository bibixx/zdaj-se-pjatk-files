#ifndef __MOON_H
#define __MOON_H

#include "planeta.h"

class Moon : public Planeta{
	public:
		Moon(Vector3d pos, double m, double r, char *tname, Planeta *p, double f=15.0);
		void draw();
	private:
		GLUquadricObj * sphere1;	
		Planeta *planeta;
		double f;
};


#endif
