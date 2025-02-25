/* Obliczenie ceny komputera */


N = 6 /* liczba sk�adnik�w */

/* Tablica nazw sk�adnik�w */
elt[] = { "Procesor", "P�yta", "Pami��", "Dysk", "Monitor", "Inne" };

/* 
   Pobieramy ceny ka�dego sk�adnika
   i liczymy ich sum�
*/
suma = 0
do i = 1 to N
   say elt[i] "- podaj cen�: ";
   cena[i] = linein();
   suma = suma + cena[i];
end

say "Cena komputera wynosi :" suma;
say "Udzia� ceny procesora :" cena[1]/suma;

/* 
   Dajemy u�ytkownikowi mo�liwo��
   zmiany ceny dowolnego sk�adnika 
*/
say "Kt�r� cen� chcesz zmieni� ? Wybierz";
do i = 1 to n
   say i '-' elt[i]
end
say "Podaj wybrany numer:"
nr = linein()

/* 
   Podany numer musi zawiera� si� 
   w zakresie od 1 do N - liczby sk�adnik�w 
*/ 

if (nr < 1 | nr > N) then do 
   say "Z�y wyb�r";
   exit
end

/* zachowujemy star� cen� wybranego sk�adnika */
stara_cena = cena[nr];

/* 
   Pobieramy now� cen� wybranego skladnika
   i obliczamy now� cen� komputera
*/
   
say elt[nr] " - wprowadz now� cen�"
cena[nr] = linein();
suma = suma - stara_cena + cena[nr];

say "Cena komputera wynosi :" suma;
say "Udzia� ceny procesora :" cena[1]/suma;
       

