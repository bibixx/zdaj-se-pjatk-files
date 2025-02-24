#ifndef __UKLAD_PLANET_H
#define __UKLAD_PLANET_H

#include "soleng.h"
#include <iostream>
#include <vector>
#include "planeta.h"

typedef std::vector<Planeta*> planetsList;
typedef planetsList::iterator planetsIterator;		


class UkladPlanet : public SceneObject{
	public: 
		UkladPlanet();
		void addPlanet(Planeta *p);
		void solveEuler();
		void solveMidPoint();
		void solveRK4();
		void draw();
		void doStep();
		void setPlanetsVelocity();
		void ZeroAcceleration();
		void calcAcceleration();
		void drawControl(int id, int w, int h);
	private:
		void armagedon();
		
		void keyboard(int key);
		planetsList planets;
		Vector3d trackPos[4000];
		Vector3d trackPos1[4000];		
		Vector3d trackPos2[4000];	
		Vector3d trackPos3[4000];	
		Vector3d trackPos4[4000];	
		
		Vector3d trackPos5[4000];	
		Vector3d trackPos6[4000];	
		Vector3d trackPos7[4000];	
		int idx;
		int idx1;	
		int idx2;
		int idx3;
		int idx4;
		int idx5;
		int idx6;
		int idx7;
		bool rysujTrajektorie;
		bool lis;
		
		int camidx;
		int solver;
};

#endif

