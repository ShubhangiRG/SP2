import java.util.Scanner;

public class SetData {
	Scanner scan = new Scanner(System.in);
	Matrix allocation = new Matrix();
	Matrix available = new Matrix();
	Matrix maximum = new Matrix();

	public void accept() {
		System.out.print("\nNo of processes : ");
		Matrix.setRow(scan.nextInt());
		System.out.print("\nNo of resources : ");
		Matrix.setCol(scan.nextInt());
		System.out.println("Resource matrix : ");
		Matrix.setResource(acceptResourceMatrix());

		System.out.println("Allocation matrix : ");
		allocation.setMatrix(acceptMatrix());
		System.out.println("Maximum claim matrix : ");
		maximum.setMatrix(acceptMatrix());
		available.setMatrix(calculateAvailableMatrix());

	}

	public int[][] acceptMatrix() {
		int[][] t = new int[Matrix.row][Matrix.col];

		for (int i = 0; i < Matrix.row; i++) {
			for (int j = 0; j < Matrix.col; j++) {
				t[i][j] = scan.nextInt();
			}
		}
		return t;
	}

	public int[] acceptResourceMatrix() {
		int[] t = new int[Matrix.col];
		for (int i = 0; i < Matrix.col; i++) {
			t[i] = scan.nextInt();
		}
		return t;
	}

	public int[][] calculateAvailableMatrix() {
		int[][] t = new int[Matrix.row][Matrix.col];
		for (int i = 0; i < Matrix.row; i++) {
			for (int j = 0; j < Matrix.col; j++) {
				t[i][j] = maximum.matrix[i][j] - allocation.matrix[i][j];
			}
		}
		return t;
	}

	public void display() {
		System.out.println("Resource matrix :\n " + Matrix.showResources());
		System.out.println("Maximium matrix :\n " + maximum.show());
		System.out.println("Allocation matrix :\n " + allocation.show());
		System.out.println("Available matrix :\n " + available.show());
		
	}
}
