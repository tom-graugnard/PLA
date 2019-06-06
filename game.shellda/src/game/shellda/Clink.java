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
import interpreter.IState;
import interpreter.ITransition;
import interpreter.IAction.Hit;
import interpreter.IAction.Move;

public class Clink extends Element {

	boolean canEast, canNorth, canSouth, canWest;

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

		IBehaviour b_tmp1 = new IBehaviour(s1, t1);
		b.add(b_tmp1);

		m_auto = new IAutomaton(s1, b);
	}

	public void step(long now) throws Exception {
		if (m_auto != null)
			m_auto.step(this);
	}

	int i = 0;

	public void move(Direction direction) {
		switch (direction) {
		case NORTH:
			if (i < 150) {
				i++;
			} else {
				i = 0;
				if (m_y - 1 >= 0) {
					m_y--;
				} else {
					m_y = Options.HAUTEUR_CARTE - 1;
				}
				System.out.println("North");
			}
			break;
		case SOUTH:
			if (i < 150) {
				i++;
			} else {
				i = 0;
				if (m_y + 1 < Options.HAUTEUR_CARTE) {
					m_y++;
				} else {
					m_y = 0;
				}
				System.out.println("South");
			}
			break;
		case EAST:
			if (i < 150) {
				i++;
			} else {
				i = 0;
				if (m_x + 1 < Options.LARGEUR_CARTE) {
					m_x++;
				} else {
					m_x = 0;
				}
				System.out.println("East");
			}
			break;
		case WEST:
			if (i < 150) {
				i++;
			} else {
				i = 0;
				if (m_x - 1 >= 0) {
					m_x--;
				} else {
					m_x = Options.LARGEUR_CARTE - 1;
				}
				System.out.println("West");
			}
			break;
		default:
			break;
		}
	}

	public boolean canmove(Direction direction) {
		switch (direction) {
		case NORTH:
			return canNorth;
		case SOUTH:
			return canSouth;
		case EAST:
			return canEast;
		case WEST:
			return canWest;
		default:
			return false;
		}
	}

	public void paint(Graphics g) {
		g.drawImage(m_model.m_clinkSprite, m_x*48 + 8, m_y*48 + 8, 32, 32, null);
	}
	
}
