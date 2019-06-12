package game.shellda;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
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
	boolean choix=false;

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
			if(!m_model.gameOption)
				_menuPrincipal(g);
			else
				_FenetreOption(g);
		else
			_FenetreJeu(g);

	}

	protected void _menuPrincipal(Graphics g) {
		g.setColor(Color.BLACK);
		g = g.create(0, 0, Options.WIDTH, Options.HEIGHT);
		String s = "The Legend of Shellda";
		g.drawString(s, Options.WIDTH / 2 - s.length() * 3, Options.HEIGHT / 2 - 100);

		m_model.m_boutonplay.paint(g);
		m_model.m_boutonexit.paint(g);
		m_model.m_boutonoption.paint(g);
		
	}

	protected void _FenetreJeu(Graphics g) {
		g.setColor(Color.BLACK);
		g = g.create(0, 0, Options.WIDTH, Options.HEIGHT);
		m_model.m_courant.paint(g);
		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, m_model.m_pourcentage_defaite * Options.WIDTH / 100, 10);
	}
	
	protected void _FenetreOption(Graphics g) {
		choix=true;
		m_model.m_boutonexit.paint(g);
		g.setColor(Color.RED);
		for(int i=0;i<5;i++) {
			g.drawRect(30, 20 + i*80, 100, 50);
		}
		g.setColor(Color.green);
		g.drawString("Virus", 30+30, 20+30);
		g.drawString("Joueur1", 60, 50+80);
		g.drawString("Joueur2",60,50+160);
		g.drawString("Fichier", 60, 50+240);
		g.drawString("Balle", 60, 50+320);
		
		g.setColor(Color.CYAN);
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				g.drawRect(200 + j*100, 20 + i*80, 80, 50);
			}
			g.setColor(Color.green);
			g.drawString("Virus", 200+20, 20+30+i*80);
			g.drawString("Joueur1", 220+100, 20+30+i*80);
			g.drawString("Joueur2",220+200,20+30+i*80);
			g.drawString("Fichier", 220+300, 20+30+i*80);
			g.drawString("Balle", 220+400, 20+30+i*80);

			g.setColor(Color.cyan);
		}
	}
	
	protected boolean inside(int i,int j,int x,int y, int w, int h) {
		if(i>=x && i<x+w && j>=y && j<y+h)
			return true;
		else
			return false;
	}

	public void step(long now) {
		
	}

}
