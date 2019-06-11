package game.shellda;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.LinkedList;

import interpreter.IKind;

public class Archive extends Element{

	String m_name;
	
	LinkedList<Element> m_contenu;
 	
	public Archive(Noeud courant, Model model, int x, int y, String name) {
		super(courant, model, x, y);
		m_name = name;
		m_kind = new IKind("G");
	}

	public void paint(Graphics g) {
		g.drawImage(m_model.m_archiveSprite, m_x * 48 + 8, m_y * 48 + 10, 32, 32, null);
		
		g.setFont(m_model.m_font);
		FontMetrics f = g.getFontMetrics();
		g.drawString(m_name, m_x * 48 + (48 - f.stringWidth(m_name))/2, m_y * 48 + 42 + (16 / 2));
	}
}
