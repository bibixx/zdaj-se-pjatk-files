a = 3;
b = 5;
func1();
func2();
say "main a =" a "b =" b;
exit;

function func1() 
  a = 7;
  b = 10;
  c = 12;
  func2();
  say "func1 a =" a "b = " b "c = " c;
  return;

function func2() expose c
  a = 100;
  b = 101;
  c = 101;
  say "func2 a =" a "b = " b "c = " c;
  return;

