%{
#include<stdio.h>
#include<string.h>

int yyerror(char* s)
{
  fprintf(stderr, "blad: %s\n", s); 
  return(0);
}

%}

%token SELECT FROM ID II W AND OR ON NE ORDER HAVING GROUP ASC DESC JOIN NJOIN LJOIN RJOIN FJOIN INSERT INTO VALUE NAP BETWEEN LICZ

%%

SQL : ZAP
    | INS
    ;

INS : INSERT INTO ID I1 VALUE '('I2')' EX
    ;

I1 :  
   |'('A2')'					
   ;

I2 : NAP	
   | NAP','I2
   | LICZ
   | LICZ','I2
  ;
 
ZAP : SELECT A1 FROM A3 O EX;

PODZAP : SELECT ID FROM ID APODZAP;

APODZAP : | A5
	  | A6
	  ;

A1 : '*' 
  | A2
  ;

A2 : ID
   | ID','A2
   ;

A3 : ID A5
   |A4
   ;

A4 : ID  
   |ID','A4
   |ID A6
   ;

A5 : A11 A6
   |A11
   ;

A11 : NJOIN ID
   |JOIN ID ON ID
   |LJOIN ID ON ID
   |RJOIN ID ON ID
   |FJOIN ID ON ID
   ;

A6 : W A8
   | W A8 A7
   |A7
   ;

A7 : GROUP A2 A10
   ;

A10 : | HAVING A8
   ;

A8 : ID'=' A9     
   | ID'<' A9
   | ID'>' A9
   | ID NE A9
   | ID BETWEEN LICZ AND LICZ
   | '('ID'=' A9')'A12     
   | '('ID'<' A9')'A12
   | '('ID'>' A9')'A12
   | '('ID NE A9')'A12
   ;
  
A9 : ID A12
   | NAP A12
   | LICZ A12
   | '(' PODZAP ')'
   ;

A12 : | AND A8
   |  OR A8  
   ; 

O  : | ORDER A2 DESC
   | ORDER A2 ASC 
   ; 
   
EX : ';' { return(0); }
   ;

%%


int main(void)
{ 
  return yyparse(); 
}  

