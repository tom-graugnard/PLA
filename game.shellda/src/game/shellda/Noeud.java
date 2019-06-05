package game.shellda;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Noeud {

	Element m_carte[][];
	
	Noeud m_parent;
	
	LinkedList<Noeud> m_enfants;

	Model m_model;
	
	String m_name;

	public Noeud(Model m) {
		m_enfants = new LinkedList<Noeud>();
		m_model = m;
		initialiser_carte(m);
	}

	public Noeud(Model m, Noeud parent) {
		m_enfants = new LinkedList<Noeud>();
		m_model = m;
		m_parent = parent;
		initialiser_carte(m);
	}

	public Noeud parent() {
		return m_parent;
	}

	private void initialiser_carte(Model model) {
		m_carte = new Element[Options.HAUTEUR_CARTE][Options.LARGEUR_CARTE];
		for (int i = 0; i < Options.HAUTEUR_CARTE; i++) {
			for (int j = 0; j < Options.LARGEUR_CARTE; j++) {
				m_carte[i][j] = null;
			}
		}
		
		m_carte[1][0] = new Dossier(this, model, 0, 1,"Retour", m_parent);
	}

	public void ajouter_enfant(Noeud parent, int nb, Tree root) {
		for (int i = 0; i < nb; i++) {
			Noeud n = new Noeud(m_model, parent);
			m_enfants.add(n);
			n.m_carte[0][Options.LARGEUR_CARTE - 1] = new Virus(this, m_model, 0,
					Options.LARGEUR_CARTE - 1);

			m_carte[Options.HAUTEUR_CARTE - 1][i] = new Dossier(this, m_model, Options.HAUTEUR_CARTE - 1, i,"" + i, n);
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
		int case_width = 48;//Options.WIDTH / Options.LARGEUR_CARTE;
		int case_height = 48;//Options.HEIGHT / Options.HAUTEUR_CARTE;
		for (int i = 0; i < Options.HAUTEUR_CARTE; i++) {
			for (int j = 0; j < Options.LARGEUR_CARTE; j++) {
				if (m_carte[i][j] == null) {
					g.setColor(Color.cyan);
				} else {
					g.setColor(m_carte[i][j].c);
				}
				g.fillRect(j * (case_width + 4), i * (case_height + 4), case_width, case_height );
			}
		}

	}
	
	public Element[][] carte() {
		return m_carte;
	}
}
