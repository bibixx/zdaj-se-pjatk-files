#include "soleng.h"

#include "BackWall.h"
#include "LeftWall.h"
#include "RightWall.h"
#include "Ground.h"
#include "inc/Particle.h"
#include "simulation.h"
#include "Plate.h"
#include "param.h"

//#pragma comment( linker, "/entry:\"mainCRTStartup\"" )

int main(int argc, char *argv[]){
	CWorld *world;
	srand(0);
	
	world = CWorld::getWorldInstance(argc, argv);
	world->setFrameDelay(40);
	//p=new Pendulum();
	
	//s=new Sphere(Vector3d(-140,10,0),1,"Ball.bmp");//"earth.bmp");
	//s2=new Sphere(Vector3d(-140,0,0),1,"Ball.bmp");
	
	world->sceneManager->addSceneObject(new BackWall());
	world->sceneManager->addSceneObject(new LeftWall());
	//world->sceneManager->addSceneObject(new RightWall());
	world->sceneManager->addSceneObject(new Ground());
	
	
	//world->sceneManager->addSceneObject(p);
	//world->sceneManager->addSceneObject(new simulation(Vector3d(-7,3.5,-3), Vector3d(-7,0,-3)));
	world->sceneManager->addSceneObject(new simulation(Vector3d(-20,7.5, -HH-2.5-0.4/*-1.16*/), Vector3d(-20,-2.5,-HH-2.5-0.4)));
	world->sceneManager->addSceneObject(new Plate());
	//world->sceneManager->addSceneObject(s2);
	//world->sceneManager->addSceneObject(s);
			
	
	//world->sceneManager->addControlObject(new DisplayControl(p,5,70,200,80,0));
	//world->sceneManager->addControlObject(new DisplayControl(p,600,38,400,360,1));
	
	world->sceneManager->addShadowPlane(new ShadowPlane( Vector3d( 0.0, -4.99, 0.0 ), Vector3d( 0.0,  -1.0, 0.0) ) );
	//world->sceneManager->addShadowPlane(new ShadowPlane( Vector3d( 0,0,-25.99 ), Vector3d( 0.0,  0.0, -1.0) ) );
	//world->sceneManager->addShadowPlane(new ShadowPlane( Vector3d( -25.99,0,0 ), Vector3d( -1,0,0) ) );
	//world->sceneManager->addShadowPlane(new ShadowPlane( Vector3d( 25.99,0,0 ), Vector3d( 1,0,0) ) );

		//glVertex3f(-15,5,-15);
	//	glVertex3f(-15,5,15);
	//	glVertex3f(19,-10,15);
	//	glVertex3f(19,-10,-15);
	
	double zz=(-1.0*(-5-HH))/tan(Alfa);
	
	Vector3d v1(-20,HH,-15);
	Vector3d v2(-20,HH,15);
	Vector3d v3(-20+zz,-5,15);
	
	Vector3d ww=(v2-v1)%(v2-v3);
	ww.norm();
	world->sceneManager->addShadowPlane(new ShadowPlane( Vector3d( -20,HH+0.1,-15 ), ww ));

	
	Light *light1;
	light1 = new Light();
	GLfloat pos1[]={70,70,-70,1};
	//GLfloat pos1[]={100,120,0,1};
	GLfloat amb[] = {0.35,0.35,0.35,0.0};
	light1->setLightPos(pos1);
	light1->setAmbientLight(amb);
	world->sceneManager->addLight(light1);	
	
	glLightModelfv(GL_LIGHT_MODEL_AMBIENT,amb);
	glEnable(GL_COLOR_MATERIAL);
	glColorMaterial(GL_FRONT_AND_BACK,GL_AMBIENT_AND_DIFFUSE);
	GLfloat spec[]={1.0, 1.0 ,1.0 ,1.0};  
	glMaterialfv(GL_FRONT_AND_BACK,GL_SPECULAR,spec);
	
	float df=128.0;

	
	glMaterialfv(GL_FRONT_AND_BACK,GL_SHININESS,&df);	
	glEnable (GL_AUTO_NORMAL);	
	
	
	world->getKamera()->MoveForward(20.0);
	//world->getKamera()->StrafeRight(10);
	//world->getKamera()->RotateY(45);
	//world->getKamera()->RotateX(-45);
	world->showAxis(0);
	world->mainLoop();
	return 0;
}


