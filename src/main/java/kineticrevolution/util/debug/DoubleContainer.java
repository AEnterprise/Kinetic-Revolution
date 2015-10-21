package kineticrevolution.util.debug;

/**
 * Created by AEnterprise
 */
public class DoubleContainer {
	private double value;

	public DoubleContainer(double value) {
		this.value = value;
		System.out.println("Init container with value " + value);
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value, boolean onClient) {
		System.out.println("value being modified, old value: " + this.value + " new value: " + value + " on client: " + onClient);
	}
}
