package game.shellda;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Dossier extends Element {

	String m_name;
	
	Noeud m_contenu;

	public Dossier(Noeud courant, Model model, int x, int y, String name, Noeud contenu) {
		super(courant, model, x, y);
		m_contenu = contenu;
		m_name = name;
		c = Color.orange;
	}
}
