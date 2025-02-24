/* Obliczenie ceny komputera */

cProc = 700;
cPly  = 500;
cPam = 300;
cDysk = 400;
cMon = 1100;
cInn = 500;
cKomp = cProc + cPly + cPam + cDysk + cMon + cInn;

say "Pocz¥tkowo cena komputera wynosi :" cKomp;
say "Udziaˆ ceny procesora :" cProc/cKomp;

cProc = 300;
cPly = 400;
cKomp = cProc + cPly + cPam + cDysk + cMon + cInn;

say "Po zmianie cen procesora i plyty";
say "Cena komputera wynosi :" cKomp;
say "Udziaˆ ceny procesora :" cProc/cKomp;

