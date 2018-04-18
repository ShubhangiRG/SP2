import java.io.*;
import java.util.*;

public class PassOne {
	ArrayList<String> instructions = new ArrayList<String>();
	int baseAddress = 0;
	String outputString = new String("");
	Tables tables = new Tables();

	public void ReadFile() {
		try {
			FileReader fr = new FileReader("input.txt");
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

	public void extractBaseAddress() {
		String temp = instructions.get(0);
		if (!temp.contains("START"))
			System.out.println("\n\t\tInvalid Code! \n\t\tSTART SYMBOL NOT FOUND !");
		else {
			baseAddress = Integer.parseInt(temp.substring(6, temp.length()));
			System.out.print("\n\tBase address : " + baseAddress);
		}
	}
	public void initiatePassOne() {
		Iterator itr = instructions.listIterator();
		itr.next(); // To skip start
		String nextToken;

		//Parsing each instruction
		while (itr.hasNext()) {
			StringTokenizer stringTokenizer = new StringTokenizer(itr.next().toString(), " ,+");
			nextToken = stringTokenizer.nextToken();
			InstructionParameters ip = new InstructionParameters();
			// First token could be label or mnemonic
			if (nextToken.contains(":")) {
				ip.label = new String(nextToken.substring(0, nextToken.length() - 1));
				addSymbolTable(ip.label);
				nextToken = stringTokenizer.nextToken();
			} else {
				ip.label = null;
			}
			ip.mnemonic = nextToken;
			ip.calculateMnemonicDetails();

			// On the basis of mnemonic
			// separating operand1 and operand2 and adding to tables
			ip.operand1 = null;
			ip.operand2 = null;
			// CASE 0 : STOP | END
			if (ip.mnemonic.equals("STOP")) {
				outputString += baseAddress + " (IS," + ip.mnemonicOpcode + ") ";
				baseAddress += 1;
			}
			else if(ip.mnemonic.equals("END")){
				outputString += baseAddress + " (AD," + ip.mnemonicOpcode + ") ";
				baseAddress += 1;
			}
			// CASE 1 : IS
			else if (ip.instructionType.equals("IS")) { // any IS statement except for stop
				ip.operand1 = stringTokenizer.nextToken();
				ip.operand2 = stringTokenizer.nextToken();

				if (ip.operand2.contains("=")) {
					ip.operand2 = ip.operand2.substring(2, ip.operand2.length() - 1);
					addLiteralTable(ip.operand2);
					outputString += baseAddress + " (IS," + ip.mnemonicOpcode + ") " + ip.operand1 + " , (L,"
							+ (getLiteralIndex(ip.operand2)+1) + ")";
				} else {
					addSymbolTable(ip.operand2);
					outputString += baseAddress + " (IS," + ip.mnemonicOpcode + ") " + ip.operand1 + " , "
							+ ip.operand2;
				}
				baseAddress += ip.mnemonicLength;
			}
			// CASE 2 : AD
			else if (ip.instructionType.equals("AD")) { 
				if (ip.mnemonic.equals("ORIGIN")) {
					ip.operand1 = stringTokenizer.nextToken();
					ip.operand2 = stringTokenizer.nextToken();
					int index = getSymbolIndex(ip.operand1);
					outputString += baseAddress + " (AD," + ip.mnemonicOpcode + ") " + ip.operand1 + " + "
							+ ip.operand2;
					baseAddress = tables.symbolTable.get(index).address + Integer.parseInt(ip.operand2);
				} else if (ip.mnemonic.equals("LTORG")) {
					ip.operand1 = stringTokenizer.nextToken(" ''=");
					ip.operand2 = null;
					int index = getLiteralIndex(ip.operand1);
					tables.literalTable.get(index).address = baseAddress;
					tables.literalTableAddress.set(index,baseAddress);
					tables.poolTable.add("#"+index);
					outputString += baseAddress + " (AD," + ip.mnemonicOpcode + ") " + ip.operand1;
					baseAddress += ip.mnemonicLength;
				}
			}
			// CASE 3: DL statements and EQU
			else {
				ip.operand1 = ip.mnemonic;
				ip.mnemonic = stringTokenizer.nextToken();
				ip.operand2 = stringTokenizer.nextToken();
				ip.calculateMnemonicDetails();
				if(ip.mnemonic.equals("DS"))
				{
					outputString += baseAddress + " (DL," + ip.mnemonicOpcode + ") " + ip.operand2;
					baseAddress += Integer.parseInt(ip.operand2);
				}
				if (ip.mnemonic.equals("EQU")) {
					int index1 = getSymbolIndex(ip.operand1);
					int index2 = getSymbolIndex(ip.operand2);
					//make address of BACK = address of LOOP
					int tempAddress = tables.symbolTable.get(index2).address;
					tables.symbolTable.get(index1).address = tempAddress;
					tables.symbolTableAddress.set(index1, tempAddress);
					outputString += baseAddress + " (AD," + ip.mnemonicOpcode + ") ";
				}
			}
			outputString += "\n";
		}
		System.out.println("\nOutput String :\n" + outputString);
	}

	//Adding content to symbol table if doesnot exist : label|symbol
	public void addSymbolTable(String str) {
		TablesParameters tp = new TablesParameters(str,baseAddress);
		if (tables.symbolTableToken.indexOf(str) == -1) {
			tables.symbolTable.add(tp);
			tables.symbolTableToken.add(tp.token);
			tables.symbolTableAddress.add(tp.address);

		}
	}
	//Adding content to literal table 
	public void addLiteralTable(String str) {
		TablesParameters tp = new TablesParameters(str,baseAddress);
		//if (tables.literalTableToken.indexOf(str) == -1) {
			tables.literalTable.add(tp);
			tables.literalTableToken.add(tp.token);
			tables.literalTableAddress.add(tp.address);

		//}
	}

	public int getSymbolIndex(String str) {
		return tables.symbolTableToken.indexOf(str);
	}

	public int getLiteralIndex(String str) {
		return tables.literalTableToken.indexOf(str);
	}
	public void displayTables() {
		System.out.println("Symbol Table : "+tables.symbolTable);
		System.out.println("Symbol Table Tokens  : "+tables.symbolTableToken);
		System.out.println("Symbol Table Address : "+tables.symbolTableAddress);
		System.out.println("Literal Table : "+tables.literalTable);
		System.out.println("Literal Table Tokens  : "+tables.literalTableToken);
		System.out.println("Literal Table Address : "+tables.literalTableAddress);
	}
	public void writeFile(){
		try {
			FileWriter fr = new FileWriter("intermediate.txt");
			BufferedWriter br = new BufferedWriter(fr);
			br.write(outputString);
			br.close();
			fr.close();
			
			fr = new FileWriter("symbol.txt");
			br = new BufferedWriter(fr);
			br.write("Symbol Table : "+tables.symbolTable.toString());
			br.write("Symbol Table Tokens  : "+tables.symbolTableToken.toString());
			br.write("Symbol Table Address : "+tables.symbolTableAddress.toString());
			br.close();
			fr.close();
			
			fr = new FileWriter("literal.txt");
			br = new BufferedWriter(fr);
			br.write("Literal Table : "+tables.literalTable);
			br.write("Literal Table Tokens  : "+tables.literalTableToken);
			br.write("Literal Table Address : "+tables.literalTableAddress);
			br.close();
			fr.close();
			
			fr = new FileWriter("pool.txt");
			br = new BufferedWriter(fr);
			br.write(tables.poolTable.toString());
			br.close();
			fr.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
