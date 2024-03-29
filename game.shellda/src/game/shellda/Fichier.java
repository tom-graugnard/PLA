package game.shellda;

import java.awt.FontMetrics;
import java.awt.Graphics;

import interpreter.IDirection;
import interpreter.IKind;
import java.util.Random;

public class Fichier extends Element {

	String m_name;

	int m_infection;

	int m_x_ancien, m_y_ancien;
	Noeud m_courant_ancien;
	String m_name_ancien;
	Element m_type;

	public Fichier(Noeud courant, Model model, int x, int y, String name) {
		super(courant, model, x, y);
		m_kind = new IKind("P");
		m_name = name;
		m_infection = 100;

	}

	public void paint(Graphics g) {
		int i;
		if (m_infection > 75) {
			i = 0;
		} else if (m_infection > 50) {
			i = 1;
		} else if (m_infection > 25) {
			i = 2;
		} else {
			i = 3;
		}
		g.drawImage(m_model.m_fichierSprite[i], m_x * 48 + 8, m_y * 48 + 10, 32, 32, null);

		g.setFont(m_model.m_font);
		FontMetrics f = g.getFontMetrics();
		g.drawString(m_name, m_x * 48 + (48 - f.stringWidth(m_name)) / 2, m_y * 48 + 42 + (16 / 2));
	}

	public void Wizz(IDirection direction) {
		m_courant.m_carte[m_x][m_y] = new Archive(m_courant, m_model, m_x, m_y, m_name);
	}

	public void Pop(IDirection direction) {
		m_name = m_model.m_generator.generate_file();
	}

	public void goCorbeille() {
		Random r = new Random();
		int r_x = r.nextInt(Options.LARGEUR_CARTE - Options.DECALAGE_CORBEILLE);
		int r_y = r.nextInt(Options.HAUTEUR_CARTE);
		r_x += Options.DECALAGE_CORBEILLE;
		while (m_model.m_corbeille.m_carte[r_x][r_y] != null) {
			r_x = r.nextInt(13);
			r_y = r.nextInt(9);
			r_x += 3;
		}
		Random rand = new Random();
		int nameLength = rand.nextInt(6) + 3;
		String corruptName = "";
		for (int i = 0; i < nameLength; i++) {
			switch (rand.nextInt(10)) {
			case 0:
			case 1:
			case 2:
				corruptName += "%";
				break;
			case 3:
			case 4:
			case 5:
				corruptName += "&";
				break;
			case 6:
				corruptName += "!";
				break;

			case 7:
				corruptName += "$";
				break;
			case 8:
			case 9:
				corruptName += "?";
			}
		}

		m_model.m_corbeille.m_carte[r_x][r_y] = new FichCorb(m_model.m_corbeille, m_model, r_x, r_y, corruptName, m_x,
				m_y, m_courant, m_name, this);
		m_model.m_nb_fichier_corbeille++;
	}

	public void retour() {
		Element e = m_courant_ancien.m_carte[m_x_ancien][m_y_ancien];
		if (e instanceof Virus) {
			Virus v = (Virus) e;
			v.die(m_model.m_virus);
			m_courant_ancien.m_carte[m_x_ancien][m_y_ancien] = null;
		}

		if (m_courant_ancien != null && m_courant_ancien != m_model.m_corbeille)
			m_courant_ancien.m_carte[m_x_ancien][m_y_ancien] = new FichNorm(m_courant_ancien, m_model, m_x_ancien,
					m_y_ancien, m_name_ancien);

		m_infection = 0;
		m_model.m_nb_fichier_corbeille--;
	}

	public static class FichNorm extends Fichier {
		int auto1;

		public FichNorm(Noeud courant, Model model, int x, int y, String name) {
			super(courant, model, x, y, name);
			m_auto = m_model.m_automate[m_model.m_autoChoix[5]].copy();
			auto1 = m_model.m_autoChoix[5];
		}

		public void step(long now) throws Exception {
			if (auto1 != m_model.m_autoChoix[5]) {
				m_auto = m_model.m_automate[m_model.m_autoChoix[5]].copy();
				auto1 = m_model.m_autoChoix[5];
			}
			if (m_auto != null)
				m_auto.step(this);
			update(now);
		}

	}

	public static class FichCorb extends Fichier {

		int auto2;

		public FichCorb(Noeud courant, Model model, int x, int y, String name, int old_x, int old_y, Noeud old_noeud,
				String old_name, Element type) {
			super(courant, model, x, y, name);
			m_x_ancien = old_x;
			m_y_ancien = old_y;
			m_courant_ancien = old_noeud;
			m_name_ancien = old_name;
			m_type = type;
			m_auto = m_model.m_automate[m_model.m_autoChoix[3]].copy();
			auto2 = m_model.m_autoChoix[3];
		}
		
		public void paint(Graphics g) {
			g.drawImage(m_model.m_fichierCorrompuSprite, m_x * 48 + 8, m_y * 48 + 10, 32, 32, null);

			g.setFont(m_model.m_font);
			FontMetrics f = g.getFontMetrics();
			g.drawString(m_name, m_x * 48 + (48 - f.stringWidth(m_name)) / 2, m_y * 48 + 42 + (16 / 2));
		}

		public void step(long now) throws Exception {
			if (auto2 != m_model.m_autoChoix[3]) {
				m_auto = m_model.m_automate[m_model.m_autoChoix[3]].copy();
				auto2 = m_model.m_autoChoix[3];
			}
			if (m_auto != null)
				m_auto.step(this);
			update(now);
		}
	}

}
