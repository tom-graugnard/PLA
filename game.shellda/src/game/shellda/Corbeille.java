package game.shellda;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Corbeille extends Dossier {

	public Corbeille(Noeud enfant, Noeud courant, Model model, int no, BufferedImage sprite, int rows, int columns,
			int x, int y, float scale) {
		super(enfant, courant, "corbeille", model, no, sprite, rows, columns, x, y, scale);
		c = Color.darkGray;
	}

}
