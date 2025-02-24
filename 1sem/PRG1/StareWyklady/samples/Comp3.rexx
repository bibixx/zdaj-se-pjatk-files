/* Obliczenie ceny komputera */


N = 6 /* liczba skˆadnik¢w */

/* Tablica nazw skˆadnik¢w */
elt[] = { "Procesor", "Pˆyta", "Pami©†", "Dysk", "Monitor", "Inne" };

/* 
   Pobieramy ceny ka¾dego skˆadnika
   i liczymy ich sum©
*/
suma = 0
do i = 1 to N
   say elt[i] "- podaj cen©: ";
   cena[i] = linein();
   suma = suma + cena[i];
end

say "Cena komputera wynosi :" suma;
say "Udziaˆ ceny procesora :" cena[1]/suma;

/* 
   Dajemy u¾ytkownikowi mo¾liwo˜†
   zmiany ceny dowolnego skˆadnika 
*/
say "Kt¢r¥ cen© chcesz zmieni† ? Wybierz";
do i = 1 to n
   say i '-' elt[i]
end
say "Podaj wybrany numer:"
nr = linein()

/* 
   Podany numer musi zawiera† si© 
   w zakresie od 1 do N - liczby skˆadnik¢w 
*/ 

if (nr < 1 | nr > N) then do 
   say "Zˆy wyb¢r";
   exit
end

/* zachowujemy star¥ cen© wybranego skˆadnika */
stara_cena = cena[nr];

/* 
   Pobieramy now¥ cen© wybranego skladnika
   i obliczamy now¥ cen© komputera
*/
   
say elt[nr] " - wprowadz now¥ cen©"
cena[nr] = linein();
suma = suma - stara_cena + cena[nr];

say "Cena komputera wynosi :" suma;
say "Udziaˆ ceny procesora :" cena[1]/suma;
       

