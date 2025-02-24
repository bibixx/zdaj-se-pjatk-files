#ifndef __QUATERNION_H
#define __QUATERNION_H

#include "vector3d.h"

class Quaternion{
public:
	float		n;	// number (scalar) part
	Vector3d	v;	// vector part: v.x, v.y, v.z

	Quaternion(void);
	Quaternion(float e0, float e1, float e2, float e3);

	float		Magnitude(void);
	Vector3d	GetVector(void);
	float		GetScalar(void);
	Quaternion	operator+=(Quaternion q);
	Quaternion	operator-=(Quaternion q);
	Quaternion 	operator*=(float s);
	Quaternion 	operator/=(float s);
	Quaternion	operator~(void) const { return Quaternion(n, -v.x, -v.y, -v.z);}	
};

	Quaternion 	operator+(Quaternion q1, Quaternion q2);
	Quaternion 	operator-(Quaternion q1, Quaternion q2);
	Quaternion 	operator*(Quaternion q1, Quaternion q2);
	Quaternion 	operator*(Quaternion q, float s);
	Quaternion 	operator*(float s, Quaternion q);
	Quaternion 	operator*(Quaternion q, Vector3d v);
	Quaternion 	operator*(Vector3d v, Quaternion q);
	Quaternion 	operator/(Quaternion q, float s);
	float 		QGetAngle(Quaternion q);
	Vector3d 	QGetAxis(Quaternion q);
	Quaternion 	QRotate(Quaternion q1, Quaternion q2);
	Vector3d	QVRotate(Quaternion q, Vector3d v);
	Quaternion	MakeQFromEulerAngles(float x, float y, float z);
	Vector3d	MakeEulerAnglesFromQ(Quaternion q);

#endif
