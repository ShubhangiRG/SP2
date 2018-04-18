import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class PassTwo {
	ArrayList<String> instructions = new ArrayList<String>();
	int baseAddress = 0;
	String finalOutputString = new String("");
	Tables tables = new Tables();

	public PassTwo(Tables t) {
		// TODO Auto-generated constructor stub
		tables = t;
	}

	public void ReadFile() {
		try {
			FileReader fr = new FileReader("intermediate.txt");
			BufferedReader br = new BufferedReader(fr);
			String temp;
			while ((temp = br.readLine()) != null) {
				instructions.add(temp);
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void displayFile() {
		System.out.print("The Input File is : \n");
		Iterator itr = instructions.listIterator();
		while (itr.hasNext())
			System.out.print(itr.next() + "\n");
	}

	public void initiatePassTwo() {
		Iterator itr = instructions.listIterator();
		String nextToken;

		// Parsing each instruction
		while (itr.hasNext()) {
			StringTokenizer stringTokenizer = new StringTokenizer(itr.next().toString(), " ,+)(");
			// initial address parsed
			nextToken = stringTokenizer.nextToken();
			finalOutputString += nextToken + " ";
			// Mnemonic and type of instruction
			nextToken = stringTokenizer.nextToken();

			if (nextToken.equals("IS")) {
				// After type of instruction found
				nextToken = stringTokenizer.nextToken();
				if (!nextToken.equals("0")) { // NOT STOP
					finalOutputString += "+0" + nextToken;

					// Register code calculation
					nextToken = stringTokenizer.nextToken();
					finalOutputString += " " + calculateRegisterCode(nextToken);
					
					//Address retrieval from various tables
					nextToken = stringTokenizer.nextToken();
					if(nextToken.equals("L"))
					{
						int i = Integer.parseInt(stringTokenizer.nextToken())-1;
						finalOutputString += " " +tables.literalTableAddress.get(i);
					}else {
						int i = getSymbolIndex(nextToken);
						finalOutputString += " " +tables.symbolTableAddress.get(i);
					}
				}
			}
			if (nextToken.equals("DL")) {
				// After type of instruction found
				nextToken = stringTokenizer.nextToken();
				nextToken = stringTokenizer.nextToken();
				finalOutputString += "+0" + nextToken;

			}
			finalOutputString += "\n";
		}
		System.out.println("The final output is : \n" + finalOutputString);
	}
	public int getSymbolIndex(String str) {
		return tables.symbolTableToken.indexOf(str);
	}

	public int getLiteralIndex(String str) {
		return tables.literalTableToken.indexOf(str);
	}
	public int calculateRegisterCode(String s) {
		int registerCode;
		switch (s) {
		case "AREG":
			registerCode = 1;
			break;
		case "BREG":
			registerCode = 2;
			break;
		case "CREG":
			registerCode = 3;
			break;
		default:
			registerCode = 0;
		}
		return registerCode;
	}
	public void writeFile() {
		try {
			FileWriter fr = new FileWriter("final.txt");
			BufferedWriter br = new BufferedWriter(fr);
			br.write(finalOutputString);
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
