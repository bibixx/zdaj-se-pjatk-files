#ifndef __SUN_H
#define __SUN_H

#include "planeta.h"

class Sun : public Planeta{
	public:
		Sun(Vector3d pos, double m, double r, char *tname);
		void draw();
	private:
		GLUquadricObj * sphere1;	
};


#endif
