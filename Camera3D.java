/**
 * Represents a "camera" in 3D space.
 */
public class Camera3D {
	private Vector3D location;
	private Vector3D viewportCenter;
	private Vector3D xBasis;
	private Vector3D yBasis;

	/**
	 * Creates a camera at (0, 0, -2) with default properties.
	 */
	public Camera3D() {
		this(new Vector3D(0, 0, -2),
				new Vector3D(0, 0, 2),
				new Vector3D(1, 0, 0),
				new Vector3D(0, 1, 0));
	}

	public Camera3D(Vector3D cameraLocation, Vector3D viewportDirection, Vector3D xBasis, Vector3D yBasis) {
		this.location = cameraLocation;
		this.viewportCenter = location.add(viewportDirection);
		this.xBasis = xBasis;
		this.yBasis = yBasis;
	}

	/**
	 * Returns the camera's point location.
	 * @return the camera's location
	 */
	public Vector3D getLocation() {
		return location;
	}

	/**
	 * Returns a unit vector representing the three-dimensional direction from the camera
	 * location to the given two-dimensional coordinate on the camera's viewport.
	 * @param x the x-coordinate of the camera's viewport
	 * @param y the y-coordinate of the camera's viewport
	 * @return a unit vector representing the direction from the camera to the specified point
	 */
	public Vector3D getDirection(double x, double y) {
		Vector3D point = viewportCenter.add(xBasis.scale(x).add(yBasis.scale(y)));
		return point.subtract(location).normalize();
	}
}
