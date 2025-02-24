set termout on                   
prompt Kasowanie tabel i sekwencji
set termout off
set feedback off    
  
DROP TABLE USLUGI CASCADE CONSTRAINTS;
DROP TABLE PERSONEL CASCADE CONSTRAINTS;
DROP TABLE KATEGORIE CASCADE CONSTRAINTS;
DROP TABLE POKOJE CASCADE CONSTRAINTS;
DROP TABLE REZERWACJEZ CASCADE CONSTRAINTS;
DROP TABLE GOSCUSLUGI CASCADE CONSTRAINTS;
DROP TABLE REZERWACJEP CASCADE CONSTRAINTS;
DROP TABLE GOSCIE CASCADE CONSTRAINTS;

DROP SEQUENCE Goscie_Auto; 
DROP SEQUENCE Kategorie_Auto;
DROP SEQUENCE Personel_Auto; 
DROP SEQUENCE Uslugi_Auto; 
DROP SEQUENCE RezerwacjeZ_Auto;
DROP SEQUENCE RezerwacjeP_Auto;
DROP SEQUENCE GoscUslugi_Auto;
 
set termout on
prompt Tworzenie tabel.  Prosze zaczekac.
set termout off
set feedback off    

CREATE SEQUENCE Goscie_Auto 
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE Kategorie_Auto 
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE Personel_Auto 
INCREMENT BY 1
START WITH 1; 

CREATE SEQUENCE Uslugi_Auto 
INCREMENT BY 1
START WITH 1;
 
CREATE SEQUENCE RezerwacjeZ_Auto
INCREMENT BY 1
START WITH 1; 

CREATE SEQUENCE RezerwacjeP_Auto
INCREMENT BY 1
START WITH 1; 

CREATE SEQUENCE GoscUslugi_Auto
INCREMENT BY 1
START WITH 1;
   

create table GOSCIE (
	IDGOSCIA NUMBER(10,0) not null,
	IMIE VARCHAR2(10) not null,
	NAZWISKO VARCHAR2(20) not null,
	ADRES VARCHAR2(30) not null,
	NRDOKUMENTU VARCHAR2(10) not null,
	NIP VARCHAR2(13) not null,
	TELEFON CHAR(10) null,
	VIP CHAR(1) not null,
	PLEC CHAR(1) not null,
	PESEL CHAR(11) not null, 
	constraint GOSCIE_PK primary key (IDGOSCIA) ); 

create table KATEGORIE (
	IDKATEGORII NUMBER(10,0) not null,
	NAZWA VARCHAR2(20) not null,
	CENA NUMBER(10,2) not null, 
	constraint KATEGORIE_PK primary key (IDKATEGORII) ); 
	
create table POKOJE (
	NRPOKOJU NUMBER(10,0) not null,
	PIETRO NUMBER(38,0) not null,
	IDKATEGORII NUMBER(38,0) not null, 
	constraint POKOJE_PK primary key (NRPOKOJU) ); 


create table PERSONEL (
	IDPRACOWNIKA NUMBER(10,0) not null,
	IMIE VARCHAR2(10) not null, 
	NAZWISKO VARCHAR2(20) not null,
	constraint PERSONEL_PK primary key (IDPRACOWNIKA) );             

create table USLUGI (
	IDUSLUGI NUMBER(10,0) not null,
	NAZWA VARCHAR2(20) not null,
	CENA NUMBER(10,2) not null,
	constraint USLUGI_PK primary key (IDUSLUGI) );   

           
create table REZERWACJEZ (
	IDREZERWACJIZ NUMBER(10,0) not null,
	DATA_OD DATE not null,
	DATA_DO DATE not null,
	IDGOSCIA NUMBER(38,0) not null,
	IDKATEGORII NUMBER(38,0) not null, 
	constraint REZERWACJE_PK primary key (IDREZERWACJIZ) ); 
   
create table REZERWACJEP (
	IDREZERWACJIP NUMBER(10,0) not null,
	DATAOD DATE not null,
	DATADO DATE not null,
	IDGOSCIA NUMBER(38,0) not null,
	NRPOKOJU NUMBER(38,0) not null, 
	constraint REZERWACJIP_PK primary key (IDREZERWACJIP) ); 

create table GOSCUSLUGI (
	IDGOSCUSLUGI NUMBER(10,0) not null,
	IDGOSCIA NUMBER(38,0) not null,
	IDREZERWACJIP NUMBER(38,0) not null,
	IDPRACOWNIKA NUMBER(38,0) not null,
	IDUSLUGI NUMBER(38,0) not null, 
	constraint GOSCUSLUGI_PK primary key (IDGOSCUSLUGI) );

alter table POKOJE
	add constraint KATEGORIE_POKOJE_FK1 foreign key (IDKATEGORII)
	 references KATEGORIE (IDKATEGORII); 

alter table REZERWACJEZ
	add constraint GOSCIE_REZERWACJE_FK1 foreign key (IDGOSCIA)
	 references GOSCIE (IDGOSCIA); 

alter table REZERWACJEZ
	add constraint KATEGORIE_REZERWACJE_FK1 foreign key (IDKATEGORII)
	 references KATEGORIE (IDKATEGORII); 

alter table REZERWACJEP
	add constraint GOSCIE_REZERWACJEP_FK1 foreign key (IDGOSCIA)
	 references GOSCIE (IDGOSCIA); 

alter table REZERWACJEP
	add constraint POKOJE_REZERWACJEP_FK1 foreign key (NRPOKOJU)
	 references POKOJE (NRPOKOJU); 

alter table GOSCUSLUGI
	add constraint REZERWACJEP_GOSCUSLUGI_FK1 foreign key (IDREZERWACJIP)
	 references REZERWACJEP (IDREZERWACJIP); 

alter table GOSCUSLUGI
	add constraint GOSCIE_GOSCUSLUGI_FK1 foreign key (IDGOSCIA)
	 references GOSCIE (IDGOSCIA); 

alter table GOSCUSLUGI
	add constraint PERSONEL_GOSCUSLUGI_FK1 foreign key (IDPRACOWNIKA)
	 references PERSONEL (IDPRACOWNIKA); 

alter table GOSCUSLUGI
	add constraint USLUGI_GOSCUSLUGI_FK1 foreign key (IDUSLUGI)
	 references USLUGI (IDUSLUGI);  

INSERT INTO GOSCIE VALUES(Goscie_Auto.NextVal, 'Jan', 'Kowalski', 'ul. Wolska 10 Warszawa', 'DD-369012', '127-65-32-783', '555-12-34', 'T', 'M', '79011200211');
INSERT INTO GOSCIE VALUES(Goscie_Auto.NextVal, 'Anna', 'Nowak', 'ul. Tobruk 9 Kielce', 'DB-124588', '211-73-09-586', '611-41-90', 'N', 'K', '54011101201');
INSERT INTO GOSCIE VALUES(Goscie_Auto.NextVal, 'Józef', 'Bary³a', 'ul. Poprad 120 Miedzylesie', 'DB-887766', '345-23-53-236', '601-01-62', 'N', 'K', '49311200258');
INSERT INTO GOSCIE VALUES(Goscie_Auto.NextVal, 'Mariola', 'Paser', 'ul. Morska 15 Gdañsk', 'DD-654839', '745-35-35-123', '533-22-11', 'N', 'K', '67210505229');
INSERT INTO GOSCIE VALUES(Goscie_Auto.NextVal, 'Karol', 'Zieliñski', 'ul. Jasna 88 Bia³ka Tatrzañska', 'DE-789392', '385-85-85-126', '421-54-32', 'N', 'M', '88100101999');
INSERT INTO GOSCIE VALUES(Goscie_Auto.NextVal, 'Ewa', 'Trol', 'ul. Wiejska 2b Wólka', 'DA-129581', '324-12-45-123', '323-12-32', 'T', 'K', '54080800002');
INSERT INTO GOSCIE VALUES(Goscie_Auto.NextVal, 'Micha³', 'Kot', 'ul. Wielki Staw 1 Ma³y Dwór', 'DB-231381', '528-14-82-632', '434-22-11', 'T', 'M', '49010412539');
INSERT INTO GOSCIE VALUES(Goscie_Auto.NextVal, 'Celina', 'Adamczuk', 'ul. Droga 10c Tarnobrzeg', 'DD-325639', '232-45-12-543', '324-12-62', 'N', 'K', '71030900111');

INSERT INTO KATEGORIE VALUES(Kategorie_Auto.NextVal, 'Normalny', 50);
INSERT INTO KATEGORIE VALUES(Kategorie_Auto.NextVal, 'Super', 120);
INSERT INTO KATEGORIE VALUES(Kategorie_Auto.NextVal, 'Luksusowy', 200);
INSERT INTO KATEGORIE VALUES(Kategorie_Auto.NextVal, 'Luksusowy+', 260);
INSERT INTO KATEGORIE VALUES(Kategorie_Auto.NextVal, 'Królewski', 500);


INSERT INTO POKOJE VALUES(2, 0, 1);
INSERT INTO POKOJE VALUES(10, 3, 5);
INSERT INTO POKOJE VALUES(7, 2, 4);
INSERT INTO POKOJE VALUES(3, 0, 2);
INSERT INTO POKOJE VALUES(8, 2, 4);
INSERT INTO POKOJE VALUES(4, 1, 2);
INSERT INTO POKOJE VALUES(12, 3, 3);
INSERT INTO POKOJE VALUES(1, 0, 1);
INSERT INTO POKOJE VALUES(6, 1, 3);
INSERT INTO POKOJE VALUES(5, 1, 3);
INSERT INTO POKOJE VALUES(9, 2, 4);
INSERT INTO POKOJE VALUES(11, 3, 5);


INSERT INTO PERSONEL VALUES(Personel_Auto.NextVal, 'Leszek', 'Kowalski');
INSERT INTO PERSONEL VALUES(Personel_Auto.NextVal, 'Anna', 'Piegus');
INSERT INTO PERSONEL VALUES(Personel_Auto.NextVal, 'Robert', 'B³oñski');
INSERT INTO PERSONEL VALUES(Personel_Auto.NextVal, 'Iwona', 'Czarnecka');	
INSERT INTO PERSONEL VALUES(Personel_Auto.NextVal, 'Beata', 'Pozorna');	
INSERT INTO PERSONEL VALUES(Personel_Auto.NextVal, 'Wies³awa', 'Soczewka');	

INSERT INTO USLUGI VALUES(Uslugi_Auto.NextVal, 'Obiad', 40);
INSERT INTO USLUGI VALUES(Uslugi_Auto.NextVal, 'Œniadanie', 15);
INSERT INTO USLUGI VALUES(Uslugi_Auto.NextVal, 'Pranie', 15);
INSERT INTO USLUGI VALUES(Uslugi_Auto.NextVal, 'Sprz¹tanie', 10);
INSERT INTO USLUGI VALUES(Uslugi_Auto.NextVal, 'Kolacja', 20);
INSERT INTO USLUGI VALUES(Uslugi_Auto.NextVal, 'Telewizja', 3);
INSERT INTO USLUGI VALUES(Uslugi_Auto.NextVal, 'Pranie chemiczne', 25);
INSERT INTO USLUGI VALUES(Uslugi_Auto.NextVal, 'Masa¿', 20);
INSERT INTO USLUGI VALUES(Uslugi_Auto.NextVal, 'Sprz¹tanie', 10);

INSERT INTO REZERWACJEZ VALUES(RezerwacjeZ_Auto.NextVal, TO_DATE('17-12-1990', 'DD-MM-YY'), TO_DATE('27-12-1990', 'DD-MM-YY'), 1, 5);         
INSERT INTO REZERWACJEZ VALUES(RezerwacjeZ_Auto.NextVal, TO_DATE('5-01-1999', 'DD-MM-YY'), TO_DATE('6-01-1999', 'DD-MM-YY'), 2, 1);
INSERT INTO REZERWACJEZ VALUES(RezerwacjeZ_Auto.NextVal, TO_DATE('11-03-2001', 'DD-MM-YY'), TO_DATE('12-03-2001', 'DD-MM-YY'), 6, 4);
INSERT INTO REZERWACJEZ VALUES(RezerwacjeZ_Auto.NextVal, TO_DATE('25-01-1999', 'DD-MM-YY'), TO_DATE('2-02-1999', 'DD-MM-YY'), 3, 1);
INSERT INTO REZERWACJEZ VALUES(RezerwacjeZ_Auto.NextVal, TO_DATE('7-07-1996', 'DD-MM-YY'), TO_DATE('9-07-1996', 'DD-MM-YY'), 7, 4);
INSERT INTO REZERWACJEZ VALUES(RezerwacjeZ_Auto.NextVal, TO_DATE('15-12-1992', 'DD-MM-YY'), TO_DATE('16-12-1992', 'DD-MM-YY'), 4, 2);
INSERT INTO REZERWACJEZ VALUES(RezerwacjeZ_Auto.NextVal, TO_DATE('1-01-1997', 'DD-MM-YY'), TO_DATE('3-01-1997', 'DD-MM-YY'), 5, 3);
INSERT INTO REZERWACJEZ VALUES(RezerwacjeZ_Auto.NextVal, TO_DATE('19-07-1995', 'DD-MM-YY'), TO_DATE('29-07-1995', 'DD-MM-YY'), 5, 4);
INSERT INTO REZERWACJEZ VALUES(RezerwacjeZ_Auto.NextVal, TO_DATE('10-10-2002', 'DD-MM-YY'), TO_DATE('13-10-2002', 'DD-MM-YY'), 6, 5);
INSERT INTO REZERWACJEZ VALUES(RezerwacjeZ_Auto.NextVal, TO_DATE('22-05-2002', 'DD-MM-YY'), TO_DATE('29-05-2002', 'DD-MM-YY'), 7, 5);
INSERT INTO REZERWACJEZ VALUES(RezerwacjeZ_Auto.NextVal, TO_DATE('3-02-2003', 'DD-MM-YY'), TO_DATE('13-02-2003', 'DD-MM-YY'), 8, 2);

INSERT INTO REZERWACJEP VALUES(RezerwacjeP_Auto.NextVal, TO_DATE('7-12-1990', 'DD-MM-YY'), TO_DATE('9-12-1990', 'DD-MM-YY'), 1, 5);
INSERT INTO REZERWACJEP VALUES(RezerwacjeP_Auto.NextVal, TO_DATE('5-02-1999', 'DD-MM-YY'), TO_DATE('6-02-1999', 'DD-MM-YY'), 2, 4);
INSERT INTO REZERWACJEP VALUES(RezerwacjeP_Auto.NextVal, TO_DATE('19-03-2001', 'DD-MM-YY'), TO_DATE('21-03-2001', 'DD-MM-YY'), 6, 3);
INSERT INTO REZERWACJEP VALUES(RezerwacjeP_Auto.NextVal, TO_DATE('25-01-2000', 'DD-MM-YY'), TO_DATE('2-02-2000', 'DD-MM-YY'), 3, 2);
INSERT INTO REZERWACJEP VALUES(RezerwacjeP_Auto.NextVal, TO_DATE('30-12-1996', 'DD-MM-YY'), TO_DATE('1-01-1997', 'DD-MM-YY'), 7, 1);
INSERT INTO REZERWACJEP VALUES(RezerwacjeP_Auto.NextVal, TO_DATE('4-04-1992', 'DD-MM-YY'), TO_DATE('16-04-1992', 'DD-MM-YY'), 4, 6);           

INSERT INTO GOSCUSLUGI VALUES(GoscUslugi_Auto.NextVal, 7, 5, 1, 2);
INSERT INTO GOSCUSLUGI VALUES(GoscUslugi_Auto.NextVal, 6, 3, 2, 8);
INSERT INTO GOSCUSLUGI VALUES(GoscUslugi_Auto.NextVal, 5, 6, 5, 3);
INSERT INTO GOSCUSLUGI VALUES(GoscUslugi_Auto.NextVal, 1, 1, 6, 4);
INSERT INTO GOSCUSLUGI VALUES(GoscUslugi_Auto.NextVal, 2, 2, 5, 2);
INSERT INTO GOSCUSLUGI VALUES(GoscUslugi_Auto.NextVal, 6, 3, 3, 5);                   
                   
COMMIT                   
                   
set termout on
prompt Skrypt zakonczyl dzialanie.
set termout off
