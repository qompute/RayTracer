import java.awt.*;

/**
 * Base class for shapes with uniform color.
 */
public abstract class SolidMaterial extends Material3D {
	private Color color;
	private double reflectance;

	public SolidMaterial(Color color, double reflectance) {
		this.color = color;
		this.reflectance = reflectance;
	}

	public Color getColor() {
		return color;
	}

	public Color getColorAt(Vector3D surface) {
		return color;
	}

	public double getReflectanceAt(Vector3D surface) {
		return reflectance;
	}
}
