package game.shellda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import interpreter.IAutomaton;
import interpreter.IBehaviour;
import interpreter.ICondition;
import interpreter.IDirection;
import interpreter.IState;
import interpreter.ITransition;

import interpreter.IAction.Move;
import interpreter.IAction.Pop;
import interpreter.IAction.Wizz;
import interpreter.IAction.Egg;

public class Virus extends Element {

	int display;
	boolean m_decouvert;
	Fichier m_proche;
	
	int type;
	boolean m_worked;

	public Virus(Noeud courant, Model model, int x, int y) {
		super(courant, model, x, y);
		display = 0;
		model.m_virus.add(this);
		m_auto = m_model.m_automateVirus.copy();
		Random rand = new Random();
		type = rand.nextInt(4);
	}

	public boolean isDiscovered() {
		if (m_model.m_courant == m_courant) {
			m_decouvert = true;
		}
		return m_decouvert;
	}

	public void step(long now) throws Exception {
		if (m_auto != null)
			m_auto.step(this);
	}

	public void Hit(IDirection direction) {
		int dis_ver = m_proche.m_y - m_y;
		int dis_hor = m_proche.m_x - m_x;

		if (Math.abs(dis_ver + dis_hor) == 1) {
			m_courant.m_carte[m_x + dis_hor][m_y+dis_ver] = null;
			m_courant.m_carte[m_x][m_y] = null;
			m_courant.m_carte[m_x + dis_hor][m_y+dis_ver] = this;
		}
	}

	public void Egg() {
	}

	void actualiser() {
		display++;
	}

	public void die(LinkedList<Virus> virus) {
		virus.remove(this);
	}
	
	public void move() {
		
	}
	
	
	

	public void paint(Graphics g) {
		if (m_courant == m_model.m_courant) {
			switch (type) {
			case 0:
				g.drawImage(m_model.m_virus1Sprite, m_x * 48 + 8, m_y * 48 + 8, 32, 32, null);
				break;
			case 1:
				g.drawImage(m_model.m_virus2Sprite, m_x * 48 + 8, m_y * 48 + 8, 32, 32, null);
				break;
			case 2:
				g.drawImage(m_model.m_virus3Sprite, m_x * 48 + 8, m_y * 48 + 8, 32, 32, null);
				break;
			case 3:
				g.drawImage(m_model.m_virus4Sprite, m_x * 48 + 8, m_y * 48 + 8, 32, 32, null);
				break;
			}
		}
	}

}
