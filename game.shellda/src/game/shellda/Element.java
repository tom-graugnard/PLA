package game.shellda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import interpreter.IAutomaton;
import interpreter.IDirection;
import interpreter.IKey;
import interpreter.IKind;

public class Element {

	IAutomaton m_auto; // Automate du comportement de l'automate

	Model m_model; // Reférence vers le model global au jeu, permettant d'accéder au sprites

	int m_x, m_y; // Coordonnée locale dans un dossier
	
	int m_x_visu, m_y_visu; // Coordonnée de l'image de l'élément

	IDirection m_direction; // Direction relative vers où l'élément pointe

	IKind m_kind;

	Noeud m_courant; // Position dans l'arborescence

	public Element(Noeud courant, Model model, int x, int y) {
		m_model = model;
		m_courant = courant;
		m_x = x;
		m_x_visu = m_x*48;
		m_y = y;
		m_y_visu = m_y*48;
		m_direction = new IDirection("N");
		m_kind = new IKind("V");
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(m_x * 48 + 8, m_y * 48, 32, 32);
	}

	long m_anim = 0;
	
	public void update(long now) {
		if(now - m_anim > 2 && (m_x*48 != m_x_visu || m_y != m_y_visu)) {
			int speed_x = Math.abs(m_x*48 - m_x_visu)/48*2 + 1;
			int speed_y = Math.abs(m_y*48 - m_y_visu)/48*2 + 1;
			if(Math.abs(m_x*48 - m_x_visu) > 48*6) {
				m_x_visu = m_x*48;
			}
			if(Math.abs(m_y*48 - m_y_visu) > 48*6) {
				m_y_visu = m_y*48;
			}
			if(m_x*48 > m_x_visu) {
				m_x_visu += speed_x;
				if(m_x*48 < m_x_visu)
					m_x_visu = m_x;
			}
			else if(m_x*48 < m_x_visu){
				m_x_visu -= speed_x;
				if(m_x*48 > m_x_visu)
					m_x_visu = m_x;
			}
			if(m_y*48 > m_y_visu) {
				m_y_visu += speed_y;
				if(m_y*48 < m_y_visu)
					m_y_visu = m_y;
			}
			else if(m_y*48 < m_y_visu){
				m_y_visu -= speed_y;
				if(m_y*48 > m_y_visu)
					m_y_visu = m_y;
			}
			m_anim = now;
		}
	}
	
	public void step(long now) throws Exception {
		if (m_auto != null)
			m_auto.step(this);
		update(now);
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

	// Action possible de l'automates
	public void Wait() {
	}

	public void Wizz(IDirection direction) {
	}

	public void Pop(IDirection direction) {
	}

	public void Move(IDirection direction) {
		int[] coordonnees;
		if (direction.absolue()) {
			coordonnees = direction.coordonnees();
			m_direction = direction;
		} else {
			if (direction.front()) {
				coordonnees = m_direction.coordonnees();
			} else if (direction.back()) {
				m_direction = m_direction.absolue_back();
				coordonnees = m_direction.coordonnees();
			} else if (direction.right()) {
				m_direction = m_direction.absolue_right();
				coordonnees = m_direction.coordonnees();
			} else /* direction.left() */ {
				m_direction = m_direction.absolue_left();
				coordonnees = m_direction.coordonnees();
			}
		}
		m_courant.m_carte[m_x][m_y] = null;
		m_x += coordonnees[0];
		m_y += coordonnees[1];
		while (m_x < 0) {
			m_x += Options.LARGEUR_CARTE;
		}
		m_x %= Options.LARGEUR_CARTE;
		while (m_y < 0) {
			m_y += Options.HAUTEUR_CARTE;
		}
		m_y %= Options.HAUTEUR_CARTE;
		m_courant.m_carte[m_x][m_y] = this;
	}

	public void Jump(IDirection direction) {
	}

	public void Turn(IDirection direction) {
	}

	public void Hit(IDirection direction) {
	}

	public void Protect(IDirection direction) {
	}

	public void Pick(IDirection direction) {
	}

	public void Throw(IDirection direction) {
	}

	public void Store() {
	}

	public void Get() {
	}

	public void Power() {
	}

	public void Kamikaze() {
	}

	public void Egg() {
	}

	// Condition possible de l'automate
	public boolean Key(IKey key) {
		return m_model.removeKey(key.m_key);
	}

	public boolean Mydir(IDirection direction) {
		return direction.m_direction == m_direction.m_direction;
	}

	public boolean Cell(IDirection direction, IKind kind, int distance) {
		if (distance == 0) {
			return m_courant.m_carte[m_x][m_y] != null && m_courant.m_carte[m_x][m_y].m_kind.equals(kind);
		} else {
			int[] coordonnees;
			if (direction.absolue()) {
				coordonnees = direction.coordonnees();
			} else /* direction.relative() */ {
				if (direction.front()) {
					coordonnees = m_direction.absolue_front().coordonnees();
				} else if (direction.back()) {
					coordonnees = m_direction.absolue_back().coordonnees();
				} else if (direction.right()) {
					coordonnees = m_direction.absolue_right().coordonnees();
				} else /* direction.left() */ {
					coordonnees = m_direction.absolue_left().coordonnees();
				}
			}
			coordonnees[0] *= distance;
			coordonnees[1] *= distance;
			Element element = m_courant.get_element(m_x + coordonnees[0], m_y + coordonnees[1]);
			return element != null && element.m_kind.equals(kind);
		}
	}

	public boolean Closest(IKind kind, IDirection direction) {
		int[] coordonnees;
		if (direction.absolue()) {
			coordonnees = direction.coordonnees();
		} else /* direction.relative() */ {
			if (direction.front()) {
				coordonnees = direction.absolue_front().coordonnees();
			} else if (direction.back()) {
				coordonnees = direction.absolue_back().coordonnees();
			} else if (direction.right()) {
				coordonnees = direction.absolue_right().coordonnees();
			} else /* direction.left() */ {
				coordonnees = direction.absolue_left().coordonnees();
			}
		}
		int[] coordonnees_incr = new int[2];
		coordonnees_incr[0] = coordonnees[0];
		coordonnees_incr[1] = coordonnees[1];

		Element element = m_courant.get_element(m_x + coordonnees[0], m_y + coordonnees[1]);
		while (element == null) {
			coordonnees[0] += coordonnees_incr[0];
			coordonnees[1] += coordonnees_incr[1];
			element = m_courant.get_element(m_x + coordonnees[0], m_y + coordonnees[1]);
			// Si on a déjà parcourue toute la ligne/colonne alors on renvoie faux
			if (coordonnees[0] > Options.LARGEUR_CARTE - 1 || coordonnees[1] > Options.HAUTEUR_CARTE - 1 || coordonnees[0] < 0 || coordonnees[1] < 0)
				return false;
		}
		return element.m_kind.equals(kind);
	}

	public boolean GotPower() {
		return true;
	}

	public boolean GotStuff() {
		return true;
	}
}
