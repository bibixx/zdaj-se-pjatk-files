#ifndef __KAMERA_H
#define __KAMERA_H

#include "vector3d.h"
#include <GL\glut.h>

class CKamera
{
public:
	CKamera(void);
	~CKamera(void);
public:
	Vector3d ViewDir;
	Vector3d RightVector;	
	Vector3d UpVector;
	Vector3d Position;

	GLfloat RotatedX, RotatedY, RotatedZ;	

	void Render ( void );
  void MoveTo(Vector3d pos);
	void Move ( Vector3d Direction );
	void RotateX ( GLfloat Angle );
	void RotateY ( GLfloat Angle );
	void RotateZ ( GLfloat Angle );

	void MoveForward ( GLfloat Distance );
	void MoveUpward ( GLfloat Distance );
	void StrafeRight ( GLfloat Distance );
	void StrafeLeft ( GLfloat Distance );


};

#endif
