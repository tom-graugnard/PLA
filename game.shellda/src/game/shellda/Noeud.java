package game.shellda;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Noeud {

	Element m_carte[][]; // Terrain de jeu du joueur

	Noeud m_parent; // Parent du noeud (si null alors ce noeud est la racine)

	LinkedList<Noeud> m_enfants; // Liste de toutes les branches de ce noeud (équivalent à tous les sous-dossiers
									// de ce dossier)

	Model m_model;

	String m_name; // Nom du noeud/dossier

	public Noeud(Model m, String name) {
		m_name = name;
		m_model = m;
		m_enfants = new LinkedList<Noeud>();
		m_carte = new Element[Options.LARGEUR_CARTE][Options.HAUTEUR_CARTE];
		m_carte[0][0] = new Corbeille(this, m_model, 0, 0, m_model.m_corbeille);
	}

	public Noeud(Model m, Noeud parent, String name) {
		m_name = name;
		m_model = m;
		m_parent = parent;
		m_carte = new Element[Options.LARGEUR_CARTE][Options.HAUTEUR_CARTE];
		m_carte[0][0] = new Dossier(this, m_model, 0, 0, "..", m_parent);
		m_enfants = new LinkedList<Noeud>();

	}

	public boolean ajouter_element(Element e) {
		// Si il y a déjà un élement à cette emplacement alors l'element ne peut être
		// placé
		if (m_carte[e.m_x][e.m_y] != null || (e.m_x == 3 && e.m_y == 3)) {
			return false;
		}
		// Sinon l'élément peut être placé
		else {
			m_carte[e.m_x][e.m_y] = e;
			return true;
		}
	}

	public Element get_element(int x, int y) {
		while (x < 0) {
			x += Options.LARGEUR_CARTE;
		}
		while (x > Options.LARGEUR_CARTE - 1) {
			x -= Options.LARGEUR_CARTE;
		}
		while (y < 0) {
			y += Options.HAUTEUR_CARTE;
		}
		while (y > Options.HAUTEUR_CARTE - 1) {
			y -= Options.HAUTEUR_CARTE;
		}
		if (this == m_model.m_joueur.m_courant && x == m_model.m_joueur.m_x && y == m_model.m_joueur.m_y)
			return m_model.m_joueur;
		else
			return m_carte[x][y];
	}

	public void generer_noeud(int profondeur) {
		int i = 0, j;
		int x, y;
		Random rand = new Random();
		if (profondeur == 0) {
			ajouter_element(new Executable(this, m_model, Options.LARGEUR_CARTE * 3 / 4, Options.HAUTEUR_CARTE * 3 / 4,
					"Shellda"));
		}
		if (profondeur > 0) {
			int nombre_dossier = rand.nextInt(profondeur) * 2 + 1;
			Noeud tmp;
			for (i = 1; i < nombre_dossier + 1; i++) {
				tmp = new Noeud(m_model, this, "" + (char) (profondeur + 'A') + "_" + i);
				x = i / Options.HAUTEUR_CARTE;
				y = i % Options.HAUTEUR_CARTE;
				ajouter_element(new Dossier(this, m_model, x, y, tmp.m_name, tmp));
				m_enfants.add(tmp);
			}
			for (i = 0; i < m_enfants.size(); i++) {
				m_enfants.get(i).generer_noeud(profondeur - 1);
			}
		}
		// On ajoute des fichiers dans notre dossier
		for (j = i; j < (profondeur + 1) * 2 + i; j++) {
			x = j / Options.HAUTEUR_CARTE;
			y = j % Options.HAUTEUR_CARTE;
			ajouter_element(new Fichier(this, m_model, x, y, "F" + (char) (profondeur + 'A') + "_" + j));
		}
		// On genere au maximum un nombre de virus égal à la profondeur dans
		// l'arborescence (voir moins)
		// Cela permet de rendre le jeu de plus en plus difficile
		for (i = 0; i < (Options.PROFONDEUR_ARBORESCENCE - profondeur) * 2 + 8; i++) {
			x = (int) rand.nextInt(Options.LARGEUR_CARTE);
			y = (int) rand.nextInt(Options.HAUTEUR_CARTE);
			ajouter_element(new Virus(this, m_model, x, y));
		}
		// Generation d'archive
		for (i = 0; i < 16; i++) {
			x = (int) rand.nextInt(Options.LARGEUR_CARTE);
			y = (int) rand.nextInt(Options.HAUTEUR_CARTE);
			ajouter_element(new Archive(this, m_model, x, y, "F" + (char) (profondeur + 'A') + "_" + i + ".zip"));
		}
	}

	public void print() {
		for (int i = 0; i < Options.HAUTEUR_CARTE; i++) {
			for (int j = 0; j < Options.LARGEUR_CARTE; j++) {
				System.out.print("|");

				if (m_carte[i][j] == null) {
					System.out.print(" ");
				} else {
					if (m_carte[i][j] instanceof Corbeille) {
						System.out.print("C");
					} else if (m_carte[i][j] instanceof Dossier) {
						System.out.print("D");
					}
					if (m_carte[i][j] instanceof Clink) {
						System.out.print("J");
					}
					if (m_carte[i][j] instanceof Virus) {
						Virus v = (Virus) m_carte[i][j];
						System.out.print(v.display);
					}

				}

			}
			System.out.println("|");
		}

	}

	public void paint(Graphics g) {
		for (int i = 0; i < Options.LARGEUR_CARTE; i++) {
			for (int j = 0; j < Options.HAUTEUR_CARTE; j++) {
				g.drawImage(m_model.m_backgroundSprite, i * 48, j * 48, 48, 48, null);
				if (m_carte[i][j] != null) {
					if (m_model.m_joueur.m_x == i && m_model.m_joueur.m_y == j) {
						g.drawImage(m_model.m_backgroundSelectedSprite, i * 48, j * 48, 48, 48, null);
					}
					m_carte[i][j].paint(g);
				}
			}
		}
		m_model.m_joueur.paint(g);
	}

	public Element[][] carte() {
		return m_carte;
	}
}
