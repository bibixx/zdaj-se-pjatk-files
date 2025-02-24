#ifndef __SIMULATION_H
#define __SIMULATION_H
#include "soleng.h"
#include "Sphere.h"


class simulation : public SceneObject{
public:
	simulation(Vector3d pos1, Vector3d pos2);
	void draw();
	void doStep();
	bool getCastsShadows() const {return true;}
	void keyboard(int key);
private:
	void solveEuler(double dt);
	void solveMidPoint(double dt);
	void solveRK4(double dt);
	void reset();
	GLUquadricObj *sphere1;
	GLUquadricObj *tube;
	GLUquadricObj *disk1;
	GLUquadricObj *disk2;
	CTexture txt1;
	CTexture txt2;
	float r1;
	float r2;
	Vector3d pos1;
	Vector3d pos2;
	
	Vector3d V1;
	Vector3d Rot1;
	Vector3d V2;
	Vector3d Rot2;
	
	int solver;
	
	float Omega1;
	float angle1;
	float Omega2;
	float angle2;
	
	Graph2D *gr;
	
	Sphere *sphere;
	
};

#endif

