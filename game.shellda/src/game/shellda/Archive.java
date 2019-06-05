package game.shellda;

import java.util.LinkedList;

public class Archive extends Element{

	String m_name;
	
	LinkedList<Element> m_contenu;
 	
	public Archive(Noeud courant, Model model, int x, int y, String name) {
		super(courant, model, x, y);
		m_name = name;
	}

}
