#ifndef __STRUKTURY_H
#define __STRUKTURY_H



class Wektor{
public:
       Wektor(double _x=0, double _y=0, double _z=0):
       x(_x),y(_y),z(_z){}

       Wektor operator+(Wektor const &);
       Wektor operator-(Wektor const &);
       double operator*(Wektor const &);
       Wektor operator*(double);

       double len();
       void norm();
       double x,y,z;
};

Wektor operator*(double, Wektor const &);


Wektor Wektor::operator-(Wektor const &w)
{
       return Wektor(x-w.x,y-w.y,z-w.z);
}

double Wektor::operator*(Wektor const &w)
{
       return (x*w.x + y*w.y + z*w.z);
}

double Wektor::len()
{
       return sqrt((*this)*(*this));
}

Wektor Wektor::operator+(Wektor const &w)
{
       return Wektor(x+w.x,y+w.y,z+w.z);
}

Wektor Wektor::operator*(double l)
{
       return Wektor(x*l, y*l, z*l);
}


Wektor operator*(double s, Wektor const &w)
{
       return Wektor(s*w.x, s*w.y, s*w.z);
}
void Wektor::norm()
{
     double d = len();
     if (d)
        (*this)=(*this)*(1/d);
}

struct Punkt{
        int flag;
        float m;

        Wektor f;
        Wektor r;
        Wektor v;

        Wektor dr;
        Wektor dv;

        Punkt *right;
};

#endif
