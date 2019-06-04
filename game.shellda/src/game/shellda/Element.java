package game.shellda;

public class Element {
	
	int m_x;
	int m_y;
	
	Noeud m_courant;
	
	String m_name;
	
	public Element(Noeud n, int x, int y) {
		m_courant = n;
		m_x = x;
		m_y = y;
	}

}
