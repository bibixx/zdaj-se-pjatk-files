/* 
   Odmiana nieosobowych rzeczownik�w m�sko�ywotnych (np. 'baran', 's�o�',
   'rak', 'lew', 'tygrys'...).
  
   Aby wywola� program, nale�y zada� cel 'zacznij'.
*/

/* Informujemy interpreter, �e predykaty forma i temat s�
   dynamiczne, tzn. mog� by� dol�czane do bazy i usuwane z niej. */
:- dynamic forma/4.
:- dynamic temat/2.

/* W wyrazie W zamienia ko�c�wk� K1 na K2. Wynikiem jest nowy wyraz U.
   Np. dla celu zamien_koncowke('ko�', '�', 'nia', U) U przyjmie 
   warto�� 'konia'.
   Predykat w og�le si� nie powodzi, je�li K1 nie jest ko�c�wk� W. */

zamien_koncowke(W, K1, K2, U) :-
    atom_length(K1, DK1),      /* DK1 to d�ugo�� ko�c�wki K1 */
    sub_atom(W, LiczbaZnakowBezK1, DK1, 0, K1),
    sub_atom(W, 0, LiczbaZnakowBezK1, _, Poczatek), 
                         /* Poczatek to wyraz W bez ko�c�wki K1 */
    concat(Poczatek, K2, U). /* ��czymy wyraz z now� ko�c�wk�.*/


/* Wyznacza temat T dla rzeczownika W */
temat(W, T) :-
    ( zamien_koncowke(W, '�', 'ci', T), ! ;
      zamien_koncowke(W, '�', 'ni', T), ! ;  /* np. 'koni' dla 'ko�' */
      zamien_koncowke(W, '�', 'si', T), ! ;
      T = W ).


/* forma(P, L, W, F) - F jest form� rzeczownika W dla przypadku P
   i liczby L */

forma(dope�niacz, liczba_pojedyncza, W, F) :-
    temat(W, T), concat(T, 'a', F).  /* do tematu dodajemy ko�c�wk� 'a' */

forma(celownik, liczba_pojedyncza, W, F) :-
    temat(W, T), concat(T, 'owi', F).

forma(narz�dnik, liczba_pojedyncza, W, F) :-
    temat(W, T), concat(T, 'em', F).

forma(miejscownik, liczba_pojedyncza, W, F) :-
    temat(W, T),
    /* mi�kkotematowe (kt�rych temat ko�czy si� na 'i') maj� w miejscowniku
       ko�c�wk� 'iu' */
    (zamien_koncowke(T, 'i', 'iu', F), ! ;   
     concat(T, 'ie', F)).

forma(mianownik, liczba_mnoga, W, F) :-
    temat(W, T),
    (zamien_koncowke(T, 'i', 'ie', F), !;
     concat(T, 'y', F)).

forma(dope�niacz, liczba_mnoga, W, F) :-
    temat(W, T),
    (zamien_koncowke(T, 'i', 'i', F), !;
     concat(T, '�w', F)).

forma(celownik, liczba_mnoga, W, F) :-
    temat(W, T), concat(T, 'om', F).

forma(narz�dnik, liczba_mnoga, W, F) :-
    temat(W, T), concat(T, 'ami', F).

forma(miejscownik, liczba_mnoga, W, F) :-
    temat(W, T), concat(T, 'ach', F).


/* r�ne og�lne regu�y */
forma(mianownik, liczba_pojedyncza, W, W).

/* biernik lp. jest r�wny dope�niaczowi lp. */
forma(biernik, liczba_pojedyncza, W, F) :-
    forma(dope�niacz, liczba_pojedyncza, W, F).

forma(wo�acz, liczba_pojedyncza, W, F) :-
    forma(miejscownik, liczba_pojedyncza, W, F).

forma(biernik, liczba_mnoga, W, F) :-
    forma(mianownik, liczba_mnoga, W, F).

forma(wo�acz, liczba_mnoga, W, F) :-
    forma(mianownik, liczba_mnoga, W, F).


menu :-
    nl,
    write('f - sprawd� form� fleksyjn�'),nl,
    write('w - wprowad� wyj�tek'),nl,
    write('t - wprowad� specjalny temat'),nl,
    write('k - koniec dzia�ania'),nl.

wykonaj(102 /* f */) :-
    write('podaj wyraz, przypadek i liczb� - ka�dy wprowadzany atom zako�cz kropk� i ENTEREM'), nl,
    read(W),
    read(P),
    read(L),
    ( forma(P, L, W, U), !, write('znaleziona forma to: '), write(U) ;
      write('nie znaleziono formy (by� mo�e wpisa�e� mnoga zamiast liczba_mnoga?)')), nl.
    
wykonaj(119 /* w */) :-
    write('podaj wyraz, przypadek, liczb� i w�a�ciw� form�'), nl,
    read(W),
    read(P),
    read(L),
    read(F),
    asserta(forma(P, L, W, F) :- !).

wykonaj(116 /* t */) :-
    write('podaj wyraz i w�a�ciwy temat'), nl,
    read(W),
    read(T),
    asserta(temat(W, T)).

zacznij :-
    repeat,
    menu,
    get_single_char(C),
    (C = 107 /* k */; wykonaj(C), fail).












