package game.shellda;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Dossier extends Element {

	String m_name;

	Noeud m_contenu;

	public Dossier(Noeud courant, Model model, int x, int y, String name, Noeud contenu) {
		super(courant, model, x, y);
		m_contenu = contenu;
		m_name = name;
	}

	public void paint(Graphics g) {
		if(m_name.equals("..")) {
			g.drawImage(m_model.m_dossierRetourSprite, m_x * 48 + 8, m_y * 48, 32, 32, null);
		}
		else {
			g.drawImage(m_model.m_dossierSprite, m_x * 48 + 8, m_y * 48, 32, 32, null);
		}
		
		g.setFont(m_model.m_font);
		FontMetrics f = g.getFontMetrics();
		g.drawString(m_name, m_x * 48 + (48 - f.stringWidth(m_name))/2, m_y * 48 + 32 + (16 / 2));
	}
}
