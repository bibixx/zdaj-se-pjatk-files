package CW_13_10_08;
public class Zadanie12 {
	public static void main(String[] args) {
		int a = 1;
		System.out.println("a = " + a);
		a++;
		System.out.println("a++ = " + a);
        ++a;    
        System.out.println("++a = " + a);
        int b = a++;    
        System.out.println("b = a++ = " + b);
        int c = ++b;
        System.out.println("c = ++b = " + c);
        int d = ++a/b++;
        System.out.println("d = ++a/b++ = " + d);
	}
}
