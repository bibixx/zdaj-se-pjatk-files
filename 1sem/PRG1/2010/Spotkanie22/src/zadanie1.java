public class zadanie1 {


    public static void main(String[] args) {
   
        System.out.println(sumuj());
        System.out.println(sumuj(1,2));
        System.out.println(sumuj(1, 2, 5, 7));
    }
public static int sumuj( int ... dane)
{
    int suma = 0;
    for ( int i = 0; i < dane.length ; i++ )
    {
        suma+=dane[i];
    }
    return suma;
   
}
}