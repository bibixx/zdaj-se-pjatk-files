/* Test funkcji  */

do forever
  v1 = linein();
  if (v1 = '') then exit;
  v2 = linein(); 
  if (v2 = '') then exit;
  showComparison( v1, v2);
end
exit


function compare ( val1, val2 )

   if ( val1 > val2 ) then return 1;
   else if (val1 < val2) then return -1;
   else return 0;
 

function showComparison ( val1, val2)
   
   msg[1] = "wi©ksza od";
   msg[0] = "rowna";
   i = -1; 
   msg[i] = "mniejsza do";

   r = compare( val1, val2 );
   say "Warto˜† " val1 " jest " msg[r] "warto˜ci " val2
   return

   