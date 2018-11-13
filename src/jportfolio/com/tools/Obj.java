package jportfolio.com.tools;

public class Obj {
	
	private int key;
	private String value;
	private double valueDouble;
	
	public Obj(int key, String value, double valueDouble) {
		super();
		this.key = key;
		this.value = value;
		this.valueDouble = valueDouble;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public double getValueDouble() {
		return valueDouble;
	}

	public void setValueDouble(double valueDouble) {
		this.valueDouble = valueDouble;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
        str.append("Key:" + getKey()+ " Value: " + getValue() + " ValueDouble: " + getValueDouble());
        return str.toString();
	}

}