#include "soleng.h"

#include "BackWall.h"
#include "LeftWall.h"
#include "RightWall.h"
#include "Ground.h"
#include "Pendulum.h"
#include "universe.h"
#include "platform.h"


int main(int argc, char *argv[]){
	CWorld *world;
	Pendulum *p;
	Light *light1;
	srand(0);
	
	world = CWorld::getWorldInstance(argc, argv);
	
	//world->getKamera()->MoveForward( -22.0 );
	p=new Pendulum();
	world->setFrameDelay(30);
	
	world->sceneManager->addSceneObject(new BackWall());
	world->sceneManager->addSceneObject(new LeftWall());
	world->sceneManager->addSceneObject(new RightWall());
	world->sceneManager->addSceneObject(new Ground());
	
	world->sceneManager->addSceneObject(p);
	world->sceneManager->addSceneObject(new Universe());
	world->sceneManager->addSceneObject(new Platform());
	
	
	
	
	
	world->sceneManager->addShadowPlane(new ShadowPlane( Vector3d( 0.0, -4.95, 0.0 ), Vector3d( 0.0,  -1.0, 0.0) ) );
	world->sceneManager->addShadowPlane(new ShadowPlane( Vector3d( 0,0,-25.95 ), Vector3d( 0.0,  0.0, -1.0) ) );
	world->sceneManager->addShadowPlane(new ShadowPlane( Vector3d( -25.95,0,0 ), Vector3d( -1,0,0) ) );
	world->sceneManager->addShadowPlane(new ShadowPlane( Vector3d( 25.95,0,0 ), Vector3d( 1,0,0) ) );

	
	light1 = new Light();
	//GLfloat pos1[]={5,7,20,1};
	GLfloat pos1[]={50,55,50,0};
	GLfloat amb1[] = {0.8,0.8,0.8,0};
	light1->setLightPos(pos1);
	light1->setAmbientLight(amb1);	
	world->sceneManager->addLight(light1);
	
	/*
	glLightModelfv(GL_LIGHT_MODEL_AMBIENT,amb1);
	glEnable(GL_COLOR_MATERIAL);
	glColorMaterial(GL_FRONT,GL_AMBIENT_AND_DIFFUSE);
	GLfloat spec[]={1.0, 1.0 ,1.0 ,1.0};  
	glMaterialfv(GL_FRONT,GL_SPECULAR,spec);
	
	float df=100.0;

	
	glMaterialfv(GL_FRONT_AND_BACK,GL_SHININESS,&df);	
	glEnable (GL_AUTO_NORMAL);
*/
	
	world->mainLoop();
	return 0;
}


