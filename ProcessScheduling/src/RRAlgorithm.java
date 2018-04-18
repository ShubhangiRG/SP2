import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import javax.print.attribute.standard.Finishings;

public class RRAlgorithm extends AcceptDisplay {
	//qflag is set to 1 if once it arrives in arrival queue
	int executionTime = 0;
	int index = 0;
	int top = 0;
	int timeSlice;// time quantam
	int burst[] = new int[10];
	ProcessParameter temp = new ProcessParameter();
	Scanner sc = new Scanner(System.in);
	Queue<String> arrivalQueue = new LinkedList<String>();
	Queue<String> outputQueue = new LinkedList<String>();
	String ganttChart = new String("");

	public void algorithm() {
		System.out.print("\nEnter time slice : ");
		timeSlice = sc.nextInt();

		for (int i = 0; i < size; i++) {
			burst[i] = p[i].burstTime;
		}
		System.out.println("bl : " + burst.length + "\tpl : " + size);
		int k = 0;
		while (allFlagSet() != true) {
			arrivalQueueUpdate();
			addTopmostElement();
			System.out.print("\nArrival Queue : " + arrivalQueue);
			executionTime += timeSlice;
			executionQueueUpdate();
			System.out.print("\nOutput Queue : " + outputQueue);
			System.out.print("\n---------------------------------------------------------------");
			k++;
		}
		System.out.print("\n\t****** Gantt Chart ******\n" + ganttChart);
	}

	public void arrivalQueueUpdate() {
		// Arrival queue
		for (int i = 0; i < size; i++) {
			// flag 0 means process yet not complete and qflag==0 means process never arrived in this queue
			if (p[i].flag == 0 && p[i].arrivalTime <= executionTime && p[i].qflag == 0) {
				arrivalQueue.add(p[i].id);
				p[i].qflag = 1;
			}
		}
	}

	public void addTopmostElement() {
		// add topmost element of queue also
		if (outputQueue.isEmpty() !=true) {
			// top = Integer.parseInt(outputQueue.peek());
			System.out.println("top element : " + top);
			if (p[top].flag == 0)
				arrivalQueue.add(p[top].id);
		}
	}

	public void executionQueueUpdate() {
		// Execution queue
		index = Integer.parseInt(arrivalQueue.remove()) - 1;
		top = index;
		outputQueue.add(p[index].id);
		ganttChart += "\n\t" + (index + 1) + ". P" + p[index].id;

		if (burst[index] <= timeSlice) {
			burst[index] = 0;
			p[index].flag = 1;
			p[index].finishTime = executionTime;
			p[index].turnaroundTime = p[index].finishTime - p[index].arrivalTime;
			p[index].waitingTime = p[index].turnaroundTime - p[index].burstTime;

		} else
			burst[index] -= timeSlice;

		System.out.print("\nburst : ");
		for (int j = 0; j < size; j++) {
			System.out.print("\t " + burst[j]);
		}
		System.out.print("\nFlag : ");
		for (int j = 0; j < size; j++) {
			System.out.print("\t" + p[j].flag);
		}
	}

	private boolean allFlagSet() {
		for (int i = 0; i < size; i++) {
			if (p[i].flag == 0)
				return false;
		}
		return true;
	}
}
/*
1	0	4
y

2	1	5
y

3	2	2
y

4	3	1
y

5	4	6
y

6	5	3
n

4

*/
/*	
			*** Algorithm Menu ***
	1. FCFS Algorithm 
	2. SJFS Algorithm 
	3. RR Algorithm
	4. Priority
	5. SRTF
Choice : 3
			*** Round Robin Algorithm ***
Enter process id : 1
Enter arrival time : 0
Enter burst time : 4
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
y
Enter process id : 5
Enter arrival time : 4
Enter burst time : 6
Enter another (y/n) ?
y
Enter process id : 6
Enter arrival time : 5
Enter burst time : 3
Enter another (y/n) ?
n

		**** Before Scheduling ****

	Process	Arrival	Burst	Finish	Turnaround	Waiting
	1	0	4	0	0		0
	2	1	5	0	0		0
	3	2	2	0	0		0
	4	3	1	0	0		0
	5	4	6	0	0		0
	6	5	3	0	0		0

	Average Turnaroud Time : 0.0
	Average Waiting Time : 0.0
Enter time slice : 2
bl : 10	pl : 6

Arrival Queue : [1]
burst : 	 2	 5	 2	 1	 6	 3
Flag : 	0	0	0	0	0	0
Output Queue : [1]
---------------------------------------------------------------top element : 0

Arrival Queue : [2, 3, 1]
burst : 	 2	 3	 2	 1	 6	 3
Flag : 	0	0	0	0	0	0
Output Queue : [1, 2]
---------------------------------------------------------------top element : 1

Arrival Queue : [3, 1, 4, 5, 2]
burst : 	 2	 3	 0	 1	 6	 3
Flag : 	0	0	1	0	0	0
Output Queue : [1, 2, 3]
---------------------------------------------------------------top element : 2

Arrival Queue : [1, 4, 5, 2, 6]
burst : 	 0	 3	 0	 1	 6	 3
Flag : 	1	0	1	0	0	0
Output Queue : [1, 2, 3, 1]
---------------------------------------------------------------top element : 0

Arrival Queue : [4, 5, 2, 6]
burst : 	 0	 3	 0	 0	 6	 3
Flag : 	1	0	1	1	0	0
Output Queue : [1, 2, 3, 1, 4]
---------------------------------------------------------------top element : 3

Arrival Queue : [5, 2, 6]
burst : 	 0	 3	 0	 0	 4	 3
Flag : 	1	0	1	1	0	0
Output Queue : [1, 2, 3, 1, 4, 5]
---------------------------------------------------------------top element : 4

Arrival Queue : [2, 6, 5]
burst : 	 0	 1	 0	 0	 4	 3
Flag : 	1	0	1	1	0	0
Output Queue : [1, 2, 3, 1, 4, 5, 2]
---------------------------------------------------------------top element : 1

Arrival Queue : [6, 5, 2]
burst : 	 0	 1	 0	 0	 4	 1
Flag : 	1	0	1	1	0	0
Output Queue : [1, 2, 3, 1, 4, 5, 2, 6]
---------------------------------------------------------------top element : 5

Arrival Queue : [5, 2, 6]
burst : 	 0	 1	 0	 0	 2	 1
Flag : 	1	0	1	1	0	0
Output Queue : [1, 2, 3, 1, 4, 5, 2, 6, 5]
---------------------------------------------------------------top element : 4

Arrival Queue : [2, 6, 5]
burst : 	 0	 0	 0	 0	 2	 1
Flag : 	1	1	1	1	0	0
Output Queue : [1, 2, 3, 1, 4, 5, 2, 6, 5, 2]
---------------------------------------------------------------top element : 1

Arrival Queue : [6, 5]
burst : 	 0	 0	 0	 0	 2	 0
Flag : 	1	1	1	1	0	1
Output Queue : [1, 2, 3, 1, 4, 5, 2, 6, 5, 2, 6]
---------------------------------------------------------------top element : 5

Arrival Queue : [5]
burst : 	 0	 0	 0	 0	 0	 0
Flag : 	1	1	1	1	1	1
Output Queue : [1, 2, 3, 1, 4, 5, 2, 6, 5, 2, 6, 5]
---------------------------------------------------------------
	****** Gantt Chart ******

	1. P1
	2. P2
	3. P3
	1. P1
	4. P4
	5. P5
	2. P2
	6. P6
	5. P5
	2. P2
	6. P6
	5. P5
		**** After Scheduling ****

	Process	Arrival	Burst	Finish	Turnaround	Waiting
	1	0	4	8	8		4
	2	1	5	20	19		14
	3	2	2	6	4		2
	4	3	1	10	7		6
	5	4	6	24	20		14
	6	5	3	22	17		14

	Average Turnaroud Time : 12.5
	Average Waiting Time : 9.0
Do you want to continue(y/n)? 
Answer : 2

*/
/*
			*** Algorithm Menu ***
	1. FCFS Algorithm 
	2. SJFS Algorithm 
	3. RR Algorithm
	4. Priority
Choice : 3
			*** Round Robin Algorithm ***
Enter process id : 1
Enter arrival time : 0
Enter burst time : 4
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
y
Enter process id : 5
Enter arrival time : 4
Enter burst time : 6
Enter another (y/n) ?
y
Enter process id : 6
Enter arrival time : 6
Enter burst time : 3
Enter another (y/n) ?
n

		**** Before Scheduling ****

	Process	Arrival	Burst	Finish	Turnaround	Waiting
	1	0	4	0	0		0
	2	1	5	0	0		0
	3	2	2	0	0		0
	4	3	1	0	0		0
	5	4	6	0	0		0
	6	6	3	0	0		0

	Average Turnaroud Time : 0.0
	Average Waiting Time : 0.0
Enter time slice : 4
bl : 10	pl : 6

Arrival Queue : [1]
burst : 	 0	 5	 2	 1	 6	 3
Flag : 	1	0	0	0	0	0
Output Queue : [1]
---------------------------------------------------------------top element : 0

Arrival Queue : [2, 3, 4, 5]
burst : 	 0	 1	 2	 1	 6	 3
Flag : 	1	0	0	0	0	0
Output Queue : [1, 2]
---------------------------------------------------------------top element : 1

Arrival Queue : [3, 4, 5, 6, 2]
burst : 	 0	 1	 0	 1	 6	 3
Flag : 	1	0	1	0	0	0
Output Queue : [1, 2, 3]
---------------------------------------------------------------top element : 2

Arrival Queue : [4, 5, 6, 2]
burst : 	 0	 1	 0	 0	 6	 3
Flag : 	1	0	1	1	0	0
Output Queue : [1, 2, 3, 4]
---------------------------------------------------------------top element : 3

Arrival Queue : [5, 6, 2]
burst : 	 0	 1	 0	 0	 2	 3
Flag : 	1	0	1	1	0	0
Output Queue : [1, 2, 3, 4, 5]
---------------------------------------------------------------top element : 4

Arrival Queue : [6, 2, 5]
burst : 	 0	 1	 0	 0	 2	 0
Flag : 	1	0	1	1	0	1
Output Queue : [1, 2, 3, 4, 5, 6]
---------------------------------------------------------------top element : 5

Arrival Queue : [2, 5]
burst : 	 0	 0	 0	 0	 2	 0
Flag : 	1	1	1	1	0	1
Output Queue : [1, 2, 3, 4, 5, 6, 2]
---------------------------------------------------------------top element : 1

Arrival Queue : [5]
burst : 	 0	 0	 0	 0	 0	 0
Flag : 	1	1	1	1	1	1
Output Queue : [1, 2, 3, 4, 5, 6, 2, 5]
---------------------------------------------------------------
	Average Turnaroud Time : 16.666666
	Average Waiting Time : 13.166667
		**** After Scheduling ****

	Process	Arrival	Burst	Finish	Turnaround	Waiting
	1	0	4	4	4		0
	2	1	5	28	27		22
	3	2	2	12	10		8
	4	3	1	16	13		12
	5	4	6	32	28		22
	6	6	3	24	18		15

	Average Turnaroud Time : 19.444445
	Average Waiting Time : 15.361112
Do you want to continue(y/n)? 
Answer : n

*/