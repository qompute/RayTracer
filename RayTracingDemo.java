import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class RayTracingDemo {
	private static final int IMAGE_WIDTH = 600;
	private static final int IMAGE_HEIGHT = 600;

	public static void main(String[] args) {
		Environment environment = new Environment();
		environment.addObject(new Sphere(0, -0.3, 8, 1, Color.RED, 0.5));
		environment.addObject(new Sphere(2, 0, 10, 1, Color.GREEN, 0.5));
		environment.addObject(new Sphere(-2, 0, 10, 1, Color.YELLOW, 0.5));
		environment.addObject(new CheckerboardPlane(new Vector3D(0, 0, 12), new Vector3D(0, 0, -1)));

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
}
