import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.prefs.BackingStoreException;

import org.omg.CosNaming.IstringHelper;

public class PassOne {
	// For Storing the instructions read
	ArrayList<String> instructions = new ArrayList<String>();
	Tables table = new Tables();
	MDTParameters mdt;

	public void readFile(String filename) {
		String temp = new String();
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			while ((temp = br.readLine()) != null) {
				instructions.add(temp);
			}
			if (instructions.get(0).equals("MACRO"))
				System.out.print("\n\t** MACRO ENCOUNTERED ! **\n");
			else
				System.out.println("** MACRO NOT FOUND ! **");
			if (instructions.get(instructions.size() - 1).equals("MEND"))
				System.out.print("\n\t** MACRO TERMNATES SUCCESSFULLY ! **\n");
			else
				System.out.print("\n\t** MACRO DOESNOT TERMINATE SUCCESSFULLY ! **\n");
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

	public void displayFile() {
		System.out.print("\n---------- INPUT FILE ---------------");
		Iterator itr = instructions.listIterator();
		while (itr.hasNext())
			System.out.print("\n" + itr.next());
		System.out.print("\n-------------------------------------");

	}

	public void extractNameAndParameters() {
		StringTokenizer st = new StringTokenizer(instructions.get(1), " &,");
		String nextToken = st.nextToken(); // Macro name add to MNTAB
		table.MNTAB.add(nextToken);

		// Add MacroParameters to PNT KPD tables
		while (st.hasMoreTokens()) {
			nextToken = st.nextToken();
			if (nextToken.contains("=")) {
				StringTokenizer stemp = new StringTokenizer(nextToken, "= ");
				nextToken = stemp.nextToken();
				table.KPDTAB.add(new KPDTABParameters(nextToken, stemp.nextToken()));
			}
			table.PNTAB.add(nextToken);
		}
	}

	public void processMacro() {
		Iterator itr = instructions.listIterator();
		itr.next(); // To skip MACRO
		itr.next(); // To skip first line of name and parameters
		StringTokenizer st;
		String nextToken;
		int ssptr=-1;
		while (itr.hasNext()) {
			st = new StringTokenizer(itr.next().toString(), " ");
			mdt = new MDTParameters();
			nextToken = st.nextToken();

			// If labels exist
			if (nextToken.contains("&")) {
				updateEVNTAB(nextToken);
				mdt.label = nextToken;
				mdt.mnemonic = st.nextToken();
			} else if (nextToken.contains(".")) {
				updateSSNTAB(nextToken);
				mdt.label = nextToken;
				mdt.mnemonic = st.nextToken();
			} else {
				mdt.label = "";
				mdt.mnemonic = nextToken;
			}
			// Terminate if MEND
			if(mdt.mnemonic.equals("MEND")) {
				table.MDTAB.add(mdt);
				break;
			}
			// Processing mnemonics
			mdt.operand = st.nextToken();
			switch (mdt.mnemonic) {
			case "LCL":
				break;
			case "SET":
				System.out.println("MDT : "+mdt.toString());
				updateEVTAB(mdt.label,mdt.operand);
				break;
			case "MOVER":
			case "MOVEM":
			case "MULT":
				StringTokenizer stemp = new StringTokenizer(mdt.operand, " ,");
				mdt.operand = getLabel(stemp.nextToken())+" , "+getLabel(stemp.nextToken());
				break;
			case "AIF":
				mdt.operand = st.nextToken(")");
				System.out.println("mdt op : "+mdt.operand);
				StringTokenizer stemp1 = new StringTokenizer(mdt.operand, " ");
				mdt.operand = getLabel(stemp1.nextToken())+" "+getLabel(stemp1.nextToken())+" "+getLabel(stemp1.nextToken());//+" "+getLabel(stemp1.nextToken())+getLabel(stemp1.nextToken())+" ."+getLabel(stemp1.nextToken());
				mdt.operand += " "+getLabel(st.nextToken(" )"));
				updateSSTAB(""+ssptr);
			default:
				break;
			}
			ssptr++;
			table.MDTAB.add(new MDTParameters(getLabel(mdt.label), mdt.mnemonic, getLabel(mdt.operand)));
		}
		updateMNTAB();
	}
	public String getLabel(String l) {
		//System.out.println("label in function : " + l.substring(1));
		if (l.contains("&")) {
			if (table.EVNTAB.contains(l.substring(1)))
				return "(E," + (table.EVNTAB.indexOf(l.substring(1)) + 1) + ")";
			else if (table.PNTAB.contains(l.substring(1)))
				return "(P," + (table.PNTAB.indexOf(l.substring(1)) + 1) + ")";

		} else if (l.contains("."))
			return "(S," + (table.SSNTAB.indexOf(l.substring(1)) + 1) + ")";

		return l;
	}
	public void updateEVNTAB(String str) {
		str = str.substring(1); // To skip &
		if (table.EVNTAB.indexOf(str) == -1) {
			table.EVNTAB.add(str);
		}
	}

	public void updateEVTAB(String label,String value) {
		int index = table.EVNTAB.indexOf(label);
		if(!value.contains("&"))
			table.EVTAB.add(value);
	}

	public void updateSSNTAB(String str) {
		str = str.substring(1); // To skip .
		if (table.SSNTAB.indexOf(str) == -1) {
			table.SSNTAB.add(str);
		}
	}
	public void updateSSTAB(String str) {
			table.SSTAB.add(str);
	}

	public void updateMNTAB() {
		table.MNTAB.add(""+table.PNTAB.size());		//size #PNT
		table.MNTAB.add(""+table.KPDTAB.size());	//size #KP
		table.MNTAB.add(""+table.EVNTAB.size());	//size #EV
		table.MNTAB.add("1");	//MDT ptr
		table.MNTAB.add("1");	//KPDTAB ptr
		table.MNTAB.add("1");	//SSTAB ptr
	}

	public void displayTables() {
		Iterator iterator;
		System.out.println("-------------------------------------------------------");
		System.out.println("\t\t\tTABLES");
		System.out.println("-------------------------------------------------------");
		System.out.print("MNTAB :");
		iterator = table.MNTAB.listIterator();
		while (iterator.hasNext()) {
			System.out.print("|" + iterator.next());
		}
		System.out.print("|\nPNTAB :\t");
		iterator = table.PNTAB.listIterator();
		while (iterator.hasNext()) {
			System.out.print("|" + iterator.next());
		}
		System.out.print("|\nSSNTAB :");
		iterator = table.SSNTAB.listIterator();
		while (iterator.hasNext()) {
			System.out.print("|" + iterator.next());
		}
		System.out.print("|\nEVNTAB : ");
		iterator = table.EVNTAB.listIterator();
		while (iterator.hasNext()) {
			System.out.print("|" + iterator.next());
		}
		System.out.print("|\nSSTAB : ");
		iterator = table.SSTAB.listIterator();
		while (iterator.hasNext()) {
			System.out.print("|" + iterator.next());
		}
		System.out.print("|\nEVTAB : ");
		iterator = table.EVTAB.listIterator();
		while (iterator.hasNext()) {
			System.out.print("|" + iterator.next());
		}
		System.out.print("|\nAPTAB : ");
		iterator = table.APTAB.listIterator();
		while (iterator.hasNext()) {
			System.out.print("|" + iterator.next());
		}
		System.out.println("|\nKPTAB : ");
		iterator = table.KPDTAB.listIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
		System.out.println("\nMDTAB : ");
		iterator = table.MDTAB.listIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
	}

}
