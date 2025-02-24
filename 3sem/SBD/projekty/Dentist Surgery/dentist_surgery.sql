--
-- Copyright (c) Damian Fok, 2006.
--
--
--           Dentist Surgery Data Base
--
-- Polish-Japanese Institute Of Information Technology
--


set serveroutput on
exec dbms_output.enable(60000);


DROP TABLE Assistant CASCADE CONSTRAINTS;
DROP TABLE Patient CASCADE CONSTRAINTS;
DROP TABLE Dentist CASCADE CONSTRAINTS;
DROP TABLE Disease CASCADE CONSTRAINTS;
DROP TABLE Category_of_disease CASCADE CONSTRAINTS;
DROP TABLE Dentist_Specialization CASCADE CONSTRAINTS;
DROP TABLE Specialization CASCADE CONSTRAINTS;
DROP TABLE Card CASCADE CONSTRAINTS;


----------------------------------------------------------------------------
-----------------------------  CRATING TABLES  -----------------------------
----------------------------------------------------------------------------


CREATE TABLE Assistant
       (Id_Assistant NUMBER(4) NOT NULL,
        Name_Assistant VARCHAR2(20),
        Surname_Assistant VARCHAR2(20),
        Salary_Assistant NUMBER(7, 2),
	Phone_Assistant NUMBER(20),
	Address_Assistant VARCHAR2(30),
        Hiredate_Assistant DATE,
	PRIMARY KEY(Id_Assistant));


CREATE TABLE Patient
       (Id_Patient NUMBER(4) NOT NULL,
        Name_Patient VARCHAR2(20),
        Surname_Patient VARCHAR2(20),
	Address_Patient VARCHAR2(30),
	Phone_Patient NUMBER(20),
	Birth_Date DATE,
	email VARCHAR2(20),
	PRIMARY KEY(Id_Patient));


CREATE TABLE Dentist
       (Id_Dentist NUMBER(4) NOT NULL,
        Name_Dentist VARCHAR2(20),
        Surname_Dentist VARCHAR2(20),
        Degree VARCHAR2(10),
        Salary_Dentist NUMBER(7, 2),
	Phone_Dentist NUMBER(10),
	Address_Dentist VARCHAR2(30),
        Hiredate_Dentist DATE,
	PRIMARY KEY(Id_Dentist));


CREATE TABLE Specialization
       (Id_Specialization NUMBER(4) NOT NULL,
        Name_of_specialization VARCHAR2(20),
	PRIMARY KEY(Id_Specialization));


CREATE TABLE Dentist_Specialization
       (Date_from DATE NOT NULL,
        Id_Specialization NUMBER(4),
        Id_Dentist NUMBER(4),
	FOREIGN KEY(Id_Specialization) REFERENCES Specialization(Id_Specialization),
	FOREIGN KEY(Id_Dentist) REFERENCES Dentist(Id_Dentist));


CREATE TABLE Category_of_disease
       (Id_category NUMBER(4) NOT NULL,
        Name_of_category VARCHAR2(20),
        Description_of_category VARCHAR2(30),
        Id_Specialization NUMBER(4),
	PRIMARY KEY(Id_category),
	FOREIGN KEY(Id_Specialization) REFERENCES Specialization(Id_Specialization));


CREATE TABLE Disease
       (Id_Disease NUMBER(4) NOT NULL,
        Name_of_disease VARCHAR2(20),
        Description_of_disease VARCHAR2(30),
	Id_category NUMBER(4),
	PRIMARY KEY(Id_Disease),
	FOREIGN KEY(Id_category) REFERENCES Category_of_disease(Id_category));


CREATE TABLE Card
       (Id_card NUMBER(4) NOT NULL,
        Date_of_registration DATE,
        Id_Patient NUMBER(4),
        Id_Dentist NUMBER(4),
        Id_Disease NUMBER(4),
        Id_Assistant NUMBER(4),
	PRIMARY KEY(Id_Card),
	FOREIGN KEY(Id_Dentist) REFERENCES Dentist(Id_Dentist),
	FOREIGN KEY(Id_Patient) REFERENCES Patient(Id_Patient),
	FOREIGN KEY(Id_Disease) REFERENCES Disease(Id_Disease),
	FOREIGN KEY(Id_Assistant) REFERENCES Assistant(Id_Assistant));



----------------------------------------------------------------------------
--------------------------------  TRIGGERS  --------------------------------
----------------------------------------------------------------------------


----> NO 1. ----> Automatic ID for Dentist
CREATE OR REPLACE TRIGGER auto_id_dentist
BEFORE INSERT ON Dentist
FOR EACH ROW
BEGIN
SELECT NVL(MAX(Id_Dentist)+1,1)
INTO :NEW.Id_Dentist
FROM Dentist;
END;
/

----> NO 2. ----> Automatic ID for Assistant
CREATE OR REPLACE TRIGGER auto_id_assistant
BEFORE INSERT ON Assistant
FOR EACH ROW
BEGIN
SELECT NVL(MAX(Id_Assistant)+1,1)
INTO :NEW.Id_Assistant
FROM Assistant;
END;
/

----> NO 3. ----> Automatic ID for Patient
CREATE OR REPLACE TRIGGER auto_id_patient
BEFORE INSERT ON Patient
FOR EACH ROW
BEGIN
SELECT NVL(MAX(Id_Patient)+1,1)
INTO :NEW.Id_Patient
FROM Patient;
END;
/

----> NO 4. ----> Automatic ID for Specialization
CREATE OR REPLACE TRIGGER auto_id_specialization
BEFORE INSERT ON Specialization
FOR EACH ROW
BEGIN
SELECT NVL(MAX(Id_Specialization)+1,1)
INTO :NEW.Id_Specialization
FROM Specialization;
END;
/

----> NO 5. ----> Automatic ID for Category
CREATE OR REPLACE TRIGGER auto_id_category
BEFORE INSERT ON Category_of_disease
FOR EACH ROW
BEGIN
SELECT NVL(MAX(Id_category)+1,1)
INTO :NEW.Id_category
FROM Category_of_disease;
END;
/

----> NO 6. ----> Automatic ID for Disease
CREATE OR REPLACE TRIGGER auto_id_disease
BEFORE INSERT ON Disease
FOR EACH ROW
BEGIN
SELECT NVL(MAX(Id_Disease)+1,1)
INTO :NEW.Id_Disease
FROM Disease;
END;
/


----> NO 7. ----> Check if given degree is correct
CREATE OR REPLACE TRIGGER degree_filter
BEFORE INSERT OR UPDATE OF Degree ON Dentist
FOR EACH ROW
WHEN(NEW.Degree != 'DR' AND NEW.Degree != 'DR HAB' AND NEW.Degree != 'PROF')
BEGIN
raise_application_error(-20225,'Please add grades for: "DR", "DR HAB" or "PROF"');
END;
/

----> NO 8. ----> Gives information about date of modification
CREATE OR REPLACE TRIGGER dateinfo
AFTER INSERT OR UPDATE ON Dentist
FOR EACH ROW
DECLARE
d DATE;
BEGIN
       d:= SYSDATE;
       DBMS_output.put_line('Table Dentist has been modified! Date: '||d);
END;
/

----> NO 9. ----> Does not allow to give a dentist hire date which is higher than system date.
CREATE OR REPLACE TRIGGER wrong_hiredate_dentist
BEFORE INSERT OR UPDATE OF Hiredate_Dentist ON Dentist
FOR EACH ROW
BEGIN
IF (:NEW.Hiredate_Dentist > SYSDATE) THEN
DBMS_output.put_line('Incorect hire date. Please try again.');
END IF;
END;
/

----> NO 10. ----> Does not allow to give an assistant hire date which is higher than system date.
CREATE OR REPLACE TRIGGER wrong_hiredate_assistant
BEFORE INSERT OR UPDATE OF Hiredate_Assistant ON Assistant
FOR EACH ROW
BEGIN
IF (:NEW.Hiredate_Assistant > SYSDATE) THEN
DBMS_output.put_line('Incorect hire date. Please try again.');
END IF;
END;
/

----> NO 11. ----> Informs us that the given salary for assistant is too big.
CREATE OR REPLACE TRIGGER toBigSal_assistant
AFTER INSERT OR UPDATE OF Salary_Assistant ON Assistant
FOR EACH ROW
BEGIN
IF :NEW.Salary_Assistant>3000
THEN
DBMS_output.put_line('For an assistant job, given salary is to big.');
END IF;
End;
/

----> NO 12. ----> Informs us that the given salary for dentist is too big.
CREATE OR REPLACE TRIGGER toBigSal_dentist
AFTER INSERT OR UPDATE OF Salary_Dentist ON Dentist
FOR EACH ROW
BEGIN
IF :NEW.Salary_Dentist>6000
THEN
DBMS_output.put_line('For a dentist job, given salary is to big.');
END IF;
End;
/

----> NO 13. ----> Does not allow us to give Null value for dentist ID.
CREATE OR REPLACE TRIGGER NotNullDentist
AFTER INSERT OR UPDATE ON Dentist
FOR EACH ROW
BEGIN
if (:NEW.Id_Dentist is NULL)
then
raise_application_error(-100,'ID can not be null !');
END IF;
END;
/

----> NO 14. ----> Does not allow us to give Null value for assistant ID.
CREATE OR REPLACE TRIGGER NotNullAssistant
AFTER INSERT OR UPDATE ON Assistant
FOR EACH ROW
BEGIN
if (:NEW.Id_Assistant is NULL)
then
raise_application_error(-100,'ID can not be null !');
END IF;
END;
/

----> NO 15. ----> Cards "younger" than 2 months can not be deleted.
CREATE OR REPLACE TRIGGER nodelete
BEFORE DELETE ON Card
FOR EACH ROW
BEGIN
if (:OLD.Date_of_registration>(Sysdate-60))
then
raise_application_error(-100,'This card can not be deleted from data base. Her validity is at least 2 months.');
END IF;
END;
/

----> NO 16. ----> Automatic ID for Card
CREATE OR REPLACE TRIGGER auto_id_card
BEFORE INSERT ON Card
FOR EACH ROW
BEGIN
SELECT NVL(MAX(Id_Card)+1,1)
INTO :NEW.Id_Card
FROM Card;
END;
/

----------------------------------------------------------------------------
-------------------------------  PROCEDURES  -------------------------------
----------------------------------------------------------------------------


----> NO 1. ----> Prevent adding an assistant if he alredy exists in data base.
create or replace procedure ADD_ASSISTANT(N CHAR, SN CHAR, SAL NUMBER, PH NUMBER, ADS CHAR, HD DATE)
as
num integer;
assistant_already_added exception;

BEGIN
       select count(*) into num 
       from Assistant
       where Name_Assistant=N and Surname_Assistant=SN and Phone_Assistant=PH;

if num>0 then raise assistant_already_added;

       else
               insert into Assistant values(1, N, SN, SAL, PH, ADS, HD);
               DBMS_output.put_line('Assistant '||SN||' '||N||' has been added.');
               commit;
end if;

exception
       when assistant_already_added then
       DBMS_output.put_line('This Assistant already exists in data base !');
END;
/


----> NO 2. ----> Prevent adding a dentist if he alredy exists in data base.
create or replace procedure ADD_DENTIST(N CHAR, SN CHAR, DG CHAR, SAL NUMBER, PH NUMBER, ADS CHAR, HD DATE)
as
num integer;
dentist_already_added exception;

BEGIN
       select count(*) into num 
       from Dentist
       where Name_Dentist=N and Surname_Dentist=SN and Phone_Dentist=PH;

if num>0 then raise dentist_already_added;

       else
               insert into Dentist values(1, N, SN, DG, SAL, PH, ADS, HD);
               DBMS_output.put_line('Dentist '||SN||' '||N||' has been added.');
               commit;
end if;

exception
       when dentist_already_added then
       DBMS_output.put_line('This Dentist already exists in data base !');
END;
/


----> NO 3. ----> Prevent adding a patient if he alredy exists in data base.
create or replace procedure ADD_PATIENT(N CHAR, SN CHAR, ADS CHAR, PH NUMBER, BD DATE, EM CHAR)
as
num integer;
patient_already_added exception;

BEGIN
       select count(*) into num 
       from Patient
       where Name_Patient=N and Surname_Patient=SN and Phone_Patient=PH;

if num>0 then raise patient_already_added;

       else
               insert into Patient values(1, N, SN, ADS, PH, BD, EM);
               DBMS_output.put_line('Patient '||SN||' '||N||' has been added.');
               commit;
end if;

exception
       when patient_already_added then
       DBMS_output.put_line('This Patient already exists in data base !');
END;
/


----> NO 4. ----> Removing Assistant from data base
create or replace procedure REMOVE_ASSISTANT(N CHAR, SN CHAR, PH NUMBER)
as
num1 integer;
num2 integer;
id integer;
i integer;
no_assistant exception;

BEGIN
     select count(*) into num1
     from Assistant
     where Name_Assistant=N and Surname_Assistant=SN and Phone_Assistant=PH;

if num1 = 0
then
       raise no_assistant;
else


       DELETE FROM Assistant
       WHERE Name_Assistant=N and Surname_Assistant=SN and Phone_Assistant=PH;
       DBMS_output.put_line('Assistant with index nr '||id||' has been deleted!');


end if;


exception
       when no_assistant then
       DBMS_output.put_line('Assistant with that data does not exist !');

END;
/



----> NO 5. ----> Removing Dentist from data base
create or replace procedure REMOVE_DENTIST(N CHAR, SN CHAR, PH NUMBER)
as
num1 integer;
num2 integer;
id integer;
i integer;
no_dentist exception;

BEGIN
     select count(*) into num1
     from Dentist
     where Name_Dentist=N and Surname_Dentist=SN and Phone_Dentist=PH;

if num1 = 0
then
       raise no_dentist;
else

       DELETE FROM Dentist
       WHERE Name_Dentist=N and Surname_Dentist=SN and Phone_Dentist=PH;
       DBMS_output.put_line('Dentist with index nr '||id||' has been deleted!');


end if;


exception
       when no_dentist then
       DBMS_output.put_line('Dentist with that data does not exist !');

END;
/


----> NO 6. ----> Removing patient from data base
create or replace procedure REMOVE_PATIENT(N CHAR, SN CHAR, PH NUMBER)
as
num1 integer;
num2 integer;
id integer;
i integer;
no_patient exception;

BEGIN
     select count(*) into num1
     from Patient
     where Name_Patient=N and Surname_Patient=SN and Phone_Patient=PH;

if num1 = 0
then
       raise no_patient;
else


       DELETE FROM Patient
       WHERE Name_Patient=N and Surname_Patient=SN and Phone_Patient=PH;
       DBMS_output.put_line('Patient with index nr '||id||' has been deleted!');


end if;


exception
       when no_patient then
       DBMS_output.put_line('Patient with that data does not exist !');

END;
/


----> NO 7. ----> Adding a specialization
create or replace procedure ADD_SPECIALIZATION(N CHAR)
as
num integer;
specialization_already_added exception;
BEGIN
       select count(*) into num
       from Specialization
       where Name_of_specialization=N;

       if num>0 then raise specialization_already_added;

       else
               insert into Specialization values(1, N);
               DBMS_output.put_line('A specialization '||N||' has been added.');
               commit;
       end if;

exception
       when specialization_already_added then
       DBMS_output.put_line('This specialization already exists in data base !');
END;
/


----> NO 8. ----> Shows dentists salary list
CREATE OR REPLACE PROCEDURE SalaryList
AS
BEGIN
declare

CURSOR TAKE_DATA IS
  
     SELECT Name_Dentist, Surname_Dentist, Salary_Dentist from Dentist
	    order by Name_Dentist;
begin
FOR zmienna_kursora IN TAKE_DATA
  LOOP
	DBMS_output.put_line('NAME: ' ||zmienna_kursora.Name_Dentist||
			     ' SURNAME: ' || zmienna_kursora.Surname_Dentist||
			     ' SALARY: ' || zmienna_kursora.Salary_Dentist );
  END LOOP;	
end;
Exception

WHEN no_data_found then
  raise_application_error (-20052, 'Error. No Data !');
END;
/


----> NO 9. ----> Rise dentist Salary by Value
create or replace procedure RDS(ID NUMBER, VAL NUMBER)
as
num1 INTEGER;
no_dentist EXCEPTION;
begin
      SELECT count(*) INTO num1
      FROM Dentist
      WHERE Id_Dentist=ID;
      If num1=0 then RAISE no_dentist;
      Else
              update Dentist set Salary_Dentist=NVL(Salary_Dentist,0)+ VAL 
              where Id_Dentist=ID;
              dbms_output.put_line('Dentist salary have been risen by a value.');
      END If;
exception
      when no_dentist then
      dbms_output.put_line('Dentist with index nr '||ID||' does not exist!');
end;
/



----> NO 10. ----> Rise dentist Salary by Percent
create or replace procedure RDS2(ID NUMBER, PR NUMBER)
as
num1 INTEGER;
no_dentist EXCEPTION;
begin
      SELECT count(*) INTO num1
      FROM Dentist
      WHERE Id_Dentist=ID;
      If num1=0 then RAISE no_dentist;
      Else
              update Dentist set Salary_Dentist=NVL(Salary_Dentist,0)+ ((PR/100) * NVL(Salary_Dentist,0))
              where Id_Dentist=ID;
              dbms_output.put_line('Dentist salary have been risen by a percentege.');
      END If;
exception
      when no_dentist then
      dbms_output.put_line('Dentist with index nr '||ID||' does not exist!');
end;
/



----> NO 11. ----> Add new Card
create or replace procedure ADD_CARD(RD DATE, IDP NUMBER, IDD NUMBER, IDDis NUMBER, IDA NUMBER)
as
num integer;
card_already_added exception;

BEGIN
       select count(*) into num 
       from Card
       where Date_of_registration=RD and Id_Patient=IDP and Id_Dentist=IDD and Id_Disease=IDDis and Id_Assistant=IDA;

if num>0 then raise card_already_added;

       else
               insert into Card values(1, RD, IDP, IDD, IDDis, IDA);
               DBMS_output.put_line('Card has been added.');
               commit;
end if;

exception
       when card_already_added then
       DBMS_output.put_line('This Card already exists in data base !');
END;
/


----------------------------------------------------------------------------
----------------------------  INSERTING VALUES  ----------------------------
----------------------------------------------------------------------------

insert into Dentist values(1, 'Henryk', 'Sienkiewicz', 'DR', 4500, 765382, 'Al. Niepodleglosci 13', '1995/09/15');
insert into Dentist values(2, 'Mikolaj', 'Kopernik', 'DR HAB', 5500, 455382, 'Kollataja 8/16', '1996/04/05');
insert into Dentist values(3, 'Andrzej', 'Andrzejewicz', 'PROF', 3500, 265876, 'Focha 4', '1998/11/10');
insert into Dentist values(4, 'Marco', 'Marconi', 'DR', 5000, 765752, 'Kubusia Puchatka 3', '1999/06/30');
insert into Dentist values(5, 'Maria', 'Konopnicka', 'DR HAB', 6000, 905382, 'Supermaana 8', '2001/03/11');
insert into Dentist values(6, 'Genowefa', 'Pigwa', 'DR', 4780, 999382, 'Spidermana 1/45', '2005/02/23');

execute ADD_DENTIST('Henryk', 'Sienkiewicz', 'DR', 4500, 765382, 'Al. Niepodleglosci 13', '1995/09/15');
execute ADD_DENTIST('Marek', 'Kola', 'DR', 5500, 455982, 'Rybacka 23', '1996/04/05');

execute REMOVE_DENTIST('Marco', 'Marconi', 765752);
execute REMOVE_DENTIST('Marek', 'Marconi', 765334);
execute REMOVE_DENTIST('Maria', 'Konopnicka', 905382);

insert into Assistant values(1, 'Iza', 'Nowak', 2500, 776334, 'Kinowa 2a', '2003/09/15');
insert into Assistant values(2, 'Karol', 'Kowalski', 1800, 456334, 'Kostki 21/34', '2004/08/30');
insert into Assistant values(3, 'Barbara', 'Trzeciak', 1750, 763973, 'Lwowska 145', '2005/01/28');
insert into Assistant values(4, 'Marta', 'Markot', 3000, 111222, 'Bema 23', '2001/01/17');
insert into Assistant values(5, 'Alicja', 'Kot', 1280, 222334, 'Slowika 12', '2005/09/21');
insert into Assistant values(6, 'John', 'Pies', 2750, 324231, 'Hanki 12/23', '2006/01/15');

execute ADD_ASSISTANT('Iza', 'Nowak', 2500, 776334, 'Kinowa 2a', '2003/09/15');
execute ADD_ASSISTANT('Karolina', 'Kowalska', 1800, 456334, 'Kostki 21/34', '2004/08/30');
execute ADD_ASSISTANT('Michal', 'Dog', 2750, 324231, 'Hanki 12/26', '2006/01/13');

execute REMOVE_ASSISTANT('Alicja', 'Kot', 222334);
execute REMOVE_ASSISTANT('Alicja', 'Pies', 222334);

insert into Patient values(1, 'Damian', 'Fok', 'Kollataja 8/16', 874934, '1985/09/21', 'akof@tlen.pl');
insert into Patient values(2, 'Zbigniew', 'Szczur', 'Zbyszkowa 13', 909090, '1956/09/23', 'szczur@wp.pl');
insert into Patient values(3, 'Ewa', 'Tyka', 'Marii Sklodowskiej 5', 908769, '1975/07/13', 'tyka20@interia.pl');
insert into Patient values(4, 'Barbara', 'Zgon', 'Slowackiego 12/35', 987654, '1965/03/22', 'aaa@tlen.pl');
insert into Patient values(5, 'Edward', 'Ziom', 'Smolki 45a', 987653, '1956/02/23', 'halo@yahoo.com');
insert into Patient values(6, 'Leszek', 'Sochanski', 'Skowronka 23', 876324, '1989/06/24', 'ziomek@onet.pl');
insert into Patient values(7, 'Aleksandra', 'Swieta', 'Rybna 12', 999876, '1991/09/16', 'elo18@maria.pl');
insert into Patient values(8, 'Jolanta', 'Kwas', 'Fabryczna 23/33', 900000, '1975/10/18', 'grom@grom.pl');
insert into Patient values(9, 'Hanna', 'Hankowa', 'Mariacka 67d', 900899, '1984/11/11', 'firma@hostia.pl');
insert into Patient values(10, 'Adam', 'Malysz', 'Koszykowa 87', 987211, '1992/12/13', 'lot@lot.pl');
insert into Patient values(11, 'Ryszard', 'Tramwajasz', 'Noakowskiego 23', 666543, '1975/05/21', 'foka@tlen.pl');
insert into Patient values(12, 'Jakub', 'Karwo', 'Jasna 65/3', 887656, '1988/04/28', 'seal@tlen.pl');
insert into Patient values(13, 'Dawid', 'Ruba', 'Ciemna 23', 222343, '1982/03/29', 'kkwas@wp.pl');
insert into Patient values(14, 'Miroslaw', 'Bak', 'Borelowskiego 23', 111456, '1978/04/26', 'morda@interia.pl');
insert into Patient values(15, 'Mateusz', 'Kosa', 'Mistrza Jana 23', 323212, '1979/07/12', 'haha@onet.pl');
insert into Patient values(16, 'Norbert', 'Zbik', 'Jana Pawla II 87', 112345, '1972/08/19', 'wwuer@wp.pl');

execute ADD_PATIENT('Damian', 'Fok', 'Kollataja 8/16', 874934, '1985/09/21', 'akof@tlen.pl');
execute ADD_PATIENT('Dimitrij', 'Fokic', 'Juszczenki 45', 800134, '1988/04/28', 'dimitrij@rom.ru');

execute REMOVE_PATIENT('Zbigniew', 'Szczur', 909090);
execute REMOVE_PATIENT('Jolanta', 'Kwas', 900000);

execute  ADD_SPECIALIZATION('Stomatologia ogólna');
execute  ADD_SPECIALIZATION('Ortodoncja');
execute  ADD_SPECIALIZATION('Protetyka');

insert into Category_of_disease values(1, 'Disease_of_gum', 'Choroby dzi¹sê³',1);
insert into Category_of_disease values(2, 'Discolored teeth', 'Przebarwienia.',2);
insert into Category_of_disease values(3, 'Diseases of jaw', 'Choroby szczêki',3);

insert into Disease values(1, 'Gingivitis', 'Zapalenie dzi¹sê³',1);
insert into Disease values(2, 'Filling', 'Plombowanie',2);
insert into Disease values(3, 'Caries', 'Próchnica',3);

insert into Dentist_Specialization values('1999/06/30', 1,1);
insert into Dentist_Specialization values('1999/06/30', 2,2);
insert into Dentist_Specialization values('1999/06/30', 3,3);
insert into Dentist_Specialization values('1999/06/30', 1,6);
insert into Dentist_Specialization values('1999/06/30', 2,7);


insert into Card values(1,'2005/02/02', 1, 2, 2, 4);

execute ADD_CARD('2006/01/02', 3, 6, 2, 1);
execute ADD_CARD('2005/02/06', 4, 7, 2, 6);


--- working with mistakes
insert into Dentist values(8, 'Adam', 'Adamek', 'NNN', 4780, 999382, 'Spidermana 1/45', '2005/02/23');


execute ADD_PATIENT('Damian', 'Fok', 'Kollataja 8/16', 874934, '1985/09/21', 'akof@tlen.pl');
execute ADD_PATIENT('Dimitrij', 'Fokic', 'Juszczenki 45', 800134, '1988/04/28', 'dimitrij@rom.ru');


execute REMOVE_PATIENT('Zbigniew', 'Szczur', 909090);
execute REMOVE_PATIENT('Jolanta', 'Kwas', 900000);

execute  SalaryList;

execute RDS(1, 50);
execute RDS2(3, 5);




