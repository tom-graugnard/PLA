package game.shellda;

public class Fichier extends Element{

	String m_name;
	
	int m_infection;
	
	public Fichier(Noeud courant, Model model, int x, int y, String name) {
		super(courant, model, x, y);
		m_name = name;
	}

}