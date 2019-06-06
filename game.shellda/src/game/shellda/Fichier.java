package game.shellda;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Fichier extends Element{

	String m_name;
	
	int m_infection;
	
	public Fichier(Noeud courant, Model model, int x, int y, String name) {
		super(courant, model, x, y);
		m_name = name;
		c = Color.cyan;
	}
	
	public void paint(Graphics g) {
		g.drawImage(m_model.m_fichierSprite, m_x*48 + 8, m_y*48, 32, 32, null);
		
		g.setFont(m_model.m_font);
		FontMetrics f = g.getFontMetrics();
		g.drawString(m_name, m_x * 48 + (48 - f.stringWidth(m_name))/2, m_y * 48 + 32 + (16 / 2));
	}

}
