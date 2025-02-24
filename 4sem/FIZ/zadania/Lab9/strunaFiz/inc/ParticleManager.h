#ifndef __PARTICLE_MANAGER_H
#define __PARTICLE_MANAGER_H
#include "Particle.h"
#include <vector>

using namespace std;

typedef vector<Particle*>particleList;
typedef particleList::iterator iteratorParticleList;

class ParticleManager{
public:
	ParticleManager(int n);
	void draw();
	void doStep();
	void init();
private:
	particleList cList;
	int n;
};

#endif
