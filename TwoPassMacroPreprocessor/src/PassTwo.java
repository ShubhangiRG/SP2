import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class PassTwo {
	Tables table = new Tables();
	String MacroName;
	String parameter1;
	String parameter2;

	public void readFile(Tables table) {
		try {
			FileReader frOuter = new FileReader("AssemblyCode.txt");
			BufferedReader brOuter = new BufferedReader(frOuter);

			String Instruction = new String();
			System.out.println("Reading File .....\n-------------------------------------------------------");
			System.out.println("File Data : \n\n" + Instruction);
			while ((Instruction = brOuter.readLine()) != null) { // Reading line by line AssemblyCode
				{
					System.out.println(Instruction);
					if ((Instruction.contains("CLEARMEM"))) { // Macro Encountered
						System.out.println(MacroLogic(Instruction));
					}
					if ((Instruction.contains("CUBE"))) { // Macro Encountered
						System.out.println(MacroLogic(Instruction));
					}
				}
			}

			brOuter.close();
			frOuter.close();
			System.out.println("-------------------------------------------------------");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Macro not incorrectly terminated...Please check the file !");
			e.printStackTrace();
		}
	}

	public String MacroLogic(String instruction) {
		StringTokenizer st = new StringTokenizer(instruction, " ,");
		String line,iNextToken;
		MacroName = st.nextToken();
		parameter1 = st.nextToken();
		parameter2 = st.nextToken();
		table.APTAB.add(parameter1);
		table.APTAB.add(parameter2);
		String OutputString = "";
		FileReader fr;
		try {
			fr = new FileReader("MacroInput.txt");
			BufferedReader br = new BufferedReader(fr);
			while (!br.readLine().contains(MacroName));// read file till macro name
			
			if(MacroName.equals("CLEARMEM")) {
			while (!(line = br.readLine()).equals("MEND")) { // Reading till MEND encountered
				st = new StringTokenizer(line, " ,");
				iNextToken = st.nextToken(" ,");
				if (iNextToken.equals("MOVER"))
					OutputString += "\n+ MOVER AREG," + parameter1;
				else if (iNextToken.contains(".MORE")) {
					for (int i = 0; i < Integer.parseInt(parameter2); i++)
						OutputString += "\n+ MOVEM AREG," + parameter1 + " + " + i;
				}
				else
				{
					st.nextToken("\n");
				}
			}
			}
			

			if(MacroName.equals("CUBE")) {
			while (!(line = br.readLine()).equals("MEND")) { // Reading till MEND encountered
				st = new StringTokenizer(line, " ,");
				iNextToken = st.nextToken(" ,");
				if (iNextToken.equals("MOVER"))
					OutputString += "\n+ MOVER AREG," + parameter1;
				else if (iNextToken.contains("MULT")) {
						OutputString += "\n+ MULT AREG," + parameter1;
				}
				else if (iNextToken.contains("MOVEM")) {
						OutputString += "\n+ MOVEM AREG," + parameter2;
				}
				else
				{
					st.nextToken("\n");
				}
			}
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return OutputString;

	}
}
