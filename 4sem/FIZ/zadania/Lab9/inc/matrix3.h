#ifndef __MATRIX3_H
#define __MATRIX3_H
#include "vector3d.h"



class Matrix3{
public:
	float	e11, e12, e13, e21, e22, e23, e31, e32, e33;
	Matrix3(void);
	Matrix3(float r1c1, float r1c2, float r1c3, 
			float r2c1, float r2c2, float r2c3, 
			float r3c1, float r3c2, float r3c3 );

	float	det(void);
	Matrix3	Transpose(void);
	Matrix3	Inverse(void);	
	
	Matrix3& operator+=(Matrix3 m);
	Matrix3& operator-=(Matrix3 m);
	Matrix3& operator*=(float s);
	Matrix3& operator/=(float s);	
};

Matrix3 operator+(Matrix3 m1, Matrix3 m2);
Matrix3 operator-(Matrix3 m1, Matrix3 m2);
Matrix3 operator/(Matrix3 m, float s);
Matrix3 operator*(Matrix3 m1, Matrix3 m2);
Matrix3 operator*(Matrix3 m, float s);
Matrix3 operator*(float s, Matrix3 m);
Vector3d operator*(Matrix3 m, Vector3d u);
Vector3d operator*(Vector3d u, Matrix3 m);

#endif

