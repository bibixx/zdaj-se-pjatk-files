#ifndef __WORLD_H
#define __WORLD_H

#include "glutwnd.h"
#include "kamera.h"
#include "SceneManager.h"
#include "ParticleManager.h"
#include "ParticleManager.h"
#include "HelpInfo.h"

#include <iostream>
#include <sstream>

#include "configDlg.h"

extern Vector3d camPos;

enum PROJECTION_MODE{
	PERSPECTIVE_PROJECTION=0,
	ORTOGRAPHIC_PROJECTION=1
};

class CWorld : public CGlutWnd{
    public:
		static CWorld *getWorldInstance(int &argc, char *argv[]);
		static CWorld *getWorld(){return pInstance;}
		~CWorld();
        static void display();
        static void idle();
        static void simulationStep(float dt);
        static void reshape(GLsizei w, GLsizei h);
        static void specialkey(int key, int x, int y);
        static void keyboard(unsigned char key, int x, int y);
        static void processMouse(int button, int state, int x, int y);
        static void processMouseActiveMotion(int x, int y);
        static void processMousePassiveMotion(int x, int y);
        
        static void setOrthographicProjection();
		static void setPerspectiveProjection();
		static SceneManager *sceneManager;
		static HelpInfo *helpInfo;
		static ParticleManager *particleManager;
		static void mainLoop();
		static int font;
		static void renderBitmapString(float x, float y, void *font,const char *string);
		static void ShadowProjection(float * l, float * e, float * n);
		static int getW() {return m_w;}
		static int getH() {return m_h;}
		static void setDefaultProjectionMode(PROJECTION_MODE pm);
		
		static void enableLights();
		static void disableLights();
		
		static void showAxis(int show) {bOsie=show;}
		
		static CKamera *getKamera() {return kamera1;}
		
		static double getDt() {return dt;}
		static void setDynamicCamera(int b) {dynamicCamera=b;}
		static void setFrameDelay(int fd) {frameDelay = fd;}
    private:
        CWorld(int &argc, char *argv[], ConfigParam cparam);
        
		static CWorld *pInstance;
		
        static void kula(float r, int g);

		// tutaj prywatne metody do rysowania np
        static Vector3d dira;
        
        //static void renderBitmapString(float x, float y, void *font,char *string);
        

        static int frame;
        static int time1;
        static int timebase;
        //static int font;            
        static CKamera *kamera1;
        
        static int m_x;
        static int m_y;
		static int m_cx;
		static int m_cy;
        static int m_state;
        static float rotY;
        static float rotZ;
        
		static int useStencilShadows;
		
        static int m_w;
        static int m_h;
        
        static int bOsie;
        static int camidx;
		
		static ControlObject *focusedobj;
		
		static int defaultProjectionMode;
		
		static double dt;
		
		static int dynamicCamera;
		static int frameDelay;
		
};




#endif
