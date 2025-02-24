/* Symulacja splaty kredytu */

say "Kredyt?";
kredyt = linein();
say "Liczba lat kredytu?";
lata = linein();
say "Oprocentowanie w skali rocznej?"
proc = linein();

/* Przeliczamy na odsetki, miesi©cznie */
proc = (proc/100)/12;

lrat = lata * 12;
rata = kredyt / lrat;

doSplaty = kredyt;
sumaSplat = 0;

do i = 1 to lrat
   splata = rata + proc*doSplaty;
   doSplaty = doSplaty - rata;
   sumaSplat = sumaSplat + splata;
   say i ':' splata;
end

say "Suma spˆat   =" sumaSplat;
say "Ponad kredyt =" sumaSplat - kredyt;

 