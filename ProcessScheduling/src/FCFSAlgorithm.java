
public class FCFSAlgorithm extends AcceptDisplay{
int executionTime=0;
String ganttChart= new String("");
	public void algorithm() {
		// TODO Auto-generated method stub
		executionTime=0;
		for(int i=0;i<size;i++) {
			//Parameter setting and gantt chart creation
			ganttChart += "\n\t" + (i + 1) + ". P" + p[i].id;
			executionTime+=p[i].burstTime;
			p[i].finishTime = executionTime;
			p[i].turnaroundTime = p[i].finishTime-p[i].arrivalTime;
			p[i].waitingTime = p[i].turnaroundTime-p[i].burstTime;
		}
		System.out.print("\n\t****** Gantt Chart ******\n" + ganttChart);
	}

}
/*
 			*** Algorithm Menu ***
	1. FCFS Algorithm 
	2. SJFS Algorithm 
	3. RR Algorithm
	4. Priority
Choice : 1
			*** FCFS Algorithm ***
Enter process id : 1
Enter arrival time : 0
Enter burst time : 20
Enter another (y/n) ?
y
Enter process id : 2
Enter arrival time : 0
Enter burst time : 3
Enter another (y/n) ?
y
Enter process id : 3
Enter arrival time : 0
Enter burst time : 4
Enter another (y/n) ?
n

		**** Before Scheduling ****

	Process	Arrival	Burst	Finish	Turnaround	Waiting
	1	0	20	0	0		0
	2	0	3	0	0		0
	3	0	4	0	0		0

	Average Turnaroud Time : 23.333334

	Average Waiting Time : 14.333333

		**** After Scheduling ****

	Process	Arrival	Burst	Finish	Turnaround	Waiting
	1	0	20	20	20		0
	2	0	3	23	23		20
	3	0	4	27	27		23
Do you want to continue(y/n)? 
Answer : n
 */
 /*
 			*** Algorithm Menu ***
	1. FCFS Algorithm 
	2. SJFS Algorithm 
	3. RR Algorithm
	4. Priority
Choice : 1
			*** FCFS Algorithm ***
Enter process id : 1
Enter arrival time : 0
Enter burst time : 3
Enter another (y/n) ?
y
Enter process id : 2
Enter arrival time : 1
Enter burst time : 5
Enter another (y/n) ?
y
Enter process id : 3
Enter arrival time : 3
Enter burst time : 2
Enter another (y/n) ?
y
Enter process id : 4
Enter arrival time : 9
Enter burst time : 5
Enter another (y/n) ?
y
Enter process id : 5
Enter arrival time : 12
Enter burst time : 5
Enter another (y/n) ?
n

		**** Before Scheduling ****

	Process	Arrival	Burst	Finish	Turnaround	Waiting
	1	0	3	0	0		0
	2	1	5	0	0		0
	3	3	2	0	0		0
	4	9	5	0	0		0
	5	12	5	0	0		0

	Average Turnaroud Time : 6.2

	Average Waiting Time : 2.2

		**** After Scheduling ****

	Process	Arrival	Burst	Finish	Turnaround	Waiting
	1	0	3	3	3		0
	2	1	5	8	7		2
	3	3	2	10	7		5
	4	9	5	15	6		1
	5	12	5	20	8		3
Do you want to continue(y/n)? 
Answer : n

 */
 