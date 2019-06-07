package game.shellda;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import interpreter.IAutomaton;
import interpreter.IBehaviour;
import interpreter.ICondition;
import interpreter.IDirection;
import interpreter.IState;
import interpreter.ITransition;
import interpreter.IAction.Hit;
import interpreter.IAction.Move;
import interpreter.IAction.Pop;
import interpreter.IAction.Wizz;

public class Clink extends Element {
	boolean isHitting = false;
	boolean isWizzing = false;
	boolean isPopping = false;
	
	Element inventaire = null;
	
	public Clink(Noeud courant, Model model, int x, int y) {
		super(courant, model, x, y);
		//m_auto = m_model.m_automateJoueur.copy();
	}

	public void step(long now) throws Exception {
		if (m_auto != null)
			m_auto.step(this);
	}

	public void Move(IDirection direction) {
		int[] coordonnees = direction.coordonnees();
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
	}
	public static class ClinkNorm extends Clink {
	public ClinkNorm(Noeud courant, Model model, int x, int y) {
			super(courant, model, x, y);
			// TODO Auto-generated constructor stub
			m_auto = m_model.m_automateJoueur1.copy();
		}

	public void Hit(IDirection direction) {
		Element e = m_model.m_courant.m_carte[m_x][m_y];
		if (e instanceof Dossier) {
			Dossier d = (Dossier) e;
			m_model.corb_parent=m_model.m_courant;
			m_x = 0;
			m_y = 0;
			m_model.m_courant = d.m_contenu;
			m_courant = d.m_contenu;
			if(d instanceof Corbeille)
				m_model.m_joueur=new ClinkCorb(m_courant, m_model, 0, 4);
		}
		else if (e instanceof Executable) {
			((Executable) e).interaction();
		}
		isHitting = false;
	}
	
	public void Wizz(IDirection direction) {
		if(inventaire == null) {
			inventaire = m_courant.m_carte[m_x][m_y];
			m_courant.m_carte[m_x][m_y] = null;
		}
		else {
			inventaire.m_courant = m_courant;
			inventaire.m_x = m_x;
			inventaire.m_y = m_y;
			m_courant.m_carte[m_x][m_y] = inventaire;
			inventaire = null;
		}
		isWizzing = false;
	}
	
	}
	
	public static class ClinkCorb extends Clink {

		public ClinkCorb(Noeud courant, Model model, int x, int y) {
			super(courant, model, x, y);
			// TODO Auto-generated constructor stub
			m_auto = m_model.m_automateJoueur2.copy();
		}
		public void Hit(IDirection direction) {
			m_model.m_courant=m_model.corb_parent;
			m_courant=m_model.corb_parent;
			m_model.m_joueur=new ClinkNorm(m_courant, m_model, 0, 0);
		}
		
	}

	public void paint(Graphics g) {
		g.drawImage(m_model.m_clinkSprite, m_x * 48 + 8, m_y * 48 + 8, 32, 32, null);
	}

}
