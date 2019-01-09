public final class Vector3D {
	private final double x;
	private final double y;
	private final double z;

	public Vector3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public Vector3D scale(double scalar) {
		return new Vector3D(x * scalar, y * scalar, z * scalar);
	}

	public Vector3D add(Vector3D other) {
		return new Vector3D(x + other.x, y + other.y, z + other.z);
	}

	public Vector3D subtract(Vector3D other) {
		return add(other.scale(-1));
	}

	public double dot(Vector3D other) {
		return x * other.x + y * other.y + z * other.z;
	}

	public double magnitude() {
		return Math.sqrt(this.dot(this));
	}

	public Vector3D normalize() {
		return scale(1 / magnitude());
	}
}
