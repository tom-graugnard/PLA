package game.shellda;

public class Dossier extends Element {

	Noeud m_enfant;

	public Dossier(Noeud enfant, Noeud courant, String name, int x, int y) {
		super(courant, x, y);
		m_enfant = enfant;
		m_name = name;
	}
}
