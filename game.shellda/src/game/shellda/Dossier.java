package game.shellda;

import java.awt.FontMetrics;
import java.awt.Graphics;

import interpreter.IDirection;
import interpreter.IKind;

public class Dossier extends Element {

	String m_name;
	int auto;
	Noeud m_contenu;

	public Dossier(Noeud courant, Model model, int x, int y, String name, Noeud contenu) {
		super(courant, model, x, y);
		m_contenu = contenu;
		m_name = name;
		m_kind = new IKind("D");
		m_auto = m_model.m_automate[m_model.m_autoChoix[5]].copy();
		auto = m_model.m_autoChoix[5];
	}
	
	public void Wizz(IDirection direction) {
		m_courant.m_carte[m_x][m_y] = new Corbeille(m_courant, m_model, m_x, m_y, m_contenu);
	}

	public void Pop(IDirection direction) {
		m_name = m_model.m_generator.generate_folder();
	}
	
	public void step(long now) throws Exception {
		if (auto != m_model.m_autoChoix[5]) {
			m_auto = m_model.m_automate[m_model.m_autoChoix[5]].copy();
			auto = m_model.m_autoChoix[5];
		}
		if (m_auto != null)
			m_auto.step(this);
		update(now);
	}

	public void paint(Graphics g) {
		if(m_name.equals("..")) {
			g.drawImage(m_model.m_dossierRetourSprite, m_x * 48 + 8, m_y * 48 + 10, 32, 32, null);
		}
		else {
			g.drawImage(m_model.m_dossierSprite, m_x * 48 + 8, m_y * 48 + 10, 32, 32, null);
		}
		
		g.setFont(m_model.m_font);
		FontMetrics f = g.getFontMetrics();
		g.drawString(m_name, m_x * 48 + (48 - f.stringWidth(m_name))/2, m_y * 48 + 42 + (16 / 2));
	}
}
