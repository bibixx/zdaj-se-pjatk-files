/* £¹czenie napisów w pêtli */

word1 = "Ala";
word2 = "kot";

n = 8;

string = "";

do i = 1 to n;
  if ( i // 2 = 0 ) then string = string||word2;
  else string = string||word1; 
end

say string;