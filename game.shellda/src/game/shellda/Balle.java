package game.shellda;

import java.awt.Graphics;

import interpreter.IDirection;
import interpreter.IKind;

public class Balle extends Element {
	public Balle(Noeud courant, Model model, int x, int y) {
		super(courant, model, x, y);
		m_kind = new IKind("B");
		m_auto = m_model.m_automateBalle.copy();
	}

	public void paint(Graphics g) {
		g.drawImage(m_model.m_balleSprite, m_x * 48 + 8, m_y * 48 + 8, 32, 32, null);
	}

	long w = 0;

	public void step(long now) throws Exception {
		if (now - w > 200) {
			if (m_auto != null)
				m_auto.step(this);
			w = now;
		}
	}

	public void Hit(IDirection direction) {
		if (!(m_courant.m_carte[m_x + 1][m_y] instanceof Balle)) {
			m_courant.m_carte[m_x][m_y] = null;
			m_courant.m_carte[m_x + 1][m_y] = null;
			m_model.limitBalle--;
		}
	}

}
