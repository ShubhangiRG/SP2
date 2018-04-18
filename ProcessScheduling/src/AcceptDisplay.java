import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class AcceptDisplay {
	ProcessParameter processParameter;
	ArrayList<ProcessParameter> a = new ArrayList<ProcessParameter>();
	ProcessParameter p[] = new ProcessParameter[10];
	int c=0;
	int size;
	float averageTurnaroundTime;
	float averageWaitingTime;

	public void accept1(int x) {
		String ans;
		do {
		p[c] = new ProcessParameter();
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter process id : ");
		p[c].setId(scan.next());
		System.out.print("Enter arrival time : ");
		p[c].setArrivalTime(scan.nextInt());
		System.out.print("Enter burst time : ");
		p[c].setBurstTime(scan.nextInt());
		if(x==4)
		{
			System.out.print("Enter priority : ");
			p[c].setPriority(scan.nextInt());
		}
		a.add(p[c]);
		c++;
		System.out.println("Enter another (y/n) ?");
		ans = scan.next();
		}while(ans.charAt(0) =='y');
		size =c;
	}

	public void display1() {
		System.out.println("\n\tProcess\tArrival\tBurst\tFinish\tTurnaround\tWaiting");
		for (int i = 0; i < size; i++) {
		System.out.println(p[i].toString());
		}
		averageTime();
	}
	public void display2() {
		System.out.println("\n\tProcess\tArrival\tBurst\tFinish\tTurnaround\tWaiting\tPriority");
		for (int i = 0; i < size; i++) {
		System.out.println(p[i].toStringPriority());
		}
		averageTime();
	}
	public void averageTime() {
		for (int i = 0; i < size; i++) {
			averageTurnaroundTime += p[i].turnaroundTime;
			averageWaitingTime += p[i].waitingTime;
		}
		averageTurnaroundTime = averageTurnaroundTime / size;
		averageWaitingTime = averageWaitingTime / size;
		System.out.print("\n\tAverage Turnaroud Time : " + averageTurnaroundTime);
		System.out.print("\n\tAverage Waiting Time : " + averageWaitingTime);

	}

}
