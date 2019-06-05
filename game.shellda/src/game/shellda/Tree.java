package game.shellda;

public class Tree {

	Noeud m_root;
	
	Model m_model;

	public Tree(Model m) {
		m_root = new Noeud(m, "root");
		m_model = m;
		m_root.generer_noeud(Options.PROFONDEUR_ARBORESCENCE);

	}

	public void paint() {

	}

}
