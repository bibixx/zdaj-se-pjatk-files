-- (grupa z hotelem)
-- zad 1 : procedura mająca dodawać rezerwacje

--	@kNz : nazwa kategorii
--	@gIm : 	imie klienta
--	@gNa :	nazwisko klienta
--	@dSt :	data od
--	@dKo :	data do
create proc rezerwuj( 
	@kNz as varchar(20), 
	@gIm as varchar(20), 
	@gNa as varchar(30), 
	@dSt as dateTime,
	@dKo as dateTime 
	)
as
	declare @kId as int
	select @kId = idKategoria from kategoria where Nazwa = @kNz
	
	if @kId is null 
		begin 
			RAISERROR('Nie znaleziono kategorii',5,1) 
		end
	else
		begin
	
		declare @gId as int
		select @gId = max(idGosc) from Gosc where imie = @gIm and nazwisko = @gNa
	
		if @gId is null
		begin	-- dodajemy goscia
			insert into Gosc([IdGosc],[Imie],[Nazwisko],[Procent_rabatu]) values (
			(select max([IdGosc])+1 from gosc),
			@gIm,@gNa,0)
			select @gId = (select max([IdGosc])+1 from gosc)--@@identity
		end
	
		declare @pId as int -- id pokoju
		select @pId = min(p.NrPokoju) from Pokoj p where idKategoria = @kId
/*		and not exists ( -- sprawdzanie czy jest jakis wolny - miało nie być
			select * from rezerwacja where 
				nrpokoju = p.nrpokoju and ( 
					@dSt between dataOd and dataDo or 
					@dKo between dataOd and dataDo  ) 
			)
*/
		if @pId is null begin RAISERROR('Nie znaleziono pokoju',5,1) end
	
		INSERT INTO [dbo].[Rezerwacja]
			([IdRezerwacja],[DataOd],[DataDo],[IdGosc],[NrPokoju],[Zaplacona])
		VALUES
			(
			(select max([IdRezerwacja])+1 from [Rezerwacja]),
			@dSt,@dKo,@gId,@pId,0
			)
	end
go

-- test: zla kat.
execute rezerwuj 'zlaKategoria', '???', '???', '11-11-2011', '12-11-2011'
select top 1 * from rezerwacja order by [IdRezerwacja] desc
go
-- test: dodawania klienta/ponowna rezerwacja
--	kategorie:
--	1	Turystyczny	30.00
--	2	Standardowy	60.00
--	3	Luksusowy	120.00

execute rezerwuj 'Turystyczny', 'Zbigniew', 'Zielony', '2011-11-11','2011-11-21'
select top 1 * from rezerwacja order by [IdRezerwacja] desc
go
execute rezerwuj 'Luksusowy', 'Zbigniew', 'Zielony', '2011-12-11', '2011-12-21'
select * from rezerwacja order by [IdRezerwacja] desc
go

drop proc rezerwuj
go


-- zad 2: trigger nie pozwalający wyrzucać rezerwacji nieopłaconych albo z 2008r
GO
CREATE TRIGGER usRezw
   ON  rezerwacja
   for DELETE
AS 
BEGIN
	declare @errCnt as int
	select @errCnt = count(*) from deleted 
	where 
		dataOd between '2008-01-01 00:00:00' and '2008-12-31 23:59:59'
		or
		dataDo between '2008-01-01 00:00:00' and '2008-12-31 23:59:59'
		or
		zaplacona = 0;

	if @errCnt = 1
	begin
		RAISERROR('Nie mozna usunac tej rezerwacji',5,1) 
		rollback
	end

	if @errCnt > 1
	begin 
		RAISERROR('Nie mozna usunac tych rezerwacji',5,1) 
		rollback
	end
END
GO

-- testy:
	--	rezerwacje:
	--	1	2009-07-01 00:00:00.000	2009-07-05 00:00:00.000	1	101	1
	--	2	2008-01-03 00:00:00.000	2008-01-15 00:00:00.000	1	102	1
	--	3	2009-07-15 00:00:00.000	2009-08-02 00:00:00.000	2	101	1
	--	4	2008-12-12 00:00:00.000	2008-12-14 00:00:00.000	3	103	1
	--	5	2009-05-01 00:00:00.000	2009-05-05 00:00:00.000	3	201	1
	--	6	2009-04-01 00:00:00.000	2009-05-01 00:00:00.000	4	201	0
	--	7	2008-11-15 00:00:00.000	2008-11-20 00:00:00.000	4	105	0
-- rezerwacja z 2008
delete from rezerwacja where idRezerwacja in(2)
GO
-- nie oplacona rezerwacja
delete from rezerwacja where idRezerwacja in(6)
GO
-- usuwanie kilku
delete from rezerwacja where idRezerwacja in(1,2,3,4,5,6,7)
GO

-- usuwanie poprawnych (powinny byc z zad 1)
update rezerwacja set zaplacona = 1 where idRezerwacja in(21,22)
delete from rezerwacja where idRezerwacja in(21,22)
GO

drop trigger usRezw
GO