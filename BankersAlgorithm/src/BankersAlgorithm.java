import java.awt.DisplayMode;

public class BankersAlgorithm extends SetData {
	String safeSequence = new String();

	public void algorithm() {
		boolean x;
		for (int i = 0; i < Matrix.row; i++)
		{
			x = resourceChecking();
			if(x==false) {
				System.out.println("There is no Safe Sequence !");
				return;
			}
		}
		System.out.println("The Safe Sequence is : \n" + safeSequence);
	}

	public boolean resourceChecking() {
		boolean flag;
		int i = 0;
		// resource availability checking for a particular process
		for (i = 0; i < Matrix.row; i++) {
			flag = true;
			for (int j = 0; j < Matrix.col; j++) {
				if (Matrix.resource[j] < available.matrix[i][j] || processDone(i)==true ) {
					flag = false;
					break;
				}
			}
			if (flag == true) { // resource can be allocated
				updateMatrices(i);
				break;
			}
		}
		if(i==Matrix.row)
			return false;
		else
			return true;
	}
public boolean processDone(int i) {
	for (int j = 0; j < Matrix.col; j++) {
		if (available.matrix[i][j]!=0) {
			return false;
		}
	}
	return true;
}
	public void updateMatrices(int i) {
		// matrix updation if flag is true
		for (int j = 0; j < Matrix.col; j++) {
			Matrix.resource[j] = Matrix.resource[j] - available.matrix[i][j] + maximum.matrix[i][j];
			available.matrix[i][j] = 0;
			allocation.matrix[i][j] = maximum.matrix[i][j];
		}
		// update safeSequence
		safeSequence += "P" + (i + 1) + " ";
		//Displaying the iteration
		System.out.println("------------- iteration "+i+"----------------");
		display();
	}

}
