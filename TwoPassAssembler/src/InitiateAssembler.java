
public class InitiateAssembler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		PassOne one = new PassOne();
		one.ReadFile();
		one.displayFile();
		System.out.println("--------------------------------------------------------------------");
		one.extractBaseAddress();
		one.initiatePassOne();
		System.out.println("--------------------------------------------------------------------");
		one.displayTables();
		System.out.println("--------------------------------------------------------------------");
		one.writeFile();
		System.out.println("--------------------------------------------------------------------");
		
		PassTwo two = new PassTwo(one.tables);
		two.ReadFile();
		two.displayFile();
		System.out.println("--------------------------------------------------------------------");
		two.initiatePassTwo();
		System.out.println("--------------------------------------------------------------------");
		two.writeFile();

	}

}
/*
The Input File is : 
START 200
MOVER AREG,='5'
MOVEM AREG,A
LOOP: MOVER AREG,A
MOVER CREG,B
ADD CREG,='1'
BC CREG,NEXT
LTORG ='5'
LTORG ='1'
NEXT: SUB AREG,='2'
BC CREG,BACK
LAST: STOP
ORIGIN LOOP+2
MULT CREG,B
ORIGIN LAST+1
A DS 1
BACK EQU LOOP
B DS 1
END
--------------------------------------------------------------------

	Base address : 200
Output String :
200 (IS,4) AREG , (L,1)
203 (IS,5) AREG , A
206 (IS,4) AREG , A
209 (IS,4) CREG , B
212 (IS,1) CREG , (L,2)
215 (IS,7) CREG , NEXT
218 (AD,5) 5
219 (AD,5) 1
220 (IS,2) AREG , (L,3)
223 (IS,7) CREG , BACK
226 (IS,0) 
227 (AD,3) LOOP + 2
208 (IS,3) CREG , B
211 (AD,3) LAST + 1
227 (DL,2) 1
228 (AD,4) 
228 (DL,2) 1
229 (AD,2) 

--------------------------------------------------------------------
Symbol Table : [TablesParameters [token=A, address=206], TablesParameters [token=LOOP, address=206], TablesParameters [token=B, address=208], TablesParameters [token=NEXT, address=220], TablesParameters [token=BACK, address=206], TablesParameters [token=LAST, address=226]]
Symbol Table Tokens  : [A, LOOP, B, NEXT, BACK, LAST]
Symbol Table Address : [206, 206, 208, 220, 206, 226]
Literal Table : [TablesParameters [token=5, address=218], TablesParameters [token=1, address=219], TablesParameters [token=2, address=220]]
Literal Table Tokens  : [5, 1, 2]
Literal Table Address : [218, 219, 220]
--------------------------------------------------------------------
--------------------------------------------------------------------
The Input File is : 
200 (IS,4) AREG , (L,1)
203 (IS,5) AREG , A
206 (IS,4) AREG , A
209 (IS,4) CREG , B
212 (IS,1) CREG , (L,2)
215 (IS,7) CREG , NEXT
218 (AD,5) 5
219 (AD,5) 1
220 (IS,2) AREG , (L,3)
223 (IS,7) CREG , BACK
226 (IS,0) 
227 (AD,3) LOOP + 2
208 (IS,3) CREG , B
211 (AD,3) LAST + 1
227 (DL,2) 1
228 (AD,4) 
228 (DL,2) 1
229 (AD,2) 
--------------------------------------------------------------------
The final output is : 
200 +04 1 218
203 +05 1 206
206 +04 1 206
209 +04 3 208
212 +01 3 219
215 +07 3 220
218 
219 
220 +02 1 220
223 +07 3 206
226 
227 
208 +03 3 208
211 
227 +01
228 
228 +01
229 

--------------------------------------------------------------------
*/