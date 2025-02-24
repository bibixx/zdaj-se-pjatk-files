/////////////////////////////////////////////////////////////////////////////
// Author:      Piotr Tronczyk
// Modified by:
// Copyright:   Piotr Tronczyk
//
// Last Modyfication: 06.01.2004
//
// File : wektor.h
/////////////////////////////////////////////////////////////////////////////


#ifndef __VECTOR3D_H
#define __VECTOR3D_H
#include <math.h>
#include <ostream>



using namespace std;


class Vector3d
{
public:
	double x,y,z;
	Vector3d(double _x=0, double _y=0, double _z=0):
	x(_x),y(_y),z(_z){}
	Vector3d(const Vector3d &vec) : x(vec.x), y(vec.y), z(vec.z) {}

	Vector3d operator+(Vector3d const &);
	Vector3d operator-(Vector3d const &);

	// podstawienie
	inline const Vector3d &operator=(const Vector3d &vec)
	{
	   x = vec.x;
	   y = vec.y;
       z = vec.z;
       
       return *this;	   
	}
	
	// iloczyn skalarny
	double operator*(Vector3d const &);
	
	// wektor razy skalar
	Vector3d operator*(double);
	Vector3d operator/(double);
	
	// iloczyn wektorowy
	Vector3d operator%(Vector3d const&);

	// dlugosc wektora
	double len();
	
	// normalizacja wektora
	void norm();		
};

/////////////////////////////////////////////////////////////////////////////
/*  skalar razy wektor
    poza klasa bo jest tgo operator typu double a nie klasy Vector3d*/
    
Vector3d operator*(double, Vector3d const &);

/////////////////////////////////////////////////////////////////////////////
/* operator << umozliwia konstrukcje cout << wektor. */

ostream &operator<<(ostream  &os,Vector3d  const &w);


inline double Vector3d::len() 
{
       return sqrt((*this)*(*this));
}

#endif


/////////////////////////////////////////////////////////////////////////////
// EOF
/////////////////////////////////////////////////////////////////////////////

