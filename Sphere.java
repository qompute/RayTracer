import java.awt.*;

public final class Sphere extends SolidMaterial {
	private final Vector3D center;
	private final double radius;

	public Sphere(double x, double y, double z, double radius, Color color, double reflectance) {
		super(color, reflectance);
		this.center = new Vector3D(x, y, z);
		this.radius = radius;
	}

	public Sphere(Vector3D center, double radius, Color color, double reflectance) {
		super(color, reflectance);
		this.center = center;
		this.radius = radius;
	}

	public Vector3D getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
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
		Vector3D v = source.subtract(center);
		double b = -v.dot(direction);
		double discriminant = b * b - v.dot(v) + radius * radius;
		if(discriminant < 0)
			return null;
		double root = b - Math.sqrt(discriminant);
		if(root > RAY_OFFSET) {
			return source.add(direction.scale(root));
		}
		root = b + Math.sqrt(discriminant);
		if(root > RAY_OFFSET) {
			return source.add(direction.scale(root));
		}
		return null;
	}

	/**
	 * Gets a unit vector representing the normal vector of a given point on the surface.
	 *
	 * @param surface the point on the object's surface
	 * @return a unit vector perpendicular to the plane of the object at that surface point
	 */
	@Override
	public Vector3D getNormal(Vector3D surface) {
		return surface.subtract(center).normalize();
	}
}
