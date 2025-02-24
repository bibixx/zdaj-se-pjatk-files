package CW_27_10_08;

public class Zadanie23 {

		public static void main(String[] args) {
		   String s1 = "a"; 
	        String s2  ="b"; 
	        String s3 = "ab"; 

	        System.out.println(s1+s2); 
	        System.out.println(s3); 
	        System.out.println(s3==s1+s2); 
	        System.out.println(s3.equals(s1+s2)); 
	        System.out.println(s3=="a"+"b"); 

	        s1="1"; 

	        System.out.println(s1+2+3); 
	        System.out.println((s1+2)+3); 
	        System.out.println(s1+(2+3)); 
	        System.out.println(s1+(2/3)); 
	        System.out.println(s1+2/3); 


	}

}
