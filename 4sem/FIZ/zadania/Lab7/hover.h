#ifndef __HOVER_H
#define __HOVER_H
#include "soleng.h"
#include "rigidbody.h"
#include "glm/glm.h"
#include "inc/meshObject.h"
class HoverCraft : public SceneObject{
public:
	HoverCraft();
	void draw();
	void doStep();
	void keyboard(int key);
	void specialkey(int key, int x, int y);
	bool getCastsShadows() const {return true;}
	void drawControl(int id, int w, int h);
private:
	RigidBody *craft;
	
	GLMmodel* pmodel1;
	GLMmodel* pmodel2;
	GLMmodel* pmodel3;
	int solver;
	MeshObject *hoverModel;
	MeshObject *towerModel;
	MeshObject *roverModel;
};

#endif
