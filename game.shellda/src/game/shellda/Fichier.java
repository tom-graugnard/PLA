package game.shellda;

import java.awt.FontMetrics;
import java.awt.Graphics;

import interpreter.IDirection;
import interpreter.IKind;

public class Fichier extends Element {

	String m_name;

	int m_infection;

	public Fichier(Noeud courant, Model model, int x, int y, String name) {
		super(courant, model, x, y);
		m_kind = new IKind("P");
		m_name = name;
		
	}

	public void paint(Graphics g) {
		g.drawImage(m_model.m_fichierSprite, m_x * 48 + 8, m_y * 48, 32, 32, null);

		g.setFont(m_model.m_font);
		FontMetrics f = g.getFontMetrics();
		g.drawString(m_name, m_x * 48 + (48 - f.stringWidth(m_name)) / 2, m_y * 48 + 32 + (16 / 2));
	}
	
	long w = 0;

	public void step(long now) throws Exception {
		if (now - w > 1000) {
			if (m_auto != null)
				m_auto.step(this);
			w = now;
		}
	}
	
	public static class FichNorm extends Fichier {

		public FichNorm(Noeud courant, Model model, int x, int y, String name) {
			super(courant, model, x, y, name);
			// TODO Auto-generated constructor stub
		}
	}
	
	public static class FichCorb extends Fichier {

		public FichCorb(Noeud courant, Model model, int x, int y, String name) {
			super(courant, model, x, y, name);
			// TODO Auto-generated constructor stub
			m_auto = m_model.m_automateFichier.copy();
		}
		
	}

}
