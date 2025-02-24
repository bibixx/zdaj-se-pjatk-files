//--------------------------------------------------------------------------
//
//	KOD DO ZADANIA DOMOWEGO Z FIZYKI
//
//--------------------------------------------------------------------------
#include "rigidbody.h"

#define	ANGULARDRAGCOEFFICIENT	100.25f
#define LINEARDRAGCOEFFICIENT	0.25f

//--------------------------------------------------------------------------
// pomocnicze procedury
//--------------------------------------------------------------------------
float	DegreesToRad(float deg);
float	RadiansToDeg(float rad);
Vector3d VRotate2D( float angle, Vector3d u);

//---------------------------------------------------------------------------

//---------------------------------------------------------------------------
//		KOD DO NAPISANIA
//---------------------------------------------------------------------------

//---------------------------------------------------------------------------
//	wyznaczenie wypadkowej sily oraz wypadkowego momentu
//  klasa RigidBody zawiera nastepujace skladowe
//  z ktorych trzeba skorzystac w tym miejscu
//
//		Vector3d forces;				-- calkowita sila dzialajaca na cialo
//		Vector3d moment;				-- calkowity moment sily dzialajacy na cialo (tylko wzgledem osi obrotu 2D)
//		Vector3d thrustForce;			-- wielkosc sily ciagu
//		Vector3d angularVelocity;		-- predkosc katowa w ukladzie ciala
//		Vector3d CD;					-- polozenie srodka ciezkosci
//		Vector3d CT;					-- polozenie srodka ciagu wzgledem srodka ciezkosci
//		Vector3d CPT;					-- polozenie lewego silnika wzgledem srodka ciezkosci
//		Vector3d CST;					-- polozenie prawego silnika
//		float projectedArea;			-- powierzchcnia sredniego przekroju poprzecznego
//		Vector3d velocityBody;			-- predkosc w ukladzie bryly (wektor skladowa z 0)
//		Vector3d sThrust;				-- ciag prawego silnika (sila)
//		Vector3d pThrust;				-- ciag lewgo silnika (sila)
//		float orientation;				-- kat obrotu ciala wzgledem globalnego ukladu
//---------------------------------------------------------------------------
void RigidBody::CalcLoads(){
	Vector3d	Fb;							// sila wypadkowa
	Vector3d	Mb;							// wypadkowy moment sil
	Vector3d	Thrust;						// sila ciagu tylnego silnika
	
	// wyzerowanie sil i momentow sil:
	this->forces = Vector3d(0.0, 0.0, 0.0); // wyzerowanie sily wypadkowej
	this->moment = Vector3d(0.0, 0.0, 0.0); // wyzerowanie mometu wypadkowego

	Fb = Vector3d(0.0, 0.0, 0.0);			// wyzerowanie licznika pomocniczego do sumowania sil
	Mb = Vector3d(0.0, 0.0, 0.0);			// wyzerowanie licznika pomocniczego do sumowania momentow

	// wektor sily ciagu silnika tylnego
	Thrust = Vector3d(1.0, 0.0, 0.0);		// kierunek sily ciagu
	Thrust = Thrust * this->thrustForce;	// wartosc sily (w tym przypadku os x1)
	
	// obliczenia sil i momentow w uladzie ciala
	Vector3d	vLocalVelocity;				// lokalna predkosc liniowa kierunek
	float		fLocalSpeed;				// predkosc
	Vector3d	vDragVector;				// sila oporu
	float		tmp;
	Vector3d	vResultant;	
	Vector3d	vtmp;	

	// obliczanie sily oporu aerodynamicznego
	// predkosc lokalna sklada sie z predkosci wynikajacej z ruchu liniowego
	// oraz predkosci wynikajacej z obrotu obiektu
		vtmp = this->angularVelocity%this->CD; // czesc obrotowa
		vLocalVelocity = this->velocityBody + vtmp; 

		// wyznaczenie predkosci powietrza
		fLocalSpeed = vLocalVelocity.len();

		// wyznaczenie kierunku dzialania sily oporu
		// zawsze dziala w tym samym kierunku co predkosc ale
		// ma przeciwny kierunek
		float	const	rho = 0.0023769f;
		if(fLocalSpeed > 0.00001) 
		{
			vLocalVelocity.norm();
			vDragVector = -1.0f*vLocalVelocity;		

			// wyznaczenie wynikowej sily dzialajacej na cialo
			tmp = 0.5f * rho * fLocalSpeed*fLocalSpeed * this->projectedArea;		
			vResultant = vDragVector * LINEARDRAGCOEFFICIENT *4.0* tmp; // symulowana sila oporu stawiana przez kadlub

			// dodanie do sily wypadkowej sily oporu aerodynamicznego
			Fb = Fb + vResultant;
		
			// obliczenie momentu sily oporu
			vtmp = this->CD%vResultant; 
			Mb = Mb + vtmp;
		}

		// obliczanie sily ciagu lewego silnika kierunkowego
		Fb = Fb + this->pThrust;	

		// oraz wynikajacego z jej dzialania momentu sily
		vtmp = this->CPT%this->pThrust; 		// CPT polozenie silnika w ukladzie ciala
		Mb = Mb + vtmp;

		// obliczanie sily ciagu prawego silnika kierunkowego
		Fb = Fb + this->sThrust;

		// oraz wynikajacego z jej dzialania momentu sily
		vtmp = this->CST%this->sThrust; 		
		Mb = Mb + vtmp;

	//!..!

	// obliczanie sily ciagu tylnego silnika kierunkowego
	Fb = Fb + Thrust; 
	// sila wynikajaca z dzialania tego silnika
	// nie powoduje powstania momentu sily
	// poniewaz kierunek jej dzialania jest wzdlow osi
	// na ktorej znajduje sie srodek ciezkosci
	// czyli traktowana jest tak jakby zostala
	// przylozona w srodku ciezkosci czyli rramie wynosi 0
	// M=r x F czyli M=0

	// dokonanie trasformacji sil z lokalnego ukladu poduszkowca
	// do ukladu wspolrzednych swiata
	this->forces = VRotate2D(this->orientation, Fb);

	this->moment = this->moment + Mb;	
}

//---------------------------------------------------------------------------
// Calkowanie rownan ruchu
//		skladowe klasy RigidBody potrzebne przy obliczeniach
//			mass			- masa ciala
//			inertia			- moment bezwladnosci
//			forces			- wektor sily wypadkowej
//			moment			- wektor wypadkowego momentu
//			position		- wektor polozenia
//			velocity		- wektor predkosci
//			angularVelocity	- predkosc katowa (wektor)
//			orientation		- orientacja ciala (kat theta)
//---------------------------------------------------------------------------
// 						METODA EULERA
//---------------------------------------------------------------------------
void RigidBody::solveEuler(){
		Vector3d 	a;		// przyspieszenie liniowe
		Vector3d 	dv;		// pochodna predkosci liniowej
		Vector3d 	ds;		// pochodna polozenia
		float  		aa;		// przyspieszenie katowe
		float  		dav;	// pochodna predkosci katowej
		float  		dtheta;	// pochodna kata
		float 		dt = 0.03;
		
		// wyznacz sily oraz momenty
		CalcLoads();
		
		// calkowanie rownania ruchu postepowego
		// F=ma -> a = F/m
		a = this->forces / this->mass;
		
		// dv/dt = a pochodna predkosci po czasi to przyspieszenie
		// znajac przyspieszenie wyznaczamy zmiane predkosci
		// i zgodnie ze schemeatem Eulera wyliczamy nowa predkosc
		// jako stara zwiekszona o zmiane dv
		dv = a * dt;
		this->velocity = this->velocity + dv;

		// wyznaczamy zmiane polozenia w ruchu liniowym
		// s = v*t
		// ds/dt = v
		ds = this->velocity * dt;
		// zgodnie ze schematem Eulera nowe polozenie to
		// stare plus zmiana polozenia ds
		this->position = this->position + ds;

		// calkowanie rownania ruchu obrotowego
		// M = I aa gdzie 
		// M  - wypadkowy moment sil
		// I  - moment bezwladnosci
		// aa - przyspieszenie katowe
		// aa = M/I
		aa = this->moment.z / this->inertia;

		// zmiana predkosci katowek w czasie definiuje przyspieszenie katow
		// av - predkosc katowa
		// aa - przyspieszenie katowe
		// czylli dav/dt = aa
		// czyli nowa predkosc katowa wyznaczana jest zgodnie ze schematem Eulera
		// angularvelocity = angularVelocity + dav
		// poniewaz obrot ma miejsce wylacznie wzgledem osi z
		// liczymy tylko predkosc katowa wzgledem osi z
		dav = aa * dt;
		this->angularVelocity.z = this->angularVelocity.z + dav;
		// predkosc katowa oznacza zmiane kata w czasie
		// czyli
		// av = dtheta/dt
		// czyli dtheta = av * dt
		dtheta = RadiansToDeg(this->angularVelocity.z * dt);
		// i ostatecznie aktualny kat przechowywany w zmiennej orientation
		// modyfikujemy o wyliczony przyrost kata dtheta
		this->orientation = this->orientation + dtheta; 
		// obliczamy wartosc predkosc
		// jako dlugosc wektora predkosci
		this->speed = this->velocity.len();	
		// wyznaczamy wektor predkosci w ukladzie swiata obracajac
		// wektor predkosci w ukladzie lokalnym
		this->velocityBody = VRotate2D(-this->orientation, this->velocity);			
}
//---------------------------------------------------------------------------

//---------------------------------------------------------------------------
// ruch postepowy:
//		a = d^2 s / dt
//	 wyliczyc
//		velocity	- v
//		position	- s
//
// ruch obrotowy:
// 		episilon = d^2 theta/dt
//	wyliczyc
//		angularVelocity		- omega
//		orientation			- theta
//---------------------------------------------------------------------------

//---------------------------------------------------------------------------
//	calkowanie metoda MidPoint
//---------------------------------------------------------------------------
void RigidBody::solveMidPoint(){
		Vector3d 	a;		// przyspieszenie liniowe
		Vector3d 	dv;		// pochodna predkosci liniowej
		Vector3d 	ds;		// pochodna polozenia
		Vector3d	k1v,k2v,k1s,k2s;
		float  		aa;		// przyspieszenie katowe
		float  		dav;	// pochodna predkosci katowej
		float  		dtheta;	// pochodna kata
		float 		dt = 0.03;	
		// wyznacz sily oraz momenty
		CalcLoads();
		// F=ma -> a = F/m
		a = this->forces / this->mass;
		dv = a * dt;
		ds = this->velocity * dt;
		
		k1v = a * dt;
		k1s = dt * dv;
		k2v = dt * (dv+0.5*k1v);
		k2s = dt * (ds+0.5*k1s);
		ds = ds + k2s;
		dv = dv + k2v;
		this->position = this->position + ds;
		this->velocity = this->velocity + dv;
//----------------
		aa = this->moment.z / this->inertia;
		dav = aa * dt;
		dtheta = RadiansToDeg(this->angularVelocity.z * dt);
		
		float k1dav = aa * dt;
		float k1dtheta = RadiansToDeg(this->angularVelocity.z * dt);//dt * angularVelocity.z;
		float k2dav = dt * (dav + 0.5 * k1dav);
		float k2dtheta = dt * (dtheta + 0.5 * k1dtheta);
		
		dav = dav + k2dav;
		dtheta = dtheta + k2dtheta;
		
		this->orientation = this->orientation + dtheta; 
		this->angularVelocity.z = this->angularVelocity.z + dav;		
		this->speed = this->velocity.len();	
		this->velocityBody = VRotate2D(-this->orientation, this->velocity);
}
//---------------------------------------------------------------------------
//	calkowanie metoda RK4
//---------------------------------------------------------------------------
void RigidBody::solveRK4(){
		Vector3d 	a;		// przyspieszenie liniowe
		Vector3d 	dv;		// pochodna predkosci liniowej
		Vector3d 	ds;		// pochodna polozenia
		Vector3d	k1v,k2v,k1s,k2s,k3v, k3s, k4v, k4s;
		float  		aa;		// przyspieszenie katowe
		float  		dav;	// pochodna predkosci katowej
		float  		dtheta;	// pochodna kata
		float 		dt = 0.03;	
		// wyznacz sily oraz momenty
		CalcLoads();
		// F=ma -> a = F/m
		a = this->forces / this->mass;
		dv = a * dt;
		ds = this->velocity * dt;
		
		k1v = a * dt;
		k1s = dt * dv;
		k2v = dt * (dv+0.5*k1v);
		k2s = dt * (ds+0.5*k1s);
		k3v = dt * (dv+0.5*k2v);
		k3s = dt * (ds+0.5*k2s);
		k4v = dt * (dv+0.5*k3v);
		k4s = dt * (ds+0.5*k3s);
		ds = ds + (k1s/6)+(k2s/3)+(k3s/3)+(k4s/6);
		dv = dv + (k1v/6)+(k2v/3)+(k3v/3)+(k4v/6);
		this->position = this->position + ds;
		this->velocity = this->velocity + dv;
//----------------
		aa = this->moment.z / this->inertia;
		dav = aa * dt;
		dtheta = RadiansToDeg(this->angularVelocity.z * dt);
		
		float k1dav = aa * dt;
		float k1dtheta = RadiansToDeg(this->angularVelocity.z * dt);;
		float k2dav = dt * (dav + 0.5 * k1dav);
		float k2dtheta =dt * (dtheta + 0.5 * k1dtheta);
		float k3dav = dt * (dav + 0.5 * k2dav);
		float k3dtheta = dt * (dtheta + 0.5 * k2dtheta);
		float k4dav = dt * (dav + 0.5 * k3dav);
		float k4dtheta = dt * (dtheta + 0.5 * k3dtheta);
		
		dav = dav + (k1dav/6)+(k2dav/3)+(k3dav/3)+(k4dav/6);
		dtheta = dtheta + (k1dtheta/6)+(k2dtheta/3)+(k3dtheta/3)+(k4dtheta/6);		
		
		
		this->orientation = this->orientation + dtheta; 
		this->angularVelocity.z = this->angularVelocity.z + dav;		
		this->speed = this->velocity.len();	
		this->velocityBody = VRotate2D(-this->orientation, this->velocity);
}
//---------------------------------------------------------------------------
// 						RESZTA KODU
//---------------------------------------------------------------------------
RigidBody::RigidBody(){
	Initialize();
}

void RigidBody::Initialize(){
	// polozenie poczatkowe
	this->position = Vector3d(0.0f, 0.0f, 0.0f);
	
	//predkosc poczatkowa
	this->velocity = Vector3d(0.0f, 0.0f, 0.0f);
	
	// poczatkowa predkosc katowa
	this->angularVelocity = Vector3d(0.0f, 0.0f, 0.0f);
	
	// okreslenie poczatkowych sil i momentow sil
	this->forces = Vector3d(0.0f, 0.0f, 0.0f);
	this->moment = Vector3d(0.0f, 0.0f, 0.0f);
	
	// zerowanie predkosci w lokalnym ukladzie
	this->velocityBody = Vector3d(0.0f, 0.0f, 0.0f);
	
	// poczatkowa orientacja bryly
	this->orientation = 0.0f;
	
	// wlasciwosci masy
	this->mass = 120.6f;
	this->inertia = 38332.0;
	this->inertiaInverse = 1.0f / this->inertia;
	
	//wspolrzedne srodka ciezkosci i oporu
	this->CD = Vector3d(-3.0f, 0.0f, 0.0f);
	
	//polozenie silnika napedowego
	this->CT = Vector3d(-30.0f, 0.0f, 0.0f);
	
	// lewy silnik
	this->CPT = Vector3d(30.0f, -25.0f, 0.0);
	
	// prawy silnik
	this->CST = Vector3d(30.0f, 25.0f, 0.0);
	
	this->projectedArea = 1500.0f;	// srednia powierzchnia przekroju
	this->thrustForce = 0.0f;		// poczatkowa sila ciagu
	
	this->width = 50.0f;
	this->length = 70.0f;
}



RigidBody::RigidBody(RigidBody const &b){
	this->mass = b.mass;
	this->inertia = b.inertia;
	this->inertiaInverse = b.inertiaInverse;
	this->position = b.position;
	this->velocity = b.velocity;
	this->velocityBody = b.velocityBody;
	this->angularVelocity = b.angularVelocity;
	this->speed = b.speed;
	this->orientation = b.orientation;
	this->forces = b.forces;
	this->moment = b.moment;
	this->CD = b.CD;
	this->CT = b.CT;
	this->CPT = b.CPT;
	this->CST = b.CST;
	this->projectedArea = b.projectedArea;
	this->thrustForce = b.thrustForce;
	this->pThrust = b.pThrust;
	this->sThrust = b.sThrust;
	
}

float	DegreesToRad(float deg)
{
	float	const	pi	= 3.14159265f;
	return deg * pi / 180.0f;
}

float	RadiansToDeg(float rad)
{	
	float	const	pi	= 3.14159265f;
	return rad * 180.0f / pi;
}

Vector3d VRotate2D( float angle, Vector3d u)
{
	float	x,y;
	x = u.x * cos(DegreesToRad(-angle)) + u.y * sin(DegreesToRad(-angle));
	y = -u.x * sin(DegreesToRad(-angle)) + u.y * cos(DegreesToRad(-angle));

	return Vector3d( x, y, 0);
}
//------------------- KONIEC ------------------------------------------------

