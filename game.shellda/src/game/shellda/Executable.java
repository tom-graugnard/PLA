package game.shellda;

import java.awt.FontMetrics;
import java.awt.Graphics;

public abstract class Executable extends Fichier {

	public Executable(Noeud courant, Model model, int x, int y, String name) {
		super(courant, model, x, y, name);
	}

	public abstract void interaction();
	
	public void paint(Graphics g) {
		g.drawImage(m_model.m_executableSprite, m_x * 48 + 8, m_y * 48, 32, 32, null);

		g.setFont(m_model.m_font);
		FontMetrics f = g.getFontMetrics();
		g.drawString(m_name, m_x * 48 + (48 - f.stringWidth(m_name)) / 2, m_y * 48 + 32 + (16 / 2));
	}
}
