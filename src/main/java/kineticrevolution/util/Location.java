package kineticrevolution.util;

/**
 * Created by AEnterprise
 */
public class Location {
	public int x, y, z;

	public Location(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Location copy() {
		return new Location(x, y, z);
	}

	public Location offset(int x, int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
}
