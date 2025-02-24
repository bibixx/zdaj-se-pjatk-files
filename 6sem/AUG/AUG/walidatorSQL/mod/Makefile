ALL:    sql_val
 
CC=gcc

OBJ=	sql_b.o sql_l.o

sql_val :	$(OBJ)
		$(CC) -o $@ $(OBJ) -lfl
		
sql_b.c :    sql.y
		bison -v -d -o sql_b.c sql.y

sql_l.c :    sql.l
		flex -I -o sql_l.c sql.l

clean:
		rm -f sql_b.output sql_b.h sql_l.c sql_l.c sql_val
		rm -f *~
		rm -f *.o
		rm -f core
