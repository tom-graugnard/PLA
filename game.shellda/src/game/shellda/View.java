package game.shellda;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.ricm3.game.GameView;

public class View extends GameView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Model m_model;
	Color m_background = Color.gray;
	long m_last;
	int m_npaints;
	int m_fps;

	public View(Model model) {
		m_model = model;
	}

	private void computeFPS() {
		long now = System.currentTimeMillis();
		if (now - m_last > 1000L) {
			m_fps = m_npaints;
			m_last = now;
			m_npaints = 0;
		}
		m_game.setFPS(m_fps, null);
		m_npaints++;
	}

	@Override
	protected void _paint(Graphics g) {
		computeFPS();

		// erase background
		g.setColor(m_background);
		g.fillRect(0, 0, Options.WIDTH, Options.HEIGHT);

		if (!m_model.gameStart) {
			_menuPrincipal(g);
		}
		else if(m_model.m_defaite) {
			_FenetreDefaite(g);
		}
		else if(m_model.gameStart){
			_FenetreJeu(g);
		}
	}

	protected void _menuPrincipal(Graphics g) {
		g.setColor(Color.BLACK);
		g = g.create(0, 0, Options.WIDTH, Options.HEIGHT);
		String s = "The Legend of Shellda";
		g.drawString(s, Options.WIDTH / 2 - s.length() * 3, Options.HEIGHT / 2 - 100);

		m_model.m_boutonplay.paint(g);
		m_model.m_boutonexit.paint(g);
	}

	protected void _FenetreJeu(Graphics g) {
		g.setColor(Color.BLACK);
		g = g.create(0, 0, Options.WIDTH, Options.HEIGHT);
		m_model.m_courant.paint(g);
		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, m_model.m_pourcentage_defaite * Options.WIDTH / 100, 10);
	}
	
	protected void _FenetreDefaite(Graphics g) {
		g.setColor(Color.BLACK);
		g = g.create(0, 0, Options.WIDTH, Options.HEIGHT);
		m_model.m_boutonreplay.paint(g);
		m_model.m_boutonexit1.paint(g);

	}


	public void step(long now) {
	}

}
