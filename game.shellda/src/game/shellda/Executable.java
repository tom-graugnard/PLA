package game.shellda;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Random;

import game.shellda.Fichier.FichCorb;
import game.shellda.Fichier.FichNorm;

public abstract class Executable extends Fichier {

	Executable m_type;

	public Executable(Noeud courant, Model model, int x, int y, String name) {
		super(courant, model, x, y, name);
	}

	public abstract void interaction();

	public void paint(Graphics g) {
		g.drawImage(m_model.m_executableSprite, m_x * 48 + 8, m_y * 48 + 10, 32, 32, null);

		g.setFont(m_model.m_font);
		FontMetrics f = g.getFontMetrics();
		g.drawString(m_name, m_x * 48 + (48 - f.stringWidth(m_name)) / 2, m_y * 48 + 42 + (16 / 2));
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

		m_model.m_corbeille.m_carte[r_x][r_y] = new ExecutableCorbeille(m_model.m_corbeille, m_model, r_x, r_y,
				corruptName, m_x, m_y, m_courant, m_name, this);
		m_model.m_nb_fichier_corbeille++;
	}

	public void retour() {
		Element e = m_courant_ancien.m_carte[m_x_ancien][m_y_ancien];
		if (e instanceof Virus) {
			Virus v = (Virus) e;
			v.die(m_model.m_virus);
			m_courant_ancien.m_carte[m_x_ancien][m_y_ancien] = null;
		}
		if (m_type instanceof Decompresseur) {
			m_courant_ancien.m_carte[m_x_ancien][m_y_ancien] = new Decompresseur(m_courant_ancien, m_model, m_x_ancien,
					m_y_ancien);
		} else {
			if (m_type instanceof AntiVirus) {
				m_courant_ancien.m_carte[m_x_ancien][m_y_ancien] = new AntiVirus(m_courant_ancien, m_model, m_x_ancien,
						m_y_ancien);
			} else {
				if (m_type instanceof Shellda) {
					m_courant_ancien.m_carte[m_x_ancien][m_y_ancien] = new Shellda(m_courant_ancien, m_model,
							m_x_ancien, m_y_ancien);
				}
			}
		}
		m_model.m_nb_fichier_corbeille--;
	}

	public static class ExecutableCorbeille extends Executable {

		public ExecutableCorbeille(Noeud courant, Model model, int x, int y, String name, int old_x, int old_y,
				Noeud old_noeud, String old_name, Executable type) {
			super(courant, model, x, y, name);
			m_x_ancien = old_x;
			m_y_ancien = old_y;
			m_courant_ancien = old_noeud;
			m_name_ancien = old_name;
			m_type = type;
			m_auto = m_model.m_automateFichier.copy();
		}

		public void paint(Graphics g) {
			g.drawImage(m_model.m_executableCorbeilleSprite, m_x * 48 + 8, m_y * 48 + 10, 32, 32, null);

			g.setFont(m_model.m_font);
			FontMetrics f = g.getFontMetrics();
			g.drawString(m_name, m_x * 48 + (48 - f.stringWidth(m_name)) / 2, m_y * 48 + 42 + (16 / 2));
		}

		@Override
		public void interaction() {
		}

	}
}
