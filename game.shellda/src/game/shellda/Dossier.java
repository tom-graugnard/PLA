package game.shellda;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Dossier extends Fichier {

	Noeud m_enfant;

	public Dossier(Noeud enfant, Noeud courant, String name, Model model, int no, BufferedImage sprite, int rows, int columns, int x,
			int y, float scale) {
		super(courant, model, no, sprite, rows, columns, x, y, scale);
		m_enfant = enfant;
		m_name = name;
		c = Color.orange;
	}
}
