package game.shellda;

public class Noeud {

	Element m_carte[][];
	Noeud m_parent;
	Noeud m_enfants[];
	int m_nb_enfant;

	public Noeud(Noeud corbeille) {
		initialiser_carte(corbeille);
	}

	public Noeud(Noeud parent, Noeud corbeille) {
		m_parent = parent;
		initialiser_carte(corbeille);
	}

	public Noeud parent() {
		return m_parent;
	}

	public int nb_enfant() {
		return m_nb_enfant;
	}

	private void initialiser_carte(Noeud corbeille) {
		m_carte = new Element[Options.HAUTEUR_CARTE][Options.LARGEUR_CARTE];
		for (int i = 0; i < Options.HAUTEUR_CARTE; i++) {
			for (int j = 0; j < Options.LARGEUR_CARTE; j++) {
				m_carte[i][j] = null;
			}
		}
		m_carte[0][0] = new Corbeille(corbeille, this, 0, 0);
		m_carte[1][0] = new Dossier(m_parent, this, "Retour", 0, 1);
	}

	public void ajouter_enfant(Noeud parent, int nb, Tree root) {
		m_nb_enfant = nb;
		m_enfants = new Noeud[nb];
		for (int i = 0; i < nb; i++) {
			Noeud n = new Noeud(parent);
			m_enfants[i] = n;
			n.m_carte[0][Options.LARGEUR_CARTE - 1] = new Virus(this, 0, Options.LARGEUR_CARTE - 1, root.vir);
			root.vir.add((Virus) n.m_carte[0][Options.LARGEUR_CARTE - 1]);

			m_carte[Options.HAUTEUR_CARTE - 1][i] = new Dossier(n, this, "" + i, Options.HAUTEUR_CARTE - 1, i);
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
}
