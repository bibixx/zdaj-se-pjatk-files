package nai;

import javax.swing.*;
import java.io.*;

class Perceptron {
	
	double teta;
	double c = 0.5;
	int wektor[] = new int[3];
	int tab[][] = { {0,0,0,-1},
					{0,0,1,1},
					{0,1,0,-1},
					{0,1,1,1},
					{1,0,0,-1},
					{1,0,1,1},
					{1,1,0,1},
					{1,1,1,1}
				  };

	public Perceptron() {
		wektor[0]=losuj();
		wektor[1]=losuj();
		wektor[2]=losuj();
		
		
		teta = (int)(Math.random()*100)/100.0;
		if((int)(Math.random()*100)%2==0) teta=-teta;

		System.out.println("Wylosowane:");
		System.out.println(" Wektor poczatkowy: ("+wektor[0]+","+wektor[1]+","+wektor[2]+")");
		System.out.println(" Wartosc progowa: "+teta);
		System.out.println("___________________________________________");
		
		int tmp,net,y;
		int zmiana=0,cykl=1;

		for(tmp=0; tmp<8; tmp++){
			net = tab[tmp][0]*wektor[0]+tab[tmp][1]*wektor[1]+tab[tmp][2]*wektor[2];
			y=(double)net<teta?-1:1;
//			System.out.println("   "+tmp+": net="+net+" y="+y);
			if(y!=tab[tmp][3]){
				zmien(tmp,y);
				zmiana=1;
			}
			if(tmp==7 && zmiana==1){
				tmp = -1;
				zmiana = 0;
				cykl++;
//				System.out.println("***** Cykl "+cykl);
			}
		}
		
		System.out.println();
		System.out.println("Ilosc cykli: "+cykl);
		System.out.println("Wektor koncowy: ("+wektor[0]+","+wektor[1]+","+wektor[2]+")");
		System.out.println("Wartosc progowa: "+teta);
	}
	
	void zmien(int tmp, int y1){
		int d = tab[tmp][3];
		double teta2 = teta - c	*(d - y1);
		int w[]= new int[3];
		w[0] = (int)(wektor[0]+c*(d-y1)*tab[tmp][0]);
		w[1] = (int)(wektor[1]+c*(d-y1)*tab[tmp][1]);
		w[2] = (int)(wektor[2]+c*(d-y1)*tab[tmp][2]);
		System.out.println(" Zla odpowiedz! Zmiana: ("+wektor[0]+","+wektor[1]+","+wektor[2]+") na ("+w[0]+","+w[1]+","+w[2]+"), nowa wart.prog.="+teta2);
		wektor[0]=w[0];
		wektor[1]=w[1];
		wektor[2]=w[2];
		teta = teta2;
	}
	
	int losuj(){
		return (int)(Math.random()*10);	
	}
	
	int AND(int x, int y){
		return x&y;
	}

	int OR(int x, int y){
		return x|y;
	}

	int NEG(int x){
		return x==0?1:0;
	}
	int XOR(int x, int y){
		return x==y?0:1;
	}

	public static void main(String args[]) {
		new Perceptron();
		System.exit(0);
	}
}
