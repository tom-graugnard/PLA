package game.shellda;

import java.awt.Color;
import java.awt.image.BufferedImage;


public class Element {
	
	Color c;

	BufferedImage m_sprite;
	BufferedImage[] m_sprites;
	Model m_model;
	int m_ncols, m_nrows;
	int m_idx;
	int m_x, m_y, m_w, m_h;
	float m_scale;
	int m_scaled_w,m_scaled_h;
	
	Noeud m_courant;
	
	String m_name;
	
	public Element(Noeud n, int x, int y) {
		m_courant = n;
		m_x = x;
		m_y = y;
	}

}
