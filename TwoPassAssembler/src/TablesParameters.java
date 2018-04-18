
public class TablesParameters {

	String token;
	int address;
	public TablesParameters(String token, int address) {
		super();
		this.token = token;
		this.address = address;
	}

	@Override
	public String toString() {
		return "TablesParameters [token=" + token + ", address=" + address + "]";
	}
}
