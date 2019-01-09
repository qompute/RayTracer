import java.awt.*;

/**
 * Base class for shapes with uniform color.
 */
public abstract class SolidMaterial extends Material3D {
	private Color color;

	public SolidMaterial(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public Color getColorAt(Vector3D surface) {
		return color;
	}
}
