package game.shellda;

import java.awt.FontMetrics;
import java.awt.Graphics;

import interpreter.IDirection;
import interpreter.IKind;

public class Corbeille extends Dossier {
	
	public Corbeille(Noeud courant, Model model, int x, int y, Noeud contenu)  {
		super(courant, model, x, y, "Corbeille", contenu);
		m_kind = new IKind("O");
	}
	
	public void Wizz(IDirection direction) {
		m_courant.m_carte[m_x][m_y] = new Dossier(m_courant, m_model, m_x, m_y, m_name, m_contenu);
	}

	public void Pop(IDirection direction) {
		if(m_name.equals("Corbeille")) {
			m_name = "Trash";
		}else {
			m_name = "Corbeille";
		}
	}

	public void paint(Graphics g) {
		
		g.drawImage(m_model.m_corbeilleSprite, m_x * 48 + 8, m_y * 48 + 10, 32, 32, null);
		
		g.setFont(m_model.m_font);
		FontMetrics f = g.getFontMetrics();
		g.drawString(m_name, m_x * 48 + (48 - f.stringWidth(m_name))/2, m_y * 48 + 42 + (16 / 2));
	}
	
}
