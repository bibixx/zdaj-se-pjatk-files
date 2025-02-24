%{
#include "sql_b.h"

void error()
{
  fprintf(stdout, "Plik niepoprawny pod wzglêdem leksykalnym. Linia: %d\n", yylineno);
  exit(1);
}

%}

%option yylineno

ID [0-9]*[a-zA-Z_][a-zA-Z0-9_]*
LICZBA [0-9]*
NAPIS ["][^"]*["]
BIALY [ \t\n]

%%

[sS][eE][lL][eE][cC][tT] return SELECT;
[fF][rR][oO][mM] return FROM;
[wW][hH][eE][rR][eE] return W;
[aA][nN][dD] return AND;
[oO][rR] return OR;
[oO][nN] return ON;
[oO][rR][dD][eE][rR][ ][bB][yY] return ORDER;
[dD][eE][sS][cC] return DESC;
[aA][sS][cC] return ASC;
[gG][rR][oO][uU][pP][ ][bB][yY] return GROUP;
[hH][aA][vV][iI][nN][gG] return HAVING;
[jJ][oO][iI][nN] return JOIN;
[nN][aA][tT][uU][rR][aA][lL][ ][jJ][oO][iI][nN] return NJOIN;
[lL][eE][fF][tT][ ][jJ][oO][iI][nN] return LJOIN;
[rR][iI][gG][hH][tT][ ][jJ][oO][iI][nN] return RJOIN;
[fF][uU][lL][lL][ ][jJ][oO][iI][nN] return FJOIN;
[iI][nN][sS][eE][rR][tT] return INSERT;
[iI][nN][tT][oO] return INTO;
[vV][aA][lL][uU][eE][sS] return VALUE;
[bB][eE][tT][wW][eE][eE][nN] return BETWEEN;
{NAPIS} return NAP;
{LICZBA} return LICZ;
{ID} return ID;
!= return NE;
"*" return '*';
"," return ',';
"=" return '=';
"<" return '<';
">" return '>';
"(" return '(';
")" return ')';
";" return ';';

{BIALY}+
. error();


%%

