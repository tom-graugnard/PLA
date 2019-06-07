package game.shellda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import interpreter.IAutomaton;
import interpreter.IDirection;

public class Element {

	Color c; // Temporaire tant qu'on utilise pas les sprites

	IAutomaton m_auto; // Automate du comportement de l'automate

	Model m_model; // Reférence vers le model global au jeu, permettant d'accéder au sprites

	int m_x, m_y; // Coordonnée locale dans un dossier

	Noeud m_courant; // Position dans l'arborescence

	public Element(Noeud courant, Model model, int x, int y) {
		m_model = model;
		m_courant = courant;
		m_x = x;
		m_y = y;
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
	public void wizz(IDirection direction) {
	}
	
	public void pop(IDirection direction) {
		
	}

	public void hit(IDirection direction) {
	}

	public void egg() {
	}

	public void move(IDirection direction) {
	}

	// Condition possible de l'automates
	public boolean canmove(IDirection direction) {
		return false;
	}

	public boolean canhit(IDirection direction) {
		return false;
	}
	
	public boolean canwizz() {
		return false;
	}

}
