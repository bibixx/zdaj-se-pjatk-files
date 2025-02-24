1. SELECT s.nazwisko, o.ocena, o.idprzedmiot
FROM sj_student s, sj_ocena o, sj_przedmiot p
WHERE o.idstudent = s.idstudent AND o.idprzedmiot = (SELECT idprzedmiot FROM sj_przedmiot WHERE przedmiot = 'Relacyjne bazy danych');

2. SELECT p.przedmiot, AVG(o.ocena)
FROM sj_przedmiot p INNER JOIN sj_ocena o ON p.idprzedmiot = o.idprzedmiot
GROUP BY p.przedmiot
HAVING COUNT(*)>3;

3. SELECT s.idstudent, s.nazwisko
FROM sj_student s, (SELECT idstudent, COUNT(*) FROM sj_ocena WHERE EXTRACT(YEAR FROM datawystawienia) = 2013 AND OCENA = 2 GROUP BY idstudent HAVING COUNT(*)>2) d
WHERE s.idstudent = d.idstudent;

4. SELECT s.idstudent, s.nazwisko, o.ocena
FROM sj_student s INNER JOIN sj_ocena o ON o.idstudent = s.idstudent
WHERE o.idprzedmiot = (SELECT idprzedmiot FROM sj_przedmiot WHERE symbol='RBD') AND o.ocena > (SELECT ocena FROM sj_ocena WHERE idprzedmiot = (SELECT idprzedmiot FROM sj_przedmiot WHERE symbol='RBD') AND idstudent = (SELECT idstudent FROM sj_student WHERE imie = 'Salomea' AND nazwisko = 'Œliwka'));

5. SELECT s.nazwisko
FROM sj_student s INNER JOIN sj_ocena o ON s.idstudent = o.idstudent
WHERE s.akt_semestr = 1 AND EXTRACT(YEAR FROM o.datawystawienia) = 2013
AND (SELECT AVG(ocena) FROM sj_ocena o, sj_student s
      WHERE EXTRACT (YEAR FROM datawystawienia) = 2013 AND o.idstudent = s.idstudent AND s.akt_semestr=1 GROUP BY o.idstudent) =
      (SELECT MAX(AVG(ocena)) FROM sj_ocena o, sj_student s
        WHERE EXTRACT (YEAR FROM datawystawienia) = 2013 AND o.idstudent = s.idstudent AND s.akt_semestr=1 GROUP BY o.idstudent)
ORDER BY s.nazwisko;

6. SELECT s.nazwisko, s.idstudent, p.przedmiot, o.ocena
FROM sj_student s INNER JOIN sj_ocena o ON s.idstudent = o.idstudent INNER JOIN sj_przedmiot p ON p.idprzedmiot = o.idprzedmiot
WHERE o.ocena < (SELECT AVG(ocena) FROM sj_ocena GROUP BY idstudent HAVING sj_ocena.idstudent = s.idstudent)
GROUP BY s.nazwisko, s.idstudent, p.przedmiot, o.ocena;

7. SELECT s.nazwisko
FROM sj_student s INNER JOIN sj_ocena o ON o.idstudent = s.idstudent
WHERE (SELECT COUNT(ocena) FROM sj_ocena WHERE o.idstudent = sj_ocena.idstudent AND EXTRACT(YEAR FROM o.datawystawienia)
        = EXTRACT(YEAR FROM sj_ocena.datawystawienia)) > (SELECT COUNT (ocena) FROM sj_ocena WHERE EXTRACT(YEAR FROM o.datawystawienia)
= EXTRACT(YEAR FROM sj_ocena.datawystawienia))/(SELECT COUNT (idstudent) FROM sj_student)
GROUP BY s.nazwisko;

8. SELECT p.przedmiot, AVG(o.ocena), EXTRACT (YEAR FROM o.datawystawienia)
FROM sj_ocena o INNER JOIN sj_przedmiot p ON p.idprzedmiot = o.idprzedmiot
WHERE o.idprzedmiot IN (SELECT idprzedmiot FROM sj_przedmiot WHERE symbol IN ('AM', 'MAD', 'ALG'))
GROUP BY EXTRACT (YEAR FROM o.datawystawienia), p.przedmiot
HAVING AVG(o.ocena) = (SELECT MAX(AVG(o.ocena)) FROM sj_ocena WHERE EXTRACT (YEAR FROM o.datawystawienia) = EXTRACT (YEAR FROM datawystawienia));

9. SELECT s.nazwisko
FROM sj_student s INNER JOIN sj_ocena o ON o.idstudent = s.idstudent
WHERE NOT EXISTS (SELECT 1 FROM sj_ocena GROUP BY idstudent HAVING COUNT(*)=0)
GROUP BY s.nazwisko;

10. SELECT DISTINCT p.przedmiot
FROM sj_przedmiot p INNER JOIN sj_ocena o ON p.idprzedmiot = o.idprzedmiot
WHERE EXISTS (SELECT 1 FROM sj_ocena GROUP BY idprzedmiot HAVING MAX(ocena)<4);