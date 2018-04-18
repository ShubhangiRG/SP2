import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import javax.print.attribute.standard.Finishings;

public class FCFSPreemptive extends AcceptDisplay {
	int executionTime;
	int timeSlice;// time quantam
	int burst[] = new int[10];
	ProcessParameter temp = new ProcessParameter();
	Scanner sc = new Scanner(System.in);
	Queue<String> q = new LinkedList<String>();

	public void algorithm() {
		System.out.print("\nEnter time slice : ");
		timeSlice = sc.nextInt();

		for (int i = 0; i < size; i++) {
			burst[i] = p[i].burstTime;
		}
		System.out.println("bl : " + burst.length + "\tpl : " + size);
		int k=0;
		while (allFlagSet() == false) {
			System.out.println("All flag : " + allFlagSet());
			executionTime = 0;// current time
			for (int i = 0; i < size; i++) {
				// flag 0 means process yet not complete
				if (p[i].flag == 0) {
					q.add(p[i].id);
					if (burst[i] <= timeSlice) {
						burst[i] = 0;
						p[i].flag = 1;
						p[i].finishTime = executionTime;
						p[i].waitingTime = p[i].finishTime - p[i].arrivalTime;
						p[i].turnaroundTime = p[i].finishTime - p[i].arrivalTime;
					} else
						burst[i] -= timeSlice;
				}
				for (int j = 0; j < size; j++) {
					System.out.println("\nburst : "+burst[j]);
					System.out.println("flag : "+p[j].flag);}
				executionTime += timeSlice;
			}
			k++;
			if(k==4)
			break;
		}
		System.out.println(q);
		averageTime();
	}

	private boolean allFlagSet() {
		for (int i = 0; i < size; i++) {
			if (p[i].flag == 0)
				return false;
		}
		return true;
	}
}
