import java.awt.*;

/**
 * Represents a 3-dimensional material that can be viewed.
 */
public abstract class Material3D {
	public static final double RAY_OFFSET = 0.001;

	/**
	 * Gets the closest intersection point of a ray with this object.
	 * This method only returns points whose distance from the ray source
	 * is greater than RAY_OFFSET.
	 * @param source the source of the ray
	 * @param direction a unit vector representing the direction of the ray
	 * @return the closest intersection point, or null if the ray never intersects with this material
	 */
	public abstract Vector3D getIntersection(Vector3D source, Vector3D direction);

	/**
	 * Gets a unit vector representing the normal vector of a given point on the surface.
	 * @param surface the point on the object's surface
	 * @return a unit vector perpendicular to the plane of the object at that surface point
	 */
	public abstract Vector3D getNormal(Vector3D surface);

	/**
	 * Gets the color of this object at the given location.
	 * @param surface the point on the object's surface
	 * @return the color of the material at that surface point
	 */
	public abstract Color getColorAt(Vector3D surface);

	/**
	 * Gets the diffuse reflectance scaling factor at the given point on the surface.
	 * @param surface the point on the object's surface
	 * @return the ratio of reflection at the given point
	 */
	public abstract double getReflectanceAt(Vector3D surface);
}
