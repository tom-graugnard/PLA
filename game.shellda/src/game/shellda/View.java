package game.shellda;

import java.awt.Color;
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
	Color m_background = new Color(212, 212, 212);// Color.gray;
	long m_last;
	int m_npaints;
	int m_fps;
	boolean choix = false;

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

		if (!m_model.gameStart)
			if (!m_model.gameOption)
				_menuPrincipal(g);
			else {
				_FenetreOption(g);

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
		g.drawString(s, Options.WIDTH / 2 - s.length() * 3, Options.HEIGHT / 2 - 60);
		
		m_model.m_logo.paint(g);
		m_model.m_boutonplay.paint(g);
		m_model.m_boutonexit.paint(g);
		
		m_model.m_boutonoption.paint(g);

	}

	protected void _FenetreJeu(Graphics g) {
		g.setColor(Color.BLACK);
		g = g.create(0, 0, Options.WIDTH, Options.HEIGHT);
		m_model.m_courant.paint(g);
		if(m_model.m_pourcentage_defaite > 66) {
			g.setColor(new Color(231, 89, 82));
		}
		else if(m_model.m_pourcentage_defaite > 33) {
			g.setColor(new Color(149, 105, 200));
		}
		else {
			g.setColor(new Color(195, 167, 225));
		}
		g.fillRect(0, 0, m_model.m_pourcentage_defaite * Options.WIDTH / 100, 10);
	}
	
	protected void _FenetreDefaite(Graphics g) {
		g.setColor(Color.BLACK);
		g = g.create(0, 0, Options.WIDTH, Options.HEIGHT);
		m_model.m_boutonreplay.paint(g);
		m_model.m_boutonexit1.paint(g);

	protected void _FenetreOption(Graphics g) {
		choix = true;
		m_model.m_boutonexit.paint(g);
		g.setColor(new Color(72, 72, 72));
		for (int i = 0; i < 6; i++) {
			g.fillRect(20, 10 + i * 70, 90, 50);
		}
		g.setColor(new Color(88, 174, 238));
		for (int i = 0; i < 6; i++) {
			g.fillRect(25, 15 + i * 70, 80, 40);
		}
		g.setColor(new Color(72, 72, 72));
		g.drawString("Virus", 25 + 20, 15 + 25);
		g.drawString("Joueur1", 45, 40 + 70);
		g.drawString("Joueur2", 45, 40 + 140);
		g.drawString("Fichier", 45, 40 + 210);
		g.drawString("Balle", 45, 40 + 280);
		g.drawString("Archive", 45, 40 + 350);

		g.setColor(new Color(72, 72, 72));
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				g.fillRect(145 + j * 90, 10 + i * 70, 90, 50);
			}

			g.setColor(new Color(72, 72, 72));
			for (int k = 0; k < 6; k++) {
				g.fillRect(145 + m_model.m_autoChoix[i] * 90, 10 + i * 70, 90, 50);
			}

			g.setColor(new Color(72, 72, 72));
		}

		g.setColor(new Color(231, 89, 82));
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				g.fillRect(150 + j * 90, 15 + i * 70, 80, 40);
			}

			g.setColor(new Color(68, 197, 91));
			for (int k = 0; k < 6; k++) {
				g.fillRect(150 + m_model.m_autoChoix[i] * 90, 15 + i * 70, 80, 40);
			}
			g.setColor(new Color(72, 72, 72));
			g.drawString("Virus", 150 + 20, 15 + 25 + i * 70);
			g.drawString("Joueur1", 170 + 90, 15 + 25 + i * 70);
			g.drawString("Joueur2", 170 + 180, 15 + 25 + i * 70);
			g.drawString("Fichier", 170 + 270, 15 + 25 + i * 70);
			g.drawString("Balle", 170 + 360, 15 + 25 + i * 70);
			g.drawString("Archive", 170 + 450, 15 + 25 + i * 70);

			g.setColor(new Color(231, 89, 82));
		}

	}

	protected boolean inside(int i, int j, int x, int y, int w, int h) {
		if (i >= x && i < x + w && j >= y && j < y + h)
			return true;
		else
			return false;
	}

	public void step(long now) {

	}

}
