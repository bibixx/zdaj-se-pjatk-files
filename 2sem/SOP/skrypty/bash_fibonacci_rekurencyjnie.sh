function fib(){

a=0; b=1; i=0
 while [ $i -le $1 ]; do
  echo $a
  sum=$(( $a + $b ))
  a=$b
  b=$sum
  i=$(( $i + 1 ))
 done
}

fib $1
