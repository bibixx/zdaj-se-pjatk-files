a = 3;
b = 5;
func1();
func2();
say "main a =" a "b =" b "c = " c;
exit;

function func1() expose b c
  a = 7;
  b = b + 1;
  c = 12;
  func2();
  say "func1 a =" a "b = " b "c = " c;
  return;

function func2() expose c
  a = 100;
  b = 101;
  c = c + 1;
  say "func2 a =" a "b = " b "c = " c;
  return;

