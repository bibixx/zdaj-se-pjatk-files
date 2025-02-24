#ifndef __GLUTWND_H
#define __GLUTWND_H

#include <GL\glut.h>

#include "configDlg.h"

class CGlutWnd{
    public:
        CGlutWnd(int &argc, char *argv[], ConfigParam cparam);
        ~CGlutWnd();
        static void enterMainLoop();
		void showGLInfo();
};


#endif
