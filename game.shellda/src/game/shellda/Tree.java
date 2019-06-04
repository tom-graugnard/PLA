package game.shellda;

import java.util.LinkedList;

public class Tree {

	Noeud m_root;

	Noeud corbeille;

	LinkedList<Virus> vir;

	LinkedList<Noeud> tous;

	public Tree(Noeud corb, LinkedList<Virus> virus) {
		corbeille = corb;
		m_root = new Noeud(corbeille);

		m_root.m_carte[1][1] = new Clink(m_root, 1, 1);
		tous = new LinkedList<Noeud>();
		vir = virus;
		tous.add(m_root);
		generate_tree(m_root);
	}

	private void generate_tree(Noeud root) {

		root.m_carte[0][Options.LARGEUR_CARTE - 1] = new Virus(root, 0, Options.LARGEUR_CARTE, vir);
		vir.add((Virus) root.m_carte[0][Options.LARGEUR_CARTE - 1]);
		m_root.ajouter_enfant(root, 1, this);
		tous.add(m_root.m_enfants[0]);

		m_root.m_enfants[0].ajouter_enfant(m_root.m_enfants[0], 3, this);
		for (int i = 1; i < 4; i++) {
			tous.add(m_root.m_enfants[0].m_enfants[i - 1]);
		}

	}

	public void print() {

	}
}
