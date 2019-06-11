package game.shellda;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import interpreter.IDirection;

public class Virus extends Element {

	int display;
	boolean m_decouvert;
	Fichier m_proche;

	int m_type;
	boolean m_worked;

	public Virus(Noeud courant, Model model, int x, int y) {
		super(courant, model, x, y);
		display = 0;
		model.m_virus.add(this);
		m_auto = m_model.m_automateVirus.copy();
		Random rand = new Random();
		m_type = rand.nextInt(4);
	}

	public boolean isDiscovered() {
		if (m_model.m_courant == m_courant) {
			m_decouvert = true;
		}
		return m_decouvert;
	}

	public void Hit(IDirection direction) {
		int[] coordonnees;
		coordonnees = direction.coordonnees();
		int m_x_ = m_x + coordonnees[0];
		int m_y_ = m_y + coordonnees[1];
		if (m_x_ > Options.LARGEUR_CARTE - 1) {
			m_x_ = 0;
		}
		if (m_x_ < 0) {
			m_x_ = Options.LARGEUR_CARTE - 1;
		}
		if (m_y_ > Options.HAUTEUR_CARTE - 1) {
			m_y_ = 0;
		}
		if (m_y_ < 0) {
			m_y_ = Options.HAUTEUR_CARTE - 1;
		}
		Element e = m_model.m_courant.m_carte[m_x_][m_y_];
		if (e instanceof Fichier) {
			Fichier f = (Fichier) e;
			f.m_infection -= Options.DEGATS_VIRUS;
			if (f.m_infection <= 0) {
				f.goCorbeille();
				Move(direction);
			}
		}
		if (m_model.m_joueur.m_x == m_x_ && m_model.m_joueur.m_y == m_y_ && m_courant == m_model.m_courant) {
			if (e == null) {
				Move(direction);
				m_model.m_joueur.m_courant = m_model.m_tree.m_root;
				m_model.m_courant = m_model.m_tree.m_root;
				m_model.m_joueur.m_x = 0;
				m_model.m_joueur.m_y = 0;
			}
		}
	}

	public void Egg() {
		if (m_x - 1 >= 0 && m_courant.m_carte[m_x - 1][m_y] == null) {
			m_courant.m_carte[m_x - 1][m_y] = new Virus(m_courant, m_model, m_x - 1, m_y);
		} else {
			if (m_x + 1 < Options.LARGEUR_CARTE && m_courant.m_carte[m_x + 1][m_y] == null) {
				m_courant.m_carte[m_x + 1][m_y] = new Virus(m_courant, m_model, m_x + 1, m_y);
			} else {
				if (m_y - 1 >= 0 && m_courant.m_carte[m_x][m_y - 1] == null) {
					m_courant.m_carte[m_x][m_y - 1] = new Virus(m_courant, m_model, m_x, m_y - 1);
				} else {
					if (m_y + 1 < Options.HAUTEUR_CARTE && m_courant.m_carte[m_x][m_y + 1] == null) {
						m_courant.m_carte[m_x][m_y + 1] = new Virus(m_courant, m_model, m_x, m_y + 1);
					}
				}
			}
		}
	}

	public void Pop(IDirection direction) {
		int[] coordonnees;
		coordonnees = direction.coordonnees();
		int m_x_ = m_x + coordonnees[0], m_y_ = m_y + coordonnees[1];
		if (m_x_ > Options.LARGEUR_CARTE - 1) {
			m_x_ = 0;
		}
		if (m_x_ < 0) {
			m_x_ = Options.LARGEUR_CARTE;
		}
		if (m_y_ > Options.HAUTEUR_CARTE - 1) {
			m_y_ = 0;
		}
		if (m_y_ < 0) {
			m_y_ = Options.HAUTEUR_CARTE;
		}
		Element e = m_model.m_courant.m_carte[m_x_][m_y_];
		if (e instanceof Dossier && !(e instanceof Corbeille)) {
			Dossier d = (Dossier) e;
			if (d.m_contenu.m_carte[8][8] == null) {
				m_x = 8;
				m_y = 8;
				m_courant = d.m_contenu;
				// m_decouvert = false; // A VOIR
			}
		}
	}

	public void Wizz(IDirection direction) {
		m_type = (m_type + 1) % 4;
	}

	void actualiser() {
		display++;
	}

	public void die(LinkedList<Virus> virus) {
		virus.remove(this);
	}

	public void paint(Graphics g) {
		if (m_courant == m_model.m_courant) {
			/*
			switch (m_type) {
			case 0:
				g.drawImage(m_model.m_virus1Sprite, m_x*48 + 8, m_y *48 + 8, 32, 32, null);
				break;
			case 1:
				g.drawImage(m_model.m_virus2Sprite, m_x*48 + 8, m_y *48 + 8, 32, 32, null);
				break;
			case 2:
				g.drawImage(m_model.m_virus3Sprite, m_x*48 + 8, m_y *48 + 8, 32, 32, null);
				break;
			case 3:
				g.drawImage(m_model.m_virus4Sprite, m_x*48 + 8, m_y *48 + 8, 32, 32, null);
				break;
			}
			*/
			
			switch (m_type) {
			case 0:
				g.drawImage(m_model.m_virus1Sprite, m_x_visu + 8, m_y_visu + 18, 32, 32, null);
				break;
			case 1:
				g.drawImage(m_model.m_virus2Sprite, m_x_visu + 8, m_y_visu + 18, 32, 32, null);
				break;
			case 2:
				g.drawImage(m_model.m_virus3Sprite, m_x_visu + 8, m_y_visu + 18, 32, 32, null);
				break;
			case 3:
				g.drawImage(m_model.m_virus4Sprite, m_x_visu + 8, m_y_visu + 18, 32, 32, null);
				break;
			}
		}
	}

	public boolean equals(Virus v) {
		return this==v;
	}
	
}
