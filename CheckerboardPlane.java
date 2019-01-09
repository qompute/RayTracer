import java.awt.*;

/**
 * A checkerboard-patterned plane, intended for use as a background.
 */
public class CheckerboardPlane extends Material3D {
	private Color darkColor;
	private Color lightColor;
	private Vector3D location;
	private Vector3D normal;

	/**
	 * Creates a new CheckerboardPlane with default values.
	 * A plane is defined by a single point on the plane and a normal vector.
	 * @param location the location of any single point on the plane
	 * @param normal a vector perpendicular to the plane
	 */
	public CheckerboardPlane(Vector3D location, Vector3D normal) {
		this.location = location;
		this.normal = normal;
		darkColor = Color.BLUE.darker();
		lightColor = Color.WHITE;
	}

	/**
	 * Gets the closest intersection point of a ray with this object.
	 * This method only returns points whose distance from the ray source
	 * is greater than RAY_OFFSET.
	 *
	 * @param source    the source of the ray
	 * @param direction a unit vector representing the direction of the ray
	 * @return the closest intersection point, or null if the ray never intersects with this material
	 */
	@Override
	public Vector3D getIntersection(Vector3D source, Vector3D direction) {
		double t = location.subtract(source).dot(normal) / direction.dot(normal);
		if(t > RAY_OFFSET) {
			return source.add(direction.scale(t));
		}
		else {
			return null;
		}
	}

	/**
	 * Gets a unit vector representing the normal vector of a given point on the surface.
	 *
	 * @param surface the point on the object's surface
	 * @return a unit vector perpendicular to the plane of the object at that surface point
	 */
	@Override
	public Vector3D getNormal(Vector3D surface) {
		return normal;
	}

	/**
	 * Gets the color of this object at the given location.
	 *
	 * @param surface the point on the object's surface
	 * @return the color of the material at that surface point
	 */
	@Override
	public Color getColorAt(Vector3D surface) {
		int i = (int) Math.floor(surface.getX() / 0.5);
		int j = (int) Math.floor(surface.getY() / 0.5);
		if((i + j) % 2 == 0) {
			return lightColor;
		}
		else {
			return darkColor;
		}
	}

	/**
	 * Gets the diffuse reflectance scaling factor at the given point on the surface.
	 *
	 * @param surface the point on the object's surface
	 * @return the ratio of reflection at the given point
	 */
	@Override
	public double getReflectanceAt(Vector3D surface) {
		return 0;
	}
}
