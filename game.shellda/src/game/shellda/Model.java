package game.shellda;

import java.util.LinkedList;

import edu.ricm3.game.GameModel;

public class Model extends GameModel {

	Tree m_tree;
	Noeud m_corbeille;
	LinkedList<Virus> m_virus;

	public Model() {
		m_virus = new LinkedList<Virus>();
		m_corbeille = new Noeud(null);
		m_tree = new Tree(m_corbeille, m_virus);
	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

}
