
public class InitiateMacroPreprocessor {

	public static void main(String[] args) {
		PassOne one = new PassOne();
		one.readFile("MacroInputClearmem.txt");
		one.displayFile();
		one.extractNameAndParameters();
		one.processMacro();
		one.displayTables();
		PassOne two = new PassOne();
		System.out.println("\n-----------------------------------");
		two.readFile("MacroInputCube.txt");
		two.displayFile();
		two.extractNameAndParameters();
		two.processMacro();
		two.displayTables();
	}

}
/*

	** MACRO ENCOUNTERED ! **

	** MACRO TERMNATES SUCCESSFULLY ! **

---------- INPUT FILE ---------------
MACRO
CLEARMEM &X,&N,&REG=AREG
LCL &M
&M SET 0
MOVER &REG,='0'
.MORE MOVEM &REG,&X + &M
&M SET &M + 1
AIF ( &M NE &N ) .MORE
MEND
-------------------------------------MDT : 
&M	SET	0
MDT : 
&M	SET	&M
mdt op :  &M NE &N 
-------------------------------------------------------
			TABLES
-------------------------------------------------------
MNTAB :|CLEARMEM|3|1|1|1|1|1|
PNTAB :	|X|N|REG|
SSNTAB :|MORE|
EVNTAB : |M|
SSTAB : |4|
EVTAB : |0|
APTAB : |
KPTAB : 
REG,AREG

MDTAB : 

	LCL	&M

(E,1)	SET	0

	MOVER	(P,3) , ='0'

(S,1)	MOVEM	(P,3) , (P,1)

(E,1)	SET	(E,1)

	AIF	(E,1) NE (P,2) (S,1)

	MEND	

-----------------------------------

	** MACRO ENCOUNTERED ! **

	** MACRO TERMNATES SUCCESSFULLY ! **

---------- INPUT FILE ---------------
MACRO
CUBE &P,&CUB
MOVER AREG,&P
MULT AREG,&P
MULT AREG,&P
MOVEM AREG,&CUB
MEND
--------------------------------------------------------------------------------------------
			TABLES
-------------------------------------------------------
MNTAB :|CUBE|2|0|0|1|1|1|
PNTAB :	|P|CUB|
SSNTAB :|
EVNTAB : |
SSTAB : |
EVTAB : |
APTAB : |
KPTAB : 

MDTAB : 

	MOVER	AREG , (P,1)

	MULT	AREG , (P,1)

	MULT	AREG , (P,1)

	MOVEM	AREG , (P,2)

	MEND	

*/
