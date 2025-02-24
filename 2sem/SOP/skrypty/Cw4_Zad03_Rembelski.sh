x=0

test -f BACKUP && x=1;
test -d BACKUP && x=2;


if [ $x != 1 ]
 then 
      if [ $x == 2 ]; then  rm BACKUP/* ; else mkdir BACKUP; fi	
      cp *~ BACKUP/	
 else 
      echo "Error 1"
fi

