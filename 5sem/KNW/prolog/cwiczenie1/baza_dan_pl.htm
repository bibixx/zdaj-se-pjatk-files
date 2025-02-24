<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0048)http://www.ceti.pl/~gralinski/lik320/baza_dan.pl -->
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=iso-8859-2">
<META content="MSHTML 6.00.2737.800" name=GENERATOR></HEAD>
<BODY><PRE>/* Predykat glowny */
/* UWAGA: Wczytywanie termu należy każdorazowo konczyć kropką + Enter */

poczatek :- 
	abolish(student, 3),
	abolish(wyklad, 2),
	write('Zaladowac baze danych?'), nl,
	get_single_char(X),
	( X = 116,
          [baza]
        ; !
        ),
	menu.	

menu :- 
	write(' 1. - dopisywanie studenta'), nl,
	write(' 2. - dopisywanie wykladu'), nl,
	write(' 3. - usuwanie studenta'),nl,
	write(' 4. - usuwanie wykladu'),nl,
	write(' 5. - przegladanie '),nl,
	write(' 6. - koniec '), nl,
	read(X),
	wykonaj(X).

wykonaj(1):-
	write('Podaj numer studenta'), nl,
	read(Nr), nl,
	write('Podaj nazwisko studenta'), nl,
	read(Nazw), nl,
	write('Podaj rok studenta'), nl,
	read(Rok), nl,
	assertz(student(Nr,Nazw,Rok)),
	write('Nastepny student?'),
	read(Odp),
	( Odp = t,
          wykonaj(1)
        ; menu
        ).


wykonaj(2):-
	write('Podaj rok'), nl,
	read(Rok),!, nl,
	write('Podaj nazwe wykladu'), nl,
	read(Wykl),!, nl,
	assertz(wyklad(Rok, Wykl)),
	write('Nastepny wyklad?'), nl,
	read(Odp),
	( Odp = t,
          wykonaj(2)
        ;
          menu
        ).

wykonaj(3) :-
	write('Podaj nazwisko'), nl,
	read(Nazw), !, nl,
	( retract(student(_,Nazw,_)), !
        ;
	  write('Nie bylo nazwiska w bazie')
        ), nl,
	write('Nastepny student?'), nl,
	read(Odp), nl,
	( Odp = t,
          wykonaj(3)
        ;
          menu
        ).

wykonaj(4) :-
	write('Podaj wyklad'),
	read(Wykl), !,
	( retract(wyklad(_,Wykl))
        ;
          write('Nie bylo wykladu w bazie')
        ),
	write('Nastepny wyklad?'),
	read(Odp),
	( Odp = t,
          wykonaj(4)
        ;
        menu
        ).

wykonaj(5) :-
	menu_przegladania.

wykonaj(6):-
	write('Zachowac zmiany w bazie?'),
	read(X),
	( X = t,
	  tell('baza'),
	  zapis(stud),
	  zapis(wykl),
	  told
        ;
        !
        ).

menu_przegladania:-
	write(' 1. - spis wykladow studenta'), nl,
	write(' 2. - koniec przegladania'),nl,
	read(X),
	przegladaj(X).

przegladaj(1):-
	write('Podaj nazwisko studenta'),
	read(Nazw),
	podaj_wyklady(Nazw),nl,
	write('Przegladamy dalej studentow?'),
	read(Odp),
	( Odp = t,
          przegladaj(1)
        ; menu_przegladania
        ).

przegladaj(2) :-
	menu.

podaj_wyklady(Nazw) :-
	student(_,Nazw,Rok),
	wyklad(Rok, Wykl),
	write(Wykl),nl,
	fail.

podaj_wyklady(_).


zapis(stud):-
	student(X,Y,Z),
	write(student(X,Y,Z)), write('.'), nl,
	fail.
zapis(stud).
zapis(wykl):-
	wyklad(X,Y),
	write(wyklad(X,Y)), write('.'), nl,
	fail.
zapis(wykl).
</PRE></BODY></HTML>
