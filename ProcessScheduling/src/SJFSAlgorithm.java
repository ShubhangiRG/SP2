
public class SJFSAlgorithm extends AcceptDisplay {
	int executionTime;
	ProcessParameter temp = new ProcessParameter();
	String ganttChart = new String("");

	public void algorithm() {
		//set flags to zero
		for (int i = 0; i < size; i++)
			p[i].flag = 0;
		//execution time to zero
		executionTime = 0;
		int j, k;
		//i is for no. of processes
		for (int i = 0; i < size; i++) {
			j = i;
			k = i;
			//Find shortest job such that its arrival time < execution time
			while (j < size) {
				if (p[j].burstTime < p[k].burstTime && p[j].arrivalTime <= executionTime && p[j].flag !=1) {
					k = j;
				}
				j++;
			}
			//Swap i and k as k is the Shortest Job
			temp = p[i];
			p[i] = p[k];
			p[k] = temp;
			//Parameter setting and gantt chart creation
			ganttChart += "\n\t" + (i + 1) + ". P" + p[i].id;
			p[i].flag = 1; // process Executed
			executionTime += p[i].burstTime;
			p[i].finishTime = executionTime;
			p[i].turnaroundTime = p[i].finishTime - p[i].arrivalTime;
			p[i].waitingTime = p[i].turnaroundTime - p[i].burstTime;

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
Choice : 2
*** SJFS Algorithm ***
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

Average Turnaroud Time : 0.0
Average Waiting Time : 0.0
****** Gantt Chart ******

1. P1
2. P3
3. P2
4. P4
5. P5
**** After Scheduling ****

Process	Arrival	Burst	Finish	Turnaround	Waiting
1	0	3	3	3		0
3	3	2	5	2		0
2	1	5	10	9		4
4	9	5	15	6		1
5	12	5	20	8		3

Average Turnaroud Time : 5.6
Average Waiting Time : 1.6
Do you want to continue(y/n)? 
Answer : n
*/