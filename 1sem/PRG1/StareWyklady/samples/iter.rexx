do i = 1 to 100000
   n = i * i;
   if (n > 20000) then leave;
end
say i n;