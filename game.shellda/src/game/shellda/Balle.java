package game.shellda;

import java.awt.Graphics;

import game.shellda.Clink.ClinkCorb;
import interpreter.IDirection;
import interpreter.IKind;

public class Balle extends Element {
	public Balle(Noeud courant, Model model, int x, int y) {
		super(courant, model, x, y);
		m_kind = new IKind("B");
		m_auto = m_model.m_automateBalle.copy();
	}

	public void paint(Graphics g) {
		g.drawImage(m_model.m_balleSprite, m_x_visu + 8, m_y_visu + 8, 32, 32, null);
	}

	long w = 0;

	public void step(long now) throws Exception {
		if(m_x >= Options.LARGEUR_CARTE - 1) {
			((ClinkCorb)m_model.m_joueur).m_lasers.remove(this);
			m_courant.m_carte[m_x][m_y] = null;
			return;
		}
		if (m_auto != null)
			m_auto.step(this);
		update(now);
	}

	public void Hit(IDirection direction) {
		if (!(m_courant.m_carte[m_x + 1][m_y] instanceof Balle)) {
			m_courant.m_carte[m_x][m_y] = null;
			m_courant.m_carte[m_x + 1][m_y] = null;
			((ClinkCorb)m_model.m_joueur).m_lasers.remove(this);
		}
	}
	
	public boolean equals(Balle b) {
		return b == this;
	}

}
