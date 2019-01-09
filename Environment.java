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

	public Environment() {
		objects = new ArrayList<>();
		background = Color.BLACK;
		ambient = 0.1;
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
				image.setRGB(x, y, color.getRGB());
			}
		}
	}

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
			return background;
		}
		return closest.getColorAt(surface); // TODO: change this line to include illumination
	}
}
