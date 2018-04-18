
public class ProcessParameter {
String id;
int arrivalTime;
int burstTime;
int finishTime;
int turnaroundTime;
int waitingTime;
int flag;
int priority;
int qflag;
public ProcessParameter() {
	super();
	this.id = null;
	this.arrivalTime = 0;
	this.burstTime = 0;
	this.finishTime = 0;
	this.turnaroundTime = 0;
	this.waitingTime = 0;
	this.flag=0;
	this.qflag = 0;
	this.priority = 0;
}
public int getPriority() {
	return priority;
}
public void setPriority(int priority) {
	this.priority = priority;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public int getArrivalTime() {
	return arrivalTime;
}
public void setArrivalTime(int arrivalTime) {
	this.arrivalTime = arrivalTime;
}
public int getBurstTime() {
	return burstTime;
}
public void setBurstTime(int burstTime) {
	this.burstTime = burstTime;
}
public int getFinishTime() {
	return finishTime;
}
public void setFinishTime(int finishTime) {
	this.finishTime = finishTime;
}
public int getTurnaroundTime() {
	return turnaroundTime;
}
public void setTurnaroundTime(int turnaroundTime) {
	this.turnaroundTime = turnaroundTime;
}
public int getWaitingTime() {
	return waitingTime;
}
public void setWaitingTime(int waitingTime) {
	this.waitingTime = waitingTime;
}
public String toStringPriority() {
	return "\t" + id + "\t" + arrivalTime + "\t" + burstTime + "\t"
			+ finishTime + "\t" + turnaroundTime + "\t\t" + waitingTime +"\t"+priority;
}
@Override
public String toString() {
	return "\t" + id + "\t" + arrivalTime + "\t" + burstTime + "\t"
			+ finishTime + "\t" + turnaroundTime + "\t\t" + waitingTime ;
}

}
