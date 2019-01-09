/**
 * Represents a vector in three-dimensional space.
 * Instances of this class are immutable.
 */
public final class Vector3D {
	private final double x;
	private final double y;
	private final double z;

	/**
	 * Creates a new Vector3D objects with the specified x, y, and z components.
	 * @param x the x-component of this vector
	 * @param y the y-component of this vector
	 * @param z the z-component of this vector
	 */
	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Gets the x-component of this vector.
	 * @return the x-component
	 */
	public double getX() {
		return x;
	}

	/**
	 * Gets the y-component of this vector.
	 * @return the y-component
	 */
	public double getY() {
		return y;
	}

	/**
	 * Gets the z-component of this vector.
	 * @return the z-component
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Multiplies each entry of this vector by a scalar multiple.
	 * @param scalar the amount to scale this vector by
	 * @return a new Vector3D with each entry scaled
	 */
	public Vector3D scale(double scalar) {
		return new Vector3D(x * scalar, y * scalar, z * scalar);
	}

	/**
	 * Adds this vector to another vector.
	 * @param other the vector to be added
	 * @return a new Vector3D that is equal to the sum of the two vectors
	 */
	public Vector3D add(Vector3D other) {
		return new Vector3D(x + other.x, y + other.y, z + other.z);
	}

	/**
	 * Subtracts another vector from this vector.
	 * @param other the vector to be subtracted
	 * @return a new Vector3D that is equal to the difference between the two vectors
	 */
	public Vector3D subtract(Vector3D other) {
		return add(other.scale(-1));
	}

	/**
	 * Computes the dot product between this vector and another vector.
	 * @param other the vector to perform a dot product with
	 * @return the dot product
	 */
	public double dot(Vector3D other) {
		return x * other.x + y * other.y + z * other.z;
	}

	/**
	 * Gets the magnitude (or length) of this vector.
	 * @return the magnitude of the vector
	 */
	public double magnitude() {
		return Math.sqrt(this.dot(this));
	}

	/**
	 * Returns a unit vector that is parallel to this vector.
	 * Due to round-off errors, it is not guaranteed that the vector returned
	 * by this method will have a magnitude exactly equal to 1.0.
	 * @return a unit vector with the same direction as this vector
	 */
	public Vector3D normalize() {
		return scale(1 / magnitude());
	}
}
