import java.util.ArrayList;

public class Tables {
	//PNT MNT SS SSN EV EVN KPD MDT APT
	//MNT : name,pp,kp,evn,mdt,kpd,ss
ArrayList<String> MNTAB = new ArrayList<String>();
ArrayList<String> PNTAB = new ArrayList<String>();
//Sequencing symbol name : .MORE
ArrayList<String> SSNTAB= new ArrayList<String>();
ArrayList<String> SSTAB = new ArrayList<String>();
//Expansion time variable name table : &M
ArrayList<String> EVNTAB = new ArrayList<String>();
ArrayList EVTAB = new ArrayList();
//Keyword parameter table : parameter name,Parameter Value : &REG=AREG
ArrayList<KPDTABParameters> KPDTAB = new ArrayList<KPDTABParameters>();
//Macro definition table : label,mnemonic,operand
ArrayList<MDTParameters> MDTAB = new ArrayList<MDTParameters>();
//Actual parameter table : AREG in &REG = AREG
ArrayList<String> APTAB = new ArrayList<String>();

}
