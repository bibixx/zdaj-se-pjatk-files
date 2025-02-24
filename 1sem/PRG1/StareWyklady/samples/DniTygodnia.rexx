/* Podaje aktualny dzien tygodnia */

dni[] = {"poniedziaˆek", "wtorek", "˜roda", "czwartek", "piatek", "sobota", "niedziela" };
ldni = date('B') /* liczba dni od 1 stycznia 0001 */
nrDnia = ldni // 7 + 1
say 'Dzisiaj jest :' dni[nrDnia]

