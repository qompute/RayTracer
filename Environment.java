import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a three-dimensional environment with various objects.
 */
public class Environment {
	private List<Material3D> objects;
	private Color background;
	private double ambient;
	private Vector3D light;

	public Environment() {
		objects = new ArrayList<>();
		background = Color.BLACK;
		ambient = 0.1;
		light = new Vector3D(2, 2, -1);
	}

	public void addObject(Material3D object) {
		objects.add(object);
	}

	/**
	 * Draws the view of this environment from the given camera on the given image.
	 * This method scales the camera's coordinate system to fit within the image.
	 * @param camera the camera displaying the desired view to draw
	 * @param image the image to draw on
	 */
	public void draw(Camera3D camera, BufferedImage image) {
		final int IMAGE_SIZE = Math.min(image.getWidth(), image.getHeight());
		for(int x = 0; x < image.getWidth(); x++) {
			for(int y = 0; y < image.getHeight(); y++) {
				double xCoord = (double) (x - image.getWidth() / 2) / IMAGE_SIZE;
				double yCoord = (double) (image.getHeight() - y - image.getHeight() / 2) / IMAGE_SIZE;
				Vector3D ray = camera.getDirection(xCoord, yCoord);
				Color color = traceRay(camera.getLocation(), ray, 5);
				if(color == null) {
					image.setRGB(x, y, background.getRGB());
				}
				else {
					image.setRGB(x, y, color.getRGB());
				}
			}
		}
	}

	// Traces a ray from a source point to the nearest material.
	// Returns null if there is no object in the ray's path.
	private Color traceRay(Vector3D source, Vector3D direction, int depth) {
		Material3D closest = null;
		Vector3D surface = null;
		double minDistance = Double.MAX_VALUE;
		for(Material3D object : objects) {
			Vector3D intersection = object.getIntersection(source, direction);
			if(intersection != null) {
				double distance = intersection.subtract(source).magnitude();
				if(distance < minDistance) {
					closest = object;
					surface = intersection;
					minDistance = distance;
				}
			}
		}
		if(closest == null) {
			return null;
		}
		return illumination(closest, surface, direction, depth);
	}

	// Calculates the color at the specified point based on illumination.
	private Color illumination(Material3D material, Vector3D surface, Vector3D direction, int depth) {
		Vector3D normal = material.getNormal(surface);
		Vector3D toLight = light.subtract(surface).normalize();
		double intensity = normal.dot(toLight);
		if(intensity < ambient)
			intensity = ambient;
		Color direct = ColorUtils.scaleColor(material.getColorAt(surface), intensity);
		if(depth <= 1) {
			return direct;
		}
		else {
			double cosine = direction.dot(normal);
			Vector3D reflection = direction.subtract(normal.scale(2 * cosine));
			Color reflected = traceRay(surface, reflection, depth - 1);
			if(reflected == null) {
				return direct;
			}
			else {
				double ratio = 1 - material.getReflectanceAt(surface);
				ratio = ratio + ((1 - ratio) * Math.pow(intensity, 30));
				return ColorUtils.mix(direct, reflected, ratio);
			}
		}
	}
}
