#ifndef __SOLENG_H
#define __SOLENG_H


#include <windows.h>
#include <GL/gl.h>
#include <GL/glut.h>
#include <GL\glext.h>

#include "inc/world.h"
#include "inc/vector3d.h"
#include "inc/matrix3.h"
#include "inc/quaternion.h"
#include "inc/DisplayControl.h"
#include "inc/ShadowPlane.h"
#include "inc/Light.h"


//#include "inc/rigidbody.h"

#include "inc/Graph2D.h"

#define LIB_VERSION "0.1b"

extern PFNGLMULTITEXCOORD1FARBPROC		glMultiTexCoord1fARB;
extern PFNGLMULTITEXCOORD2FARBPROC		glMultiTexCoord2fARB;
extern PFNGLMULTITEXCOORD3FARBPROC		glMultiTexCoord3fARB;
extern PFNGLMULTITEXCOORD4FARBPROC		glMultiTexCoord4fARB;
extern PFNGLACTIVETEXTUREARBPROC		glActiveTextureARB;
extern PFNGLCLIENTACTIVETEXTUREARBPROC	glClientActiveTextureARB;	

#endif
