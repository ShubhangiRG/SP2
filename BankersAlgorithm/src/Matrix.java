import java.util.Arrays;

public class Matrix {
	static int row; // no. of processes
	static int col; // no. of resources
	static int resource[] = new int[col];
	int[][] matrix;

	public Matrix() {
		this.matrix = new int[row][col];
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public static int[] getResource() {
		return resource;
	}

	public static void setResource(int[] resource) {
		Matrix.resource = resource;
	}

	public static int getRow() {
		return row;
	}

	public static void setRow(int row) {
		Matrix.row = row;
	}

	public static int getCol() {
		return col;
	}

	public static void setCol(int col) {
		Matrix.col = col;
	}

	public static String showResources() {
		String temp = new String();
		for (int j = 0; j < Matrix.col; j++) {
			temp += "\t" + resource[j];
		}
		temp += "\n";
		return temp;
	}

	public String show() {
		String temp = new String();
		for (int i = 0; i < Matrix.row; i++) {
			for (int j = 0; j < Matrix.col; j++) {
				temp += "\t" + matrix[i][j];
			}
			temp += "\n";
		}
		return temp;
	}
}
