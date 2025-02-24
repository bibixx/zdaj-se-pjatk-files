#ifndef __MESH_OBJECT_H
#define __MESH_OBJECT_H

#include "../soleng.h"
#include "../glm/glm.h"


class MeshObject : public SceneObject{
public:
	MeshObject();
	void LoadObject(char *objfile, int normals);
	void draw();
private:
	GLMmodel* model;
	int listid;
};


#endif
