
public class InstructionParameters {
	String mnemonic;
	int mnemonicOpcode;
	int mnemonicLength;
	String instructionType;
	String operand1;
	String operand2;
	String label;
	int address;
	int registerCode;
	int varAddress;

	public InstructionParameters() {
		operand1 = null;
		operand2 = null;
		label = null;
		instructionType = "IS";
		mnemonicOpcode = 0;
		mnemonicLength = 3;
		registerCode=0;
	}

	public String getParsedOperand() {
		if (operand2.contains("='")) // to skip =' if any
			operand2 = operand2.substring(operand2.indexOf('\'') + 1, operand2.lastIndexOf('\''));
		return operand2;
	}

	@Override
	public String toString() {
		return "Instruction [mnemonic=" + mnemonic + ", mnemonicOpcode=" + mnemonicOpcode + ", mnemonicLength="
				+ mnemonicLength + ", instructionType=" + instructionType + ", operand1=" + operand1 + ", operand2="
				+ operand2 + ", label=" + label + ", address=" + address + ", registerCode=" + registerCode + "]";
	}

	public void calculateMnemonicDetails() {
		switch (mnemonic) {
		case "STOP":
			mnemonicOpcode = 0;
			mnemonicLength = 1;
			instructionType = "IS";
			break;
		case "ADD":
			mnemonicOpcode = 1;
			mnemonicLength = 3;
			instructionType = "IS";
			break;
		case "SUB":
			mnemonicOpcode = 2;
			mnemonicLength = 3;
			instructionType = "IS";
			break;
		case "MULT":
			mnemonicOpcode = 3;
			mnemonicLength = 3;
			instructionType = "IS";
			break;
		case "MOVER":
			mnemonicOpcode = 4;
			mnemonicLength = 3;
			instructionType = "IS";
			break;
		case "MOVEM":
			mnemonicOpcode = 5;
			mnemonicLength = 3;
			instructionType = "IS";
			break;
		case "COMP":
			mnemonicOpcode = 06;
			mnemonicLength = 3;
			instructionType = "IS";
			break;
		case "BC":
			mnemonicOpcode = 7;
			mnemonicLength = 3;
			instructionType = "IS";
			break;
		case "DIV":
			mnemonicOpcode = 8;
			mnemonicLength = 3;
			instructionType = "IS";
		case "READ":
			mnemonicOpcode = 9;
			mnemonicLength = 3;
			instructionType = "IS";
			break;
		case "PRINT":
			mnemonicOpcode = 10;
			mnemonicLength = 3;
			instructionType = "IS";
			break;
		case "DC":
			mnemonicOpcode = 1;
			mnemonicLength = 3;
			instructionType = "DL";
			break;
		case "DS":
			mnemonicOpcode = 2;
			mnemonicLength = 3;
			instructionType = "DL";
			break;
		case "START":
			mnemonicOpcode = 1;
			mnemonicLength = 3;
			instructionType = "AD";
			break;
		case "END":
			mnemonicOpcode = 2;
			mnemonicLength = 3;
			instructionType = "AD";
			break;
		case "ORIGIN":
			mnemonicOpcode = 3;
			mnemonicLength = 3;
			instructionType = "AD";
			break;
		case "EQU":
			mnemonicOpcode = 4;
			mnemonicLength = 3;
			instructionType = "AD";
			break;
		case "LTORG":
			mnemonicOpcode = 5;
			mnemonicLength = 1;
			instructionType = "AD";
			break;
		default:
			//System.out.println("Mnemonic not in range !");
			mnemonicOpcode = 0;
			mnemonicLength = 0;
			instructionType = "";
		}
	}

}

