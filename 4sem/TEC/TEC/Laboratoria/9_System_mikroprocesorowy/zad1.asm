.ORG 0
	LD  A,0BCH
	OUT (0BH),A
	LD   A,09H
	OUT (0BH),A
	LD   A,05H
	OUT (0BH),A

START:
	 LD A,08H;
	 LD C,A;

WYSLIJ:
	IN A,(0AH); Odczytuje z portu PC
	BIT 0,A; testuje bit
	JP Z,WYSLIJ; Skacze gdy nie ma znaku
	DEC C;
	LD A,C;
	OUT (09H),A;
	JP Z,START;
	JP WYSLIJ;
.END
 