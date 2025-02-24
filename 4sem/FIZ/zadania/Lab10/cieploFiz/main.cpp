#include "soleng.h"

#include "Plate.h"


int main(int argc, char *argv[]){
	CWorld *world;
	srand(0);
	
	world = CWorld::getWorldInstance(argc, argv);
	//world->setFrameDelay(40);
	
	world->sceneManager->addSceneObject(new Plate());

	
	Light *light1;
	light1 = new Light();
	GLfloat pos1[]={70,70,70,1};
	GLfloat amb[] = {0.3,0.3,0.3,0.0};
	light1->setLightPos(pos1);
	light1->setAmbientLight(amb);
	world->sceneManager->addLight(light1);	
	world->mainLoop();
	return 0;
}


