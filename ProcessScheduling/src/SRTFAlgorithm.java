
public class SRTFAlgorithm extends AcceptDisplay {
	int burst[] = new int[10];
	int timeSlice = 1;
	int executionTime = 0;
	String ganttChart = new String("");;

	public void algorithm() {
		int index = 0;
		// TODO Auto-generated method stub
		// copy burst time
		for (int i = 0; i < size; i++)
			burst[i] = p[i].burstTime;

		while (allFlagSet() != true) {
			index = findShortestRemainingTime(executionTime);
			burst[index] -= timeSlice;
			executionTime += timeSlice;
			ganttChart += "\n\t" + (index + 1) + ". P" + p[index].id;
			if (burst[index] == 0) {
				p[index].flag = 1;
				p[index].finishTime = executionTime;
				p[index].turnaroundTime = p[index].finishTime - p[index].arrivalTime;
				p[index].waitingTime = p[index].turnaroundTime - p[index].burstTime;
			}
		}
		System.out.print("\n\t****** Gantt Chart ******\n" + ganttChart);

	}

	public int findShortestRemainingTime(int ex) {
		// find shortest remaining time schedule such that
		// it has arrived and not yet completely executed
		int min = 99, index = 0;
		for (int i = 0; i < size; i++) {
			if (p[i].flag != 1 && burst[i] < min && p[i].arrivalTime <= ex) {
				min = burst[i];
				index = i;
			}
		}
		return index;
	}

	public boolean allFlagSet() {
		for (int i = 0; i < size; i++) {
			if (p[i].flag == 0)
				return false;
		}
		return true;
	}
}
/*
*** Algorithm Menu ***
1. FCFS Algorithm 
2. SJFS Algorithm 
3. RR Algorithm
4. Priority
5. SRTF
Choice : 5
*** SRTF Scheduling Algorithm ***
Enter process id : 1
Enter arrival time : 0
Enter burst time : 5
Enter another (y/n) ?
y
Enter process id : 2
Enter arrival time : 1
Enter burst time : 5
Enter another (y/n) ?
y
Enter process id : 3
Enter arrival time : 2
Enter burst time : 2
Enter another (y/n) ?
y
Enter process id : 4
Enter arrival time : 3
Enter burst time : 1
Enter another (y/n) ?
n

**** Before Scheduling ****

Process	Arrival	Burst	Finish	Turnaround	Waiting
1	0	5	0	0		0
2	1	5	0	0		0
3	2	2	0	0		0
4	3	1	0	0		0

Average Turnaroud Time : 0.0
Average Waiting Time : 0.0
****** Gantt Chart ******

1. P1
1. P1
3. P3
3. P3
4. P4
1. P1
1. P1
1. P1
2. P2
2. P2
2. P2
2. P2
2. P2
**** After Scheduling ****

Process	Arrival	Burst	Finish	Turnaround	Waiting
1	0	5	8	8		3
2	1	5	13	12		7
3	2	2	4	2		0
4	3	1	5	2		1

Average Turnaroud Time : 6.0
Average Waiting Time : 2.75
Do you want to continue(y/n)? 
Answer : n
*/