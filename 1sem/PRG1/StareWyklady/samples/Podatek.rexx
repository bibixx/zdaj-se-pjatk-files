/* Wyliczenie podatku */

say "Podaj doch¢d po odliczeniach:";
doch = linein();

if (doch > 74048) then pod = 17048.44 + 0.4 * (doch - 74048);
else if (doch > 37024) then podk = 6541.24 + 0.3 * (doch - 37024)
     else pod = 0.19 * doch - 493.32;

say "Podatek:" pod;