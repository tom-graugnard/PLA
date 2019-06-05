package game.shellda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import interpreter.Direction;
import interpreter.IAutomaton;

public class Element {

	Color c;
	IAutomaton auto;
	BufferedImage m_sprite;
	BufferedImage[] m_sprites;
	Model m_model;
	int m_ncols, m_nrows;
	int m_idx;
	int m_x, m_y, m_w, m_h;
	float m_scale;
	int m_scaled_w, m_scaled_h;
	int nbpas;
	Noeud m_courant;

	String m_name;

	public Element(Noeud n, Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale) {
		m_model = model;
		m_sprite = sprite;
		m_ncols = columns;
		m_nrows = rows;
		m_idx = no;
		m_x = x;
		m_y = y;
		m_scale = scale;
		//splitSprite();
		m_scaled_w = (int) (m_scale * m_w);
		m_scaled_h = (int) (m_scale * m_h);
	}

	private void splitSprite() {
		int width = m_sprite.getWidth(null);
		int height = m_sprite.getHeight(null);
		m_sprites = new BufferedImage[m_nrows * m_ncols];
		m_w = width / m_ncols;
		m_h = height / m_nrows;
		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
			}
		}
	}

	public void paint(Graphics g) {

		Image img = m_sprites[m_idx];
		g.drawImage(img, m_x, m_y, m_scaled_w, m_scaled_h, null);

	}

	public void step(long now) throws Exception {
		//if(auto!=null)
		//auto.step(this);
	}
	public Noeud noeud() {
		return m_courant;
	}
	public int getx() {
		return m_x;
	}
	public int gety() {
		return m_y;
	}
	public void wizz() {
		// TODO Auto-generated method stub
		
	}

	public void pop() {
		// TODO Auto-generated method stub
		
	}

	public void hit(Direction direction) {
		// TODO Auto-generated method stub
		
	}

	public void egg() {
		// TODO Auto-generated method stub
		
	}

	public void move(Direction direction) {
		// TODO Auto-generated method stub
		
	}

	public boolean canmove(Direction direction) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean cinqpas(int nbpas2) {
		// TODO Auto-generated method stub
		return false;
	}

}
