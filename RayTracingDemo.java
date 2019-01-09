import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class RayTracingDemo {
	private static final int IMAGE_WIDTH = 600;
	private static final int IMAGE_HEIGHT = 600;
	private static final Vector3D camera = new Vector3D(0, 1, 0);
	private static final Vector3D light = new Vector3D(2, 2, 0);
	private static final Sphere[] spheres = {
			new Sphere(0, -500, 0, 500, Color.YELLOW),
			new Sphere(0, 0, 3, 1, Color.RED),
			new Sphere(-2, 1, 4, 1, Color.GREEN),
			new Sphere(2, 1, 4, 1, Color.BLUE)
	};
	private static final double AMBIENT_LIGHT = 0.1;

	public static void main(String[] args) {
		Environment environment = new Environment();
		environment.addObject(new Sphere(0, 0, 2, 1, Color.RED));
		environment.addObject(new Sphere(2, 0, 3, 1, Color.GREEN));
		environment.addObject(new Sphere(-2, 0, 3, 1, Color.YELLOW));

		BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		environment.draw(new Camera3D(), image);

		JFrame frame = new JFrame("Ray Tracing Demo");
		ImageIcon icon = new ImageIcon(image);
		frame.add(new JLabel(icon));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private static BufferedImage getImage() {
		BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < IMAGE_WIDTH; i++) {
			for (int j = 0; j < IMAGE_HEIGHT; j++) {
				image.setRGB(i, j, getColorOfPixel(i, IMAGE_HEIGHT - j).getRGB());
			}
		}
		return image;
	}

	private static Color getColorOfPixel(int x, int y) {
		final int IMAGE_SIZE = Math.min(IMAGE_HEIGHT, IMAGE_WIDTH);
		Vector3D direction = new Vector3D((double) (x - IMAGE_WIDTH / 2) / IMAGE_SIZE,
				(double) (y - IMAGE_HEIGHT / 2) / IMAGE_SIZE, 1).normalize();
		return traceRay(camera, direction, 4);
	}

	private static Color traceRay(Vector3D source, Vector3D direction, int depth) {
		Sphere closest = null;
		Vector3D surface = null;
		double minDistance = Double.MAX_VALUE;
		for (Sphere sphere : spheres) {
			Vector3D sphereSurface = sphere.getIntersection(source, direction);
			if (sphereSurface != null) {
				double distance = sphereSurface.subtract(source).magnitude();
				if (distance < minDistance) {
					closest = sphere;
					minDistance = distance;
					surface = sphereSurface;
				}
			}
		}
		if (closest == null) {
			return Color.BLACK;
		}
		return illumination(surface, closest, direction, depth);
	}

	private static Color illumination(Vector3D surface, Sphere sphere,
									  Vector3D direction, int depth) {
		Vector3D toSurface = surface.subtract(sphere.getCenter()).normalize();
		Vector3D toLight = light.subtract(surface).normalize();
		double intensity = toSurface.dot(toLight);
		if (intensity < AMBIENT_LIGHT) {
			intensity = AMBIENT_LIGHT;
		}
		Color direct = ColorUtils.scaleColor(sphere.getColor(), intensity);
		if (depth <= 1)
			return direct;
		else {
			double cosine = direction.dot(toSurface);
			Vector3D bounce = direction.subtract(toSurface.scale(2 * cosine));
			Color reflected = traceRay(surface, bounce, depth - 1);
			return ColorUtils.mix(direct, reflected, 0.5 + 0.5 * Math.pow(intensity, 30));
		}
	}
}
