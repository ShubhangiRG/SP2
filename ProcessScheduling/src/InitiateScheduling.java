import java.util.Scanner;

public class InitiateScheduling {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ans;
		int choice;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\t\t\t*** Algorithm Menu ***");
			System.out.print("\t1. FCFS Algorithm \n\t2. SJFS Algorithm \n\t3. RR Algorithm\n\t4. Priority\n\t5. SRTF\nChoice : ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("\t\t\t*** FCFS Algorithm ***");
				FCFSAlgorithm fcfs = new FCFSAlgorithm();
				fcfs.accept1(0);
				System.out.println("\n\t\t**** Before Scheduling ****");
				fcfs.display1();
				fcfs.algorithm();
				System.out.println("\n\t\t**** After Scheduling ****");
				fcfs.display1();
				break;
			case 2:
				System.out.println("\t\t\t*** SJFS Algorithm ***");
				SJFSAlgorithm sjfs = new SJFSAlgorithm();
				sjfs.accept1(0);
				System.out.println("\n\t\t**** Before Scheduling ****");
				sjfs.display1();
				sjfs.algorithm();
				System.out.println("\n\t\t**** After Scheduling ****");
				sjfs.display1();
				break;
			case 3 :
				System.out.println("\t\t\t*** Round Robin Algorithm ***");
				RRAlgorithm rr = new RRAlgorithm();
				rr.accept1(0);
				System.out.println("\n\t\t**** Before Scheduling ****");
				rr.display1();
				rr.algorithm();
				System.out.println("\n\t\t**** After Scheduling ****");
				rr.display1();
				break;
			case 4 :
				System.out.println("\t\t\t*** Priority Scheduling Algorithm ***");
				PriorityQueueAlgorithm pq = new PriorityQueueAlgorithm();
				pq.accept1(4);
				System.out.println("\n\t\t**** Before Scheduling ****");
				pq.display2();
				pq.algorithm();
				System.out.println("\n\t\t**** After Scheduling ****");
				pq.display2();
				break;
			case 5 :
				System.out.println("\t\t\t*** SRTF Scheduling Algorithm ***");
				SRTFAlgorithm srtf = new SRTFAlgorithm();
				srtf.accept1(0);
				System.out.println("\n\t\t**** Before Scheduling ****");
				srtf.display1();
				srtf.algorithm();
				System.out.println("\n\t\t**** After Scheduling ****");
				srtf.display1();
				break;
			default:
			System.out.println("Please enter valid choice !");
			}
			System.out.print("\nDo you want to continue(y/n)? \nAnswer : ");
			ans = sc.next();
		} while (ans.charAt(0) == 'y');
	}

}
