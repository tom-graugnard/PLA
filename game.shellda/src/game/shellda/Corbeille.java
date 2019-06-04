package game.shellda;

import java.awt.Color;

public class Corbeille extends Dossier {
	

	public Corbeille(Noeud enfant, Noeud courant, int x, int y) {
		super(enfant, courant, "corbeille", x, y);
		c = Color.darkGray;
	}

}
