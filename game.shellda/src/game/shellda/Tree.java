package game.shellda;

import java.util.LinkedList;

public class Tree {

	Noeud m_root;

	Noeud corbeille;
	Model m_model;

	LinkedList<Virus> vir;

	LinkedList<Noeud> tous;

	public Tree(Model m, Noeud corb, LinkedList<Virus> virus) {
		corbeille = corb;
		m_root = new Noeud(m);
		m_model = m;
		tous = new LinkedList<Noeud>();
		vir = virus;
		tous.add(m_root);
		generate_tree(m_root);
	}

	private void generate_tree(Noeud root) {

		root.m_carte[0][Options.LARGEUR_CARTE - 1] = new Virus(root, m_model, 1, m_model.m_virusSprite, 2, 4, 0,
				Options.LARGEUR_CARTE - 1, 1, vir);
		vir.add((Virus) root.m_carte[0][Options.LARGEUR_CARTE - 1]);
		m_root.ajouter_enfant(root, 1, this);
		tous.add(m_root.m_enfants[0]);

		m_root.m_enfants[0].ajouter_enfant(m_root.m_enfants[0], 3, this);
		for (int i = 1; i < 4; i++) {
			tous.add(m_root.m_enfants[0].m_enfants[i - 1]);
		}

	}

	public void print() {
		for (int i = 0; i < tous.size(); i++) {
			tous.get(i).print();
			System.out.println();
		}
	}

	public void paint() {

	}

}
