/* Obliczenie ceny komputera */

/* Tablica nazw skˆadnik¢w */
elt[0] = 6;  /* liczba skladnikow - w elemencie z indeksem 0 */
elt[] = { "Procesor", "Pˆyta", "Pami©†", "Dysk", "Monitor", "Inne" };

cena[0] = elt[0]  /* tyle samo cen ile skladnikow */

do i = 1 to cena[0]
   cena[i] = inputData(elt[i]); 
end

more = 1;                /* czy powtarza† obliczenia i zmiany ? */
do while (more = 1)
   cenaOg = sumPrices(); 
   say "Cena komputera wynosi :" cenaOg;

   nrSkl = choose("Wybierz skˆadnik, kt¢rego udziaˆ w cenie chcesz policzy†");
   if (nrSkl \= '') then do
     udzial = cena[nrSkl]/cenaOg;
     say "Udzial ceny skladnika" elt[nrSkl] "wynosi: " udzial;
   end

   nrSkl = choose("Wybierz skladnik, ktorego cene chcesz zmienic");
   if (nrSkl = "") then more = 0;
   else cena[nrSkl] = inputData( elt[nrSkl] );

end
exit;
 

function inputData(nazwaSkl)
   say "Wprowadz cene dla: " nazwaSkl;
   trzebaPobracDane = 1;
   do while (trzebaPobracDane)
      cena = linein()
      if datatype(cena) = "NUM" then trzebaPobracDane = 0;
   end
   return cena;

function sumPrices() expose cena[]
  sum = 0;
  do i = 1 to cena[0]
    sum = sum + cena[i];
  end
  return sum;

function choose(msg) expose elt[] 
  say msg;
  do i = 1 to elt[0]
    say i '-' elt[i];
  end
  do forever
    say "Podaj wybrany numer lub samo ENTER by zrezygnowac:"
    nr = linein();
    if nr = "" then leave;
    if (nr < 1 | nr > elt[0]) then say "Zˆy wyb¢r.";
    else leave;
  end
  return nr;


