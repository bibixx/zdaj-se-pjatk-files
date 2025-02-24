/* Mno¾enie macierzy kwadratowych */

N = 2;

a[1][1] = 1;  b[1][1] = 2;
a[1][2] = 2;  b[1][2] = 2;
a[2][1] = 3;  b[2][1] = 3;
a[2][2] = 7;  b[2][2] = 2;

do i = 1 to N;
  do j = 1 to N;
    c[i][j] = 0;
    do k = 1 to N;
       c[i][j] = c[i][j] + a[i][k] * b[k][j];
    end
  end
end

say 'Wynik:'
do i = 1 to N
  out = ''
  do j = 1 to N
    out = out right(c[i][j], 8)
  end
  say out
end 
