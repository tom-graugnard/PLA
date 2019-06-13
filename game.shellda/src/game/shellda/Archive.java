package game.shellda;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.LinkedList;

import game.shellda.Fichier.FichNorm;
import interpreter.IDirection;
import interpreter.IKind;

public class Archive extends Element {

	String m_name;

	LinkedList<Element> m_contenu;
	
	int auto;
	
	public Archive(Noeud courant, Model model, int x, int y, String name) {
		super(courant, model, x, y);
		m_name = name;
		m_kind = new IKind("G");
		m_auto = m_model.m_automate[m_model.m_autoChoix[5]].copy();
		auto = m_model.m_autoChoix[5];
	}
	
	public void Wizz(IDirection direction) {
		m_courant.m_carte[m_x][m_y] = new FichNorm(m_courant, m_model, m_x, m_y, m_name);
	}

	public void Pop(IDirection direction) {
		m_name = m_model.m_generator.generate_compressed();
	}

	public Element decompression() {
		return new Fichier(m_courant,m_model,m_x,m_y,m_model.m_generator.generate_file());
	}

	public void paint(Graphics g) {
		g.drawImage(m_model.m_archiveSprite, m_x * 48 + 8, m_y * 48 + 10, 32, 32, null);

		g.setFont(m_model.m_font);
		FontMetrics f = g.getFontMetrics();
		g.drawString(m_name, m_x * 48 + (48 - f.stringWidth(m_name)) / 2, m_y * 48 + 42 + (16 / 2));
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
}
