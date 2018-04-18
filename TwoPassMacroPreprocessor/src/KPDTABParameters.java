
public class KPDTABParameters {
String parameterName;
String parameterValue;
public KPDTABParameters() {
	parameterName = new String();
	parameterValue = new String();
}
public KPDTABParameters(String parameterName, String parameterValue) {
	super();
	this.parameterName = parameterName;
	this.parameterValue = parameterValue;
}

@Override
public String toString() {
	return parameterName + ","+parameterValue;
}

}
