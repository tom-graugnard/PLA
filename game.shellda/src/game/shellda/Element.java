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

	IDirection m_direction; // Direction relative vers où l'élément pointe

	IKind m_kind;

	Noeud m_courant; // Position dans l'arborescence

	public Element(Noeud courant, Model model, int x, int y) {
		m_model = model;
		m_courant = courant;
		m_x = x;
		m_y = y;
		m_direction = new IDirection("N");
		m_kind = new IKind("_");
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(m_x * 48 + 8, m_y * 48, 32, 32);
	}

	public void step(long now) throws Exception {
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
		if(direction.absolue()) {
			int[] coordonnees = direction.coordonnees();
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
		return m_model.m_keys.remove(key);
	}
	
	public boolean Mydir(IDirection direction) {
		return direction.m_direction == m_direction.m_direction;
	}

	public boolean Cell(IDirection direction, IKind kind, int distance) {
		int[] coordonnees;
		if (direction.absolue()) {
			coordonnees = direction.coordonnees();
		} else /* direction.relative() */ {
			if(direction.front()) {
				coordonnees = direction.absolue_front().coordonnees();
			} else if(direction.back()) {
				coordonnees = direction.absolue_back().coordonnees();
			} else if(direction.right()) {
				coordonnees = direction.absolue_right().coordonnees();
			} else /*direction.left()*/ {
				coordonnees = direction.absolue_left().coordonnees();
			}
		}
		coordonnees[0] *= distance;
		coordonnees[1] *= distance;
		Element element = m_courant.get_element(m_x + coordonnees[0], m_y + coordonnees[1]);
		return element != null && element.m_kind.equals(kind);
	}
	
	public boolean Closest(IKind kind, IDirection direction) {
		int[] coordonnees;
		if (direction.absolue()) {
			coordonnees = direction.coordonnees();
		} else /* direction.relative() */ {
			if(direction.front()) {
				coordonnees = direction.absolue_front().coordonnees();
			} else if(direction.back()) {
				coordonnees = direction.absolue_back().coordonnees();
			} else if(direction.right()) {
				coordonnees = direction.absolue_right().coordonnees();
			} else /*direction.left()*/ {
				coordonnees = direction.absolue_left().coordonnees();
			}
		}
		int[] coordonnees_incr = new int[2];
		coordonnees_incr[0] = coordonnees[0];
		coordonnees_incr[1] = coordonnees[1];
		Element element = m_courant.get_element(m_x + coordonnees[0], m_y + coordonnees[1]);
		while(element == null) {
			coordonnees[0] += coordonnees_incr[0];
			coordonnees[1] += coordonnees_incr[1];
			element = m_courant.get_element(m_x + coordonnees[0], m_y + coordonnees[1]);
			//Si on a déjà parcourue toute la ligne/colonne alors on renvoie faux
			if(coordonnees[0] > Options.LARGEUR_CARTE - 1 || coordonnees[1] > Options.HAUTEUR_CARTE - 1)
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
