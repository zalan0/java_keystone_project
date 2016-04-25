package visualization;

/**
 * Simple class to contain an (x, y) Cartesian coordinate.
 * 
 * @author jnzastrow
 *
 */
public class Coordinate {
	private float x;
	private float y;
	
	public Coordinate(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
}
