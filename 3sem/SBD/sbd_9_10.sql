-- Temat 9 – PL/SQL – podstawy
-- 1.	Napisz prosty program w PL/SQL. Zadeklaruj zmienną, przypisz na tą zmienną 
-- 		liczbę rekordów w tabeli EMP i wypisz uzyskany wynik w postaci 
-- 		np. "W tabeli jest 10 osób".
set serveroutput on;

DECLARE
	 ile integer(8);   
   output VARCHAR2(25);
BEGIN
    select count(*) into ile from emp;
    output := 'W tabeli jest ' || ile || 'osob';
	  dbms_output.put_line ( output );
END;

-- 2.	Sprawdź w bloku PL/SQL liczbę pracowników z tabeli EMP. Jeśli liczba jest mniejsza 
-- 		niż 16, wstaw pracownika Kowalskiego i wypisz odpowiedni komunikat. W przeciwnym 
-- 		przypadku wypisz komunikat informujący o tym, że nie wstawiono danych.

DECLARE
	 ile integer(8);   
   output VARCHAR2(25);
BEGIN
    select count(*) into ile from emp;
    
    IF ile < 16 THEN
      INSERT INTO EMP VALUES
        (888, 'KOVALSKI',  'ANALYST',   666,
        TO_DATE('1982-12-09'), 3000, NULL, 20);
        
        output := 'Dodano KOVALSKI';
    ELSE
      output := 'Nie dodano KOVALSKI';
    END IF;
        
	  dbms_output.put_line ( output );
END;

-- 3.	Napisz procedurę służącą do wstawiania działów do tabeli Dept. Procedura będzie 
-- 		pobierać jako parametry: nr_działu, nazwę i lokalizację. Należy sprawdzić, czy 
-- 		dział o takiej nazwie lub lokalizacji już istnieje. Jeżeli istnieje, to nie 
-- 		wstawiamy nowego rekordu. 

create or replace
PROCEDURE WSTAW (did IN integer, n IN VARCHAR2, l IN VARCHAR2) AS
	 ile integer(8);   
   output VARCHAR2(25);
	BEGIN
    select count(*) into ile from dept where dname = n or loc = l or deptno = did;
  
    IF ile < 1 THEN
      INSERT INTO DEPT VALUES (did, n, l);
        
        output := 'Dodano '||n;
    ELSE
      output := 'Nie Dodano '||n;
    END IF;
  
		dbms_output.put_line ( output );
	END;


  execute WSTAW(10,'aaa','aaa');
  execute WSTAW(99,'aaa','aaa');


-- 4.	Napisz procedurę służącą do wstawiania pracowników. Jako parametry podamy: 
-- 		nr działu i nazwisko. Procedura powinna sprawdzić, czy podany dział 
-- 		istnieje (w przeciwnym przypadku zgłaszamy błąd) i wyliczyć pracownikowi 
-- 		pensję równą minimalnemu wynagrodzeniu w jego dziale. Procedura powinna również 
-- 		nadać EMPNO nowemu pracownikowi obliczone jako maksymalne EMPNO w tabeli + 1.

create or replace
PROCEDURE WSTAWEMP (did IN integer, n IN VARCHAR2) AS
	ile integer(8);   
	output VARCHAR2(25);
BEGIN
	select count(*) into ile from dept where deptno = did;

	IF ile > 0 THEN
	
	INSERT INTO EMP VALUES
			(
        (select max(empno)+1 from emp), 
        n,   
        'DERP', 
        NULL,
        TO_DATE('1981-11-17'), 
        (select min(sal) from emp where deptno = did), 
        NULL, 
        did
      );
	
		output := 'Dodano '||n;
	ELSE
		output := 'Nie Dodano '||n;
	END IF;

	dbms_output.put_line ( output );
END;

execute WSTAWEMP(11,'JanGrzyb');
execute WSTAWEMP(10,'JanGrzyb');

-- 5.	Utwórz tabelę Magazyn (IdPozycji, Nazwa, Ilosc) zawierającą ilości 
-- 		poszczególnych towarów w magazynie i wstaw do niej kilka przykładowych rekordów.
-- 		W bloku PL/SQL sprawdź, którego artykułu jest najwięcej w magazynie i zmniejsz 
-- 		ilość tego artykułu o 5 (jeśli stan jest większy lub równy 5, w przeciwnym wypadku zgłoś błąd).

CREATE TABLE MAG
       (IDP NUMBER(4) NOT NULL,
        PN VARCHAR2(40),
        PC NUMBER(7, 2)
        );
        
DELETE FROM MAG;        
INSERT INTO MAG VALUES (1,'GRUSZKI',2);        
INSERT INTO MAG VALUES (2,'BETONIARKI',200);        
INSERT INTO MAG VALUES (3,'KRASNALE OGRODOWE',4);        
INSERT INTO MAG VALUES (4,'POMPY DO BETONU',20);        
INSERT INTO MAG VALUES (5,'WIADRA',100);    
INSERT INTO MAG VALUES (6,'KOSTKI MASŁA',250);      
INSERT INTO MAG VALUES (7,'WORKI Z PIASKIEM',250);      

DECLARE
	 ile integer(8);   
	 mag_idp integer(8);   
   output VARCHAR2(45);
BEGIN
    select IDP, PC, PN into mag_idp,ile,output from MAG WHERE idp = ( 
      select min(idp) from mag where pc = ( 
        select max(PC) from mag 
      ) 
    );
    
    IF ile > 4 THEN
        UPDATE mag set PC = PC - 5 where IDP = mag_idp;
        
        output := output || '('||mag_idp||'): ' || ile || ' -> ' || (ile-5);
    ELSE
      output := 'Za malo towaru: '||output;
    END IF;
        
	  dbms_output.put_line ( output );
END;

select * from mag;

--	Temat 10 – PL/SQL – kursory

--	1.	Przy pomocy kursora przejrzyj wszystkich pracowników i zmodyfikuj wynagrodzenia tak, 
--		aby osoby zarabiające mniej niż 1000 miały zwiększone wynagrodzenie o 10%, natomiast 
--		osoby zarabiające powyżej 1500 miały zmniejszone wynagrodzenie o 10%. Wypisz na ekran 
--		każdą wprowadzoną zmianę.

set serveroutput on;

DECLARE
--	 CURSOR emp_c IS SELECT empno,ename,sal from emp;
--   emp_ename emp.ename%type;
--   emp_sal emp.sal%type;
--   emp_empno emp.empno%type;


	 CURSOR emp_c IS SELECT * from emp;
   emp_row emp%rowtype;
   
   output VARCHAR2(25);
BEGIN   
		OPEN emp_c;
		
    
		LOOP
			--FETCH emp_c INTO emp_empno,emp_ename,emp_sal;
			FETCH emp_c INTO emp_row;
			EXIT WHEN emp_c%NOTFOUND;
      
--      output := emp_ename || ':' || emp_sal;
--      dbms_output.put_line ( output );

      if emp_row.sal < 1000 then
        update emp set sal = sal * 1.1 where empno = emp_row.empno;
        output := emp_row.ename || ':' || emp_row.sal || ' -> ' || (emp_row.sal*1.1);
      elsif emp_row.sal > 1500 then
        update emp set sal = sal * 0.9 where empno = emp_row.empno;
        output := emp_row.ename || ':' || emp_row.sal || ' -> ' || (emp_row.sal*0.9);
      end if;

      
      dbms_output.put_line ( output );
		END LOOP;
    
		CLOSE emp_c;
END;

--	2.	Przerób kod z zadania 1 na procedurę tak, aby wartości zarobków (1000 i 1500) nie 
--		były stałe, tylko były parametrami procedury.

create or replace
PROCEDURE ADJSAL( minsal IN integer, maxsal IN integer ) AS

	 CURSOR emp_c IS SELECT * from emp;
   emp_row emp%rowtype;
   
   output VARCHAR2(25);
BEGIN   
		OPEN emp_c;
		
    
		LOOP
			FETCH emp_c INTO emp_row;
			EXIT WHEN emp_c%NOTFOUND;
      
      if emp_row.sal < minsal then
        update emp set sal = sal * 1.1 where empno = emp_row.empno;
        output := emp_row.ename || ':' || emp_row.sal || ' -> ' || (emp_row.sal*1.1);
      elsif emp_row.sal > maxsal then
        update emp set sal = sal * 0.9 where empno = emp_row.empno;
        output := emp_row.ename || ':' || emp_row.sal || ' -> ' || (emp_row.sal*0.9);
      end if;

      
      dbms_output.put_line ( output );
		END LOOP;
    
		CLOSE emp_c;

END ADJSAL;

execute adjsal( 500, 2000);

--	3.	W procedurze sprawdź średnią wartość zarobków z tabeli EMP z działu określonego 
--		parametrem procedury. Następnie należy dać prowizję (comm) tym pracownikom tego 
--		działu, którzy zarabiają poniżej średniej. Prowizja powinna wynosić 5% ich 
--		miesięcznego wynagrodzenia.

create or replace
PROCEDURE ADJCOM( deptno IN integer ) AS

	 CURSOR emp_c IS SELECT * from emp where deptno = deptno and sal < ( select avg(sal) from emp where deptno = deptno );
   emp_row emp%rowtype;
   
   output VARCHAR2(25);
BEGIN   
		OPEN emp_c;
		

		LOOP
			FETCH emp_c INTO emp_row;
			EXIT WHEN emp_c%NOTFOUND;
      
      update emp set comm = coalesce(comm,0) + 0.05 * sal where empno = emp_row.empno;
      output := emp_row.ename || ':' || coalesce(emp_row.comm,0) || ' -> ' || (  coalesce(emp_row.comm,0) + 0.05 * emp_row.sal);
      
      dbms_output.put_line ( output );
		END LOOP;
    
		CLOSE emp_c;

END ADJCOM;

execute adjcom(10);

--	4.	(bez kursora) Utwórz tabelę Magazyn (IdPozycji, Nazwa, Ilosc) zawierającą ilości 
--		poszczególnych towarów w magazynie i wstaw do niej kilka przykładowych rekordów.

CREATE TABLE MAG
       (IDP NUMBER(4) NOT NULL,
        PN VARCHAR2(40),
        PC NUMBER(7, 2)
        );
        
DELETE FROM MAG;        
INSERT INTO MAG VALUES (1,'GRUSZKI',2);        
INSERT INTO MAG VALUES (2,'BETONIARKI',200);        
INSERT INTO MAG VALUES (3,'KRASNALE OGRODOWE',4);        
INSERT INTO MAG VALUES (4,'POMPY DO BETONU',20);        
INSERT INTO MAG VALUES (5,'WIADRA',100);    
INSERT INTO MAG VALUES (6,'KOSTKI MASŁA',250);      
INSERT INTO MAG VALUES (7,'WORKI Z PIASKIEM',250);   

--		W bloku Transact-SQL sprawdź, którego artykułu jest najwięcej w magazynie i zmniejsz 
--		ilość tego artykułu o 5 (jeśli stan jest większy lub równy 5, w przeciwnym wypadku 
--		zgłoś błąd).

DECLARE
	 CURSOR mag_c IS SELECT * from mag;
   mag_row mag%rowtype;
   
   maxc integer := 0;
   maxid integer := 0;
   
   output VARCHAR2(45);
BEGIN   
		OPEN mag_c;
		
		LOOP
			FETCH mag_c INTO mag_row;
			EXIT WHEN mag_c%NOTFOUND;

      if maxc < mag_row.pc then
        maxc := mag_row.pc;
        maxid := mag_row.idp;
        output := mag_row.pn;
      end if;

		END LOOP;
    
    CLOSE mag_c;
    
    if maxc > 5 then
      update mag set pc = pc - 5 where idp = maxid;
      output := output || ': ' || maxc || ' -> ' || (maxc-5);
    else
      output := 'za malo towaru: ' || output ;
    end if;
    
    dbms_output.put_line ( output );
end;

--	5.	Przerób kod z zadania 4 na procedurę, której będziemy mogli podać wartość, o którą 
--		zmniejszamy stan (zamiast wpisanego „na sztywno” 5).

CREATE OR REPLACE
PROCEDURE ADJMAG
( adjVal IN NUMBER DEFAULT 5
) AS
	 CURSOR mag_c IS SELECT * from mag;
   mag_row mag%rowtype;
   
   maxc integer := 0;
   maxid integer := 0;
   
   output VARCHAR2(45);
BEGIN   
		OPEN mag_c;
		
		LOOP
			FETCH mag_c INTO mag_row;
			EXIT WHEN mag_c%NOTFOUND;

      if maxc < mag_row.pc then
        maxc := mag_row.pc;
        maxid := mag_row.idp;
        output := mag_row.pn;
      end if;

		END LOOP;
    
    CLOSE mag_c;
    
    if maxc > adjVal then
      update mag set pc = pc - adjVal where idp = maxid;
      output := output || ': ' || maxc || ' -> ' || (maxc-adjVal);
    else
      output := 'za malo towaru: ' || output ;
    end if;
    
    dbms_output.put_line ( output );
END ADJMAG;

execute ADJMAG(100);