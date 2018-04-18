
public class MDTParameters {
String label;
String mnemonic;
String operand;
public MDTParameters() {
	this.label = "";
	this.mnemonic = "";
	this.operand = "";
}
public MDTParameters(String label, String mnemonic, String operand) {
	super();
	this.label = label;
	this.mnemonic = mnemonic;
	this.operand = operand;
}
@Override
public String toString() {
	return "\n"+label + "\t" + mnemonic + "\t" + operand ;
}


}
