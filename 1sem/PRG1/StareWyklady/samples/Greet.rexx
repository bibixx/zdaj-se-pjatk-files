/* Pierwszy program:  powitanie */ 

say "Jak si© nazywasz?";     /* wypisz tekst na konsoli */

name = linein();             /* wczytaj dane z konsoli  */ 

/* 
  je¾eli nie podano imienia, wypisz "Witaj" 
  w przeciwnym razie - Witaj, imie i wykrzyknik     
*/  
   
if name = "" then say "Witaj!";
else say "Witaj," name "!";

/* podaj godzine */
say "Teraz jest godzina:" time(); 

