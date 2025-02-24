package CW_07_11_08;

public class Zadanie28 
{
	public static void main(String[] args) 
	{
		int x=2,y=3,r=4;
		Point p = new Point(x,y);    
		Circle c = new Circle(p, 4);
		Circle d = new Circle(new Point(3,4), 5);
		
		System.out.println("\nKlasa Circle:");
		p=c.getCenter();													//1.
		System.out.println("Radius: " + c.getRadius());						//2.
		c.setCenter(p);														//3.
		c.setRadius(r);														//4.
		c.show();															//5.
		System.out.println("Czy nasz punkt jest w srodku: " +c.inside(p));	//6,
		System.out.println("Czy nasze kolo jest wieksze: " +c.isBigger(d));	//7.
	    System.out.println("Pole naszego kola: "+d.area());					//8.
		System.out.println("Liczba kol: " + Circle.dajNr());
		
		
	}
}
	
	class Circle 
	{
		static int nr;
		int r;
		public Point p = new Point();
		public Circle(){
			p.setX(0);
			p.setY(0);
			r=1;
			Circle.nr++;
		}
		public Circle(int r){
			p.setX(0);
			p.setY(0);
			this.r=r;
			Circle.nr++;
		}
		public Circle(Point p, int r){
		
			this.p = p;
			this.r=r;
			Circle.nr++;
		}
		public Point getCenter(){
			return p;
		}
		public int getRadius(){
			 return r;
		 }
		public void setCenter(Point c){	p = c; }
		public void setRadius(int r){
			this.r=r;
		}
		public void show(){
			System.out.println("Punkt: " + p.show() +  " Promien: " + r);
		}
		public boolean inside(Point p){
			double odl = this.p.distance(p);
			return (odl < r);
		}
		public boolean isBigger(Circle c) {
			if (this.getRadius() >= c.getRadius()) return true;
			else return false;
			
		}
		public double area(){
			double pole = Math.PI * r*r;
			return pole;
		}
		public static int dajNr() {return nr;} 
	}
	
	class Point 
	{
		int x=0,y=0;
		public Point(int a, int b)	{x = a;	y = b;}
		public Point(int a)	{x = a; y = 0;}
		public Point()	{x = 0;	y = 0;}
		
		public int getX(){return x;}
		public int getY(){return y;}
		public void setX(int a) {x = a;}
		public void setY(int b) {y = b;}
		public String show() {return (x+","+y);}
	
		public double distance(Point temp) {
			int od1 = x - temp.x;
			int od2 = y - temp.y;
			double dlugosc = Math.sqrt(od1*od1 + od2*od2);
			return dlugosc;
			}
			
		public boolean parallel(Point temp) {
			if (x == temp.x || y == temp.y) return true; 
			else return false;
			}
	}

