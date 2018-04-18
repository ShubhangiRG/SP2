
public class PriorityQueueAlgorithm extends AcceptDisplay {
	int executionTime;
	ProcessParameter temp = new ProcessParameter();
	int index;
	String ganttChart = new String("");

	public void algorithm() {
		sortByArrival();
		//make flags 0
		for(int i=0;i<size;i++)
			p[i].flag=0;
		executionTime = 0;
		//start PriorityQueue algorithm
		for (int i = 0; i < size; i++) {
			index = sortByPriority(executionTime);
			p[index].flag = 1;
			//Parameter setting and gantt chart creation
			ganttChart += "\n\t" + (index + 1) + ". P" + p[index].id;
			executionTime += p[index].burstTime;
			p[index].finishTime = executionTime;
			p[index].turnaroundTime = p[index].finishTime - p[index].arrivalTime;
			p[index].waitingTime = p[index].turnaroundTime - p[index].burstTime;
			System.out.print("\n\t****** Gantt Chart ******\n" + ganttChart);
			display2();
		}
		System.out.print("\n\t****** Gantt Chart ******\n" + ganttChart);
	}

	public void sortByArrival() {
		for (int i = 0; i < (size - 1); i++) {
			for (int j = (i + 1); j < size; j++) {
				if (p[i].arrivalTime > p[j].arrivalTime) {
					temp = p[i];
					p[i] = p[j];
					p[j] = temp;

				}
			}
		}
	}

	public int sortByPriority(int ex) {
		int priority=0, index = 0;
		//Is ready for execution and not yet executed
		for (int i = 0; i < size; i++) {
			if (p[i].arrivalTime <= ex && p[i].flag != 1) {
				if (p[i].priority > priority)
					index = i;
					priority = p[i].priority;
			}
		}
		return index;

	}
}
/*
			*** Algorithm Menu ***
	1. FCFS Algorithm 
	2. SJFS Algorithm 
	3. RR Algorithm
	4. Priority
Choice : 4
			*** Priority Scheduling Algorithm ***
Enter process id : 1
Enter arrival time : 0
Enter burst time : 4
Enter priority : 2
Enter another (y/n) ?
y
Enter process id : 2
Enter arrival time : 1
Enter burst time : 2
Enter priority : 4
Enter another (y/n) ?
y
Enter process id : 3
Enter arrival time : 2
Enter burst time : 3
Enter priority : 6
Enter another (y/n) ?
y
Enter process id : 4
Enter arrival time : 3
Enter burst time : 5
Enter priority : 10
Enter another (y/n) ?
y
Enter process id : 5
Enter arrival time : 4
Enter burst time : 1
Enter priority : 8
Enter another (y/n) ?
y
Enter process id : 6
Enter arrival time : 5
Enter burst time : 4
Enter priority : 12
Enter another (y/n) ?
y
Enter process id : 7
Enter arrival time : 6
Enter burst time : 6
Enter priority : 9
Enter another (y/n) ?
n

		**** Before Scheduling ****

	Process	Arrival	Burst	Finish	Turnaround	Waiting
	1	0	4	0	0		0
	2	1	2	0	0		0
	3	2	3	0	0		0
	4	3	5	0	0		0
	5	4	1	0	0		0
	6	5	4	0	0		0
	7	6	6	0	0		0

	Average Turnaroud Time : 0.0
	Average Waiting Time : 0.0
	****** Gantt Chart ******

	1. P1
	Process	Arrival	Burst	Finish	Turnaround	Waiting	Priority
	1	0	4	4	4		0	2
	2	1	2	0	0		0	4
	3	2	3	0	0		0	6
	4	3	5	0	0		0	10
	5	4	1	0	0		0	8
	6	5	4	0	0		0	12
	7	6	6	0	0		0	9

	Average Turnaroud Time : 0.5714286
	Average Waiting Time : 0.0
	****** Gantt Chart ******

	1. P1
	4. P4
	Process	Arrival	Burst	Finish	Turnaround	Waiting	Priority
	1	0	4	4	4		0	2
	2	1	2	0	0		0	4
	3	2	3	0	0		0	6
	4	3	5	9	6		1	10
	5	4	1	0	0		0	8
	6	5	4	0	0		0	12
	7	6	6	0	0		0	9

	Average Turnaroud Time : 1.5102041
	Average Waiting Time : 0.14285715
	****** Gantt Chart ******

	1. P1
	4. P4
	6. P6
	Process	Arrival	Burst	Finish	Turnaround	Waiting	Priority
	1	0	4	4	4		0	2
	2	1	2	0	0		0	4
	3	2	3	0	0		0	6
	4	3	5	9	6		1	10
	5	4	1	0	0		0	8
	6	5	4	13	8		4	12
	7	6	6	0	0		0	9

	Average Turnaroud Time : 2.787172
	Average Waiting Time : 0.7346939
	****** Gantt Chart ******

	1. P1
	4. P4
	6. P6
	7. P7
	Process	Arrival	Burst	Finish	Turnaround	Waiting	Priority
	1	0	4	4	4		0	2
	2	1	2	0	0		0	4
	3	2	3	0	0		0	6
	4	3	5	9	6		1	10
	5	4	1	0	0		0	8
	6	5	4	13	8		4	12
	7	6	6	19	13		7	9

	Average Turnaroud Time : 4.826739
	Average Waiting Time : 1.8192419
	****** Gantt Chart ******

	1. P1
	4. P4
	6. P6
	7. P7
	5. P5
	Process	Arrival	Burst	Finish	Turnaround	Waiting	Priority
	1	0	4	4	4		0	2
	2	1	2	0	0		0	4
	3	2	3	0	0		0	6
	4	3	5	9	6		1	10
	5	4	1	20	16		15	8
	6	5	4	13	8		4	12
	7	6	6	19	13		7	9

	Average Turnaroud Time : 7.4038196
	Average Waiting Time : 4.1170344
	****** Gantt Chart ******

	1. P1
	4. P4
	6. P6
	7. P7
	5. P5
	3. P3
	Process	Arrival	Burst	Finish	Turnaround	Waiting	Priority
	1	0	4	4	4		0	2
	2	1	2	0	0		0	4
	3	2	3	23	21		18	6
	4	3	5	9	6		1	10
	5	4	1	20	16		15	8
	6	5	4	13	8		4	12
	7	6	6	19	13		7	9

	Average Turnaroud Time : 10.771975
	Average Waiting Time : 7.0167193
	****** Gantt Chart ******

	1. P1
	4. P4
	6. P6
	7. P7
	5. P5
	3. P3
	2. P2
	Process	Arrival	Burst	Finish	Turnaround	Waiting	Priority
	1	0	4	4	4		0	2
	2	1	2	25	24		22	4
	3	2	3	23	21		18	6
	4	3	5	9	6		1	10
	5	4	1	20	16		15	8
	6	5	4	13	8		4	12
	7	6	6	19	13		7	9

	Average Turnaroud Time : 14.68171
	Average Waiting Time : 10.573817
	****** Gantt Chart ******

	1. P1
	4. P4
	6. P6
	7. P7
	5. P5
	3. P3
	2. P2
		**** After Scheduling ****

	Process	Arrival	Burst	Finish	Turnaround	Waiting	Priority
	1	0	4	4	4		0	2
	2	1	2	25	24		22	4
	3	2	3	23	21		18	6
	4	3	5	9	6		1	10
	5	4	1	20	16		15	8
	6	5	4	13	8		4	12
	7	6	6	19	13		7	9

	Average Turnaroud Time : 15.240244
	Average Waiting Time : 11.081975
Do you want to continue(y/n)? 
Answer : n

*/
