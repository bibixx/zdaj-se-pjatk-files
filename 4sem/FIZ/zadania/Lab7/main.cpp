#include "soleng.h"
#include "hover.h"
#include "ground.h"
#include "universe.h"


int main(int argc, char *argv[]){
	CWorld *world;
	srand(0);
	Light *light1;
	HoverCraft *h;
	DisplayControl *dc;
	world = CWorld::getWorldInstance(argc, argv);
	world->setDynamicCamera(1);
	world->sceneManager->addSceneObject(new Ground());
	h = new HoverCraft();
	world->sceneManager->addSceneObject(h);
	dc = new DisplayControl(h,300,5,30,100,1);
	world->sceneManager->addSceneObject(new Universe());

	
	
	world->sceneManager->addShadowPlane(new ShadowPlane( Vector3d( 0.0, -4.90, 0.0 ), Vector3d( 0.0,  -1.0, 0.0) ) );
	world->sceneManager->addControlObject(dc);
	
//	world->sceneManager->addSceneObject(new Tester());
	
	light1 = new Light();
	GLfloat pos1[]={500,550,500,1};
	GLfloat amb[] = {0.6,0.5,0.5,0.0};
	light1->setLightPos(pos1);
	light1->setAmbientLight(amb);
	world->sceneManager->addLight(light1);
	world->showAxis(false);
	
	world->mainLoop();
	return 0;
}


