
public class InitiateAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankersAlgorithm s = new  BankersAlgorithm();
		s.accept();
		System.out.println("------------- Initial State ----------------");
		s.display();
		s.algorithm();
	}

}

/*

No of processes : 4

No of resources : 3
Resource matrix : 
0	1	1
Allocation matrix : 
2	1	1
7	2	3
3	2	2
1	1	3
Maximum claim matrix : 
4	3	3
7	2	4
4	2	5
5	3	3
------------- Initial State ----------------
Resource matrix :
 	0	1	1

Maximium matrix :
 	4	3	3
	7	2	4
	4	2	5
	5	3	3

Allocation matrix :
 	2	1	1
	7	2	3
	3	2	2
	1	1	3

Available matrix :
 	2	2	2
	0	0	1
	1	0	3
	4	2	0

------------- iteration 1----------------
Resource matrix :
 	7	3	4

Maximium matrix :
 	4	3	3
	7	2	4
	4	2	5
	5	3	3

Allocation matrix :
 	2	1	1
	7	2	4
	3	2	2
	1	1	3

Available matrix :
 	2	2	2
	0	0	0
	1	0	3
	4	2	0

------------- iteration 0----------------
Resource matrix :
 	9	4	5

Maximium matrix :
 	4	3	3
	7	2	4
	4	2	5
	5	3	3

Allocation matrix :
 	4	3	3
	7	2	4
	3	2	2
	1	1	3

Available matrix :
 	0	0	0
	0	0	0
	1	0	3
	4	2	0

------------- iteration 2----------------
Resource matrix :
 	12	6	7

Maximium matrix :
 	4	3	3
	7	2	4
	4	2	5
	5	3	3

Allocation matrix :
 	4	3	3
	7	2	4
	4	2	5
	1	1	3

Available matrix :
 	0	0	0
	0	0	0
	0	0	0
	4	2	0

------------- iteration 3----------------
Resource matrix :
 	13	7	10

Maximium matrix :
 	4	3	3
	7	2	4
	4	2	5
	5	3	3

Allocation matrix :
 	4	3	3
	7	2	4
	4	2	5
	5	3	3

Available matrix :
 	0	0	0
	0	0	0
	0	0	0
	0	0	0

The Safe Sequence is : 
P2 P1 P3 P4 

*/

/*


No of processes : 2

No of resources : 2
Resource matrix : 
0	0	
Allocation matrix : 
1	2
2	2
Maximum claim matrix : 
1	2	2	2
------------- Initial State ----------------
Resource matrix :
 	0	0

Maximium matrix :
 	1	2
	2	2

Allocation matrix :
 	1	2
	2	2

Available matrix :
 	0	0
	0	0

There is no Safe Sequence !
*/