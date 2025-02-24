#include "soleng.h"
#include "universe.h"
#include "UkladPlanet.h"



int main(int argc, char *argv[]){
	CWorld *world;
	Light *light1;
		
	world = CWorld::getWorldInstance(argc, argv);
	world->getKamera()->MoveForward( 300.0 );
	world->setViewAngle(50.0);
	world->showAxis(false);
	world->setFrameDelay(1);

	
	light1 = new Light();
	GLfloat pos1[]={0,0,0,1};
	GLfloat amb[] = {0.1,0.1,0.1,1.0};
	light1->setLightPos(pos1);
	light1->setAmbientLight(amb);
	world->sceneManager->addLight(light1);
	
	UkladPlanet *u=new UkladPlanet();
	world->sceneManager->addSceneObject(u);
	world->sceneManager->addSceneObject(new Universe());	
		
	world->sceneManager->addControlObject(new DisplayControl(u,600,38,300,300,1));
	
	world->mainLoop();
	return 0;
}


