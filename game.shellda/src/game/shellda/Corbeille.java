package game.shellda;

import java.awt.Color;

public class Corbeille extends Dossier {

	public Corbeille(Noeud courant, Model model, int x, int y, Noeud contenu)  {
		super(courant, model, x, y, "corbeille", contenu);
		
		c = Color.darkGray;
	}

}
