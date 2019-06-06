package game.shellda;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import interpreter.Direction;
import interpreter.IAutomaton;
import interpreter.IBehaviour;
import interpreter.ICondition;
import interpreter.ICondition.CanMove;
import interpreter.ICondition.CanWizz;
import interpreter.ICondition.CanHit;
import interpreter.IState;
import interpreter.ITransition;
import interpreter.IAction.Hit;
import interpreter.IAction.Move;
import interpreter.IAction.Wizz;

public class Clink extends Element {

	Direction mouvement = null;
	boolean isHitting = false;
	boolean isWizzing = false;
	
	Element inventaire = null;
	
	public Clink(Noeud courant, Model model, int x, int y) {
		super(courant, model, x, y);
		c = Color.black;
		IState s1 = new IState("depart");
		s1.id = 1;
		
		List<IBehaviour> b = new LinkedList<IBehaviour>();

		List<ITransition> t1 = new LinkedList<ITransition>();

		Move move4 = new Move(Direction.SOUTH);
		CanMove con = new CanMove(Direction.SOUTH);
		ITransition t_tmp4 = new ITransition(con, move4, s1);
		t1.add(t_tmp4);

		Move move1 = new Move(Direction.EAST);
		con = new CanMove(Direction.EAST);
		ITransition t_tmp1 = new ITransition(con, move1, s1);
		t1.add(t_tmp1);

		Move move2 = new Move(Direction.WEST);
		con = new CanMove(Direction.WEST);
		ITransition t_tmp2 = new ITransition(con, move2, s1);
		t1.add(t_tmp2);

		Move move3 = new Move(Direction.NORTH);
		con = new CanMove(Direction.NORTH);
		ITransition t_tmp3 = new ITransition(con, move3, s1);
		t1.add(t_tmp3);

		Hit hit = new Hit(null);
		CanHit con2 = new CanHit(null);
		ITransition t_tmp5 = new ITransition(con2, hit, s1);
		t1.add(t_tmp5);
		
		Wizz wizz = new Wizz();
		CanWizz con3 = new CanWizz();
		ITransition t_tmp6 = new ITransition(con3, wizz, s1);
		t1.add(t_tmp6);

		IBehaviour b_tmp1 = new IBehaviour(s1, t1);
		b.add(b_tmp1);
		
		m_auto = new IAutomaton(s1, b);
	}

	public void step(long now) throws Exception {
		if (m_auto != null)
			m_auto.step(this);
	}

	public void move(Direction direction) {
		switch (direction) {
		case NORTH:
			if (m_y - 1 >= 0) {
				m_y--;
			} else {
				m_y = Options.HAUTEUR_CARTE - 1;
			}
			break;
		case SOUTH:
			if (m_y + 1 < Options.HAUTEUR_CARTE) {
				m_y++;
			} else {
				m_y = 0;
			}
			break;
		case EAST:
			if (m_x + 1 < Options.LARGEUR_CARTE) {
				m_x++;
			} else {
				m_x = 0;
			}
			break;
		case WEST:
			if (m_x - 1 >= 0) {
				m_x--;
			} else {
				m_x = Options.LARGEUR_CARTE - 1;
			}
			break;
		default:
			break;
		}
		mouvement = null;
		isHitting = false;
	}

	public boolean canmove(Direction direction) {
		if(mouvement == null)
			return false;
		boolean movementPossible = true;
		switch (mouvement) {
		case NORTH:
			if (m_y - 1 >= 0) {
				if(m_courant.m_carte[m_x][m_y-1] instanceof Archive) {
					movementPossible = false;
				}
			} else {
				if(m_courant.m_carte[m_x][Options.HAUTEUR_CARTE - 1] instanceof Archive) {
					movementPossible = false;
				}
			}
			break;
		case SOUTH:
			if (m_y + 1 < Options.HAUTEUR_CARTE) {
				if(m_courant.m_carte[m_x][m_y+1] instanceof Archive) {
					movementPossible = false;
				}
			} else {
				if(m_courant.m_carte[m_x][0] instanceof Archive) {
					movementPossible = false;
				}
			}
			break;
		case EAST:
			if (m_x + 1 < Options.LARGEUR_CARTE) {
				if(m_courant.m_carte[m_x + 1][m_y] instanceof Archive) {
					movementPossible = false;
				}
			} else {
				if(m_courant.m_carte[0][m_y] instanceof Archive) {
					movementPossible = false;
				}
			}
			break;
		case WEST:
			if (m_x - 1 >= 0) {
				if(m_courant.m_carte[m_x - 1][m_y] instanceof Archive) {
					movementPossible = false;
				}
			} else {
				if(m_courant.m_carte[Options.LARGEUR_CARTE - 1][m_y] instanceof Archive) {
					movementPossible = false;
				}
			}
			break;
		default:
			movementPossible = false;
			break;
		}
		return mouvement == direction && movementPossible;
	}

	public void hit(Direction direction) {
		Element e = m_model.m_courant.m_carte[m_x][m_y];
		if (e instanceof Dossier) {
			Dossier d = (Dossier) e;
			m_x = 0;
			m_y = 0;
			m_model.m_courant = d.m_contenu;
			m_courant = d.m_contenu;
		}
		isHitting = false;
	}
	
	public void wizz() {
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
	
	public boolean canhit(Direction direction) {
		return isHitting;
	}
	
	public boolean canwizz() {
		if(inventaire == null) {
			if(m_courant.m_carte[m_x][m_y] != null && m_courant.m_carte[m_x][m_y] instanceof Fichier) {
				return isWizzing;
			}
		}
		else {
			if(m_courant.m_carte[m_x][m_y] == null) {
				return isWizzing;
			}
		}
		
		return false;
	}

	public void paint(Graphics g) {
		g.drawImage(m_model.m_clinkSprite, m_x * 48 + 8, m_y * 48 + 8, 32, 32, null);
	}

}
