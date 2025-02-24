#ifndef __SCENE_MANAGER_H
#define __SCENE_MANAGER_H

#include "SceneObject.h"
#include "ControlObject.h"
#include "ShadowPlane.h"
#include "ParticleManager.h"
#include "Light.h"

#include <vector>
using namespace std;

typedef vector<SceneObject*>sceneObjectList;
typedef sceneObjectList::iterator sceneObjectListIterator;

typedef vector<ControlObject*>controlObjectList;
typedef controlObjectList::iterator controlObjectListIterator;

typedef vector<ShadowPlane*>shadowPlanesList;
typedef shadowPlanesList::iterator shadowPlanesListIterator;

typedef vector<Light*>lightObjectList;
typedef lightObjectList::iterator lightListIterator;


class SceneManager{
public:
	static SceneManager *getSceneManagerInstance();
	void addSceneObject(SceneObject *obj);
	void addControlObject(ControlObject *ctrl);
	void addShadowPlane(ShadowPlane *splane);
	void addLight(Light *light);
	void drawControls();
	void drawObjects();	
	void drawShadows(int blend);
	void drawShadowPlanes();
	void doStep();
	size_t getObjectsCount() {return objList.size();}
	int getShadowObjectsCount();
	ControlObject *getObject(int n) {return ctrlList[n];}
	ControlObject *doHitTest(int x, int y);
	void disableLights();
	void enableLights();
	void pumpKeyboardEvent(int key);
	void pumpSpecialKeyboardEvent(int key, int x, int y);
	void drawStencilShadowsScene();
	void drawScene();
private:
	
	SceneManager();
	void drawObjectsShadow();	
	sceneObjectList objList;
	controlObjectList ctrlList;
	shadowPlanesList shadowList;
	lightObjectList lightList;
	static SceneManager *pInstance;
};

#endif
