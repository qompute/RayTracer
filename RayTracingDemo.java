import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class RayTracingDemo {
	private static final int IMAGE_WIDTH = 600;
	private static final int IMAGE_HEIGHT = 600;

	public static void main(String[] args) {
		Environment environment = new Environment();
		environment.addObject(new Sphere(0, -500, 0, 499, Color.LIGHT_GRAY, 0.5));
		environment.addObject(new Sphere(0, -1, 3, 1, Color.BLUE, 0.5));
		environment.addObject(new Sphere(1.5, 0, 6, 1, Color.RED, 0.5));
		environment.addObject(new Sphere(-1.5, 0, 5, 1, Color.GREEN, 0.5));
		environment.addObject(new Sphere(0, 1, 6, 0.5, Color.WHITE, 1.0));

		BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		environment.draw(new Camera3D(), image);

		JFrame frame = new JFrame("Ray Tracing Demo");
		ImageIcon icon = new ImageIcon(image);
		frame.add(new JLabel(icon));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		try {
			ImageIO.write(image, "png", new File("demo.png"));
		} catch (IOException ex) {
			System.err.println("Could not save image: " + ex.getMessage());
		}
	}
}
