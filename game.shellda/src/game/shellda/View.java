package game.shellda;

import java.awt.Color;
import java.awt.Graphics;

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
		
		
		m_model.m_courant.paint(g);

	}

}
