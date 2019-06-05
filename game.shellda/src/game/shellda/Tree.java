package game.shellda;


public class Tree {

	Noeud m_root;

	Model m_model;

	public Tree(Model m) {
		m_root = new Noeud(m);
		m_model = m;
		generate_tree();
	}

	private void generate_tree() {
		m_root.m_carte[0][0] = new Corbeille(m_root, m_model, 0, 0, m_model.m_corbeille);
		m_root.m_carte[6][6] = new Virus(m_root, m_model, 6, 6);
		m_root.m_carte[4][0] = new Fichier(m_root, m_model, 4, 0, "lol.txt");
		m_root.ajouter_enfant(m_root, 1, this);

		m_root.m_enfants.get(0).ajouter_enfant(m_root.m_enfants.get(0), 3, this);
	}

	public void paint() {

	}

}
