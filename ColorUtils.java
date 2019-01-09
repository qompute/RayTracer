import java.awt.Color;

public class ColorUtils {
	public static Color scaleColor(Color c, double scaleFactor) {
		int r = (int) (c.getRed() * scaleFactor);
		int g = (int) (c.getGreen() * scaleFactor);
		int b = (int) (c.getBlue() * scaleFactor);
		return new Color(r, g, b);
	}

	public static Color mix(Color c1, Color c2, double ratio) {
		int r = (int) (c1.getRed() * ratio + c2.getRed() * (1 - ratio));
		int g = (int) (c1.getGreen() * ratio + c2.getGreen() * (1 - ratio));
		int b = (int) (c1.getBlue() * ratio + c2.getBlue() * (1 - ratio));
		return new Color(r, g, b);
	}
}
