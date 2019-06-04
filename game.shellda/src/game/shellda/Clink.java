package game.shellda;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Clink extends Element {

	public Clink(Noeud n, Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale) {
		super(n, model, no, sprite, rows, columns, x, y, scale);
		c = Color.black;
	}

}
