package game.shellda;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import interpreter.IDirection;
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

	public Clink(Noeud n, Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale) {
		super(n, model, no, sprite, rows, columns, x, y, scale);
		c = Color.black;
		IState s1 = new IState("depart");
		s1.id = 1;

		List<IBehaviour> b = new LinkedList<IBehaviour>();

		List<ITransition> t1 = new LinkedList<ITransition>();

		Move move4 = new Move(IDirection.SOUTH);
		CanMove con = new CanMove(IDirection.SOUTH);
		ITransition t_tmp4 = new ITransition(con, move4, s1);
		t1.add(t_tmp4);

		Move move1 = new Move(IDirection.EAST);
		con = new CanMove(IDirection.EAST);
		ITransition t_tmp1 = new ITransition(con, move1, s1);
		t1.add(t_tmp1);

		Move move2 = new Move(IDirection.WEST);
		con = new CanMove(IDirection.WEST);
		ITransition t_tmp2 = new ITransition(con, move2, s1);
		t1.add(t_tmp2);

		Move move3 = new Move(IDirection.NORTH);
		con = new CanMove(IDirection.NORTH);
		ITransition t_tmp3 = new ITransition(con, move3, s1);
		t1.add(t_tmp3);

		IBehaviour b_tmp1 = new IBehaviour(s1, t1);
		b.add(b_tmp1);

		auto = new IAutomaton(s1, b);
	}

	public void step(long now) throws Exception {
		if (auto != null)
			auto.step(this);
	}

	int i = 0;

	public void move(IDirection direction) {
		switch (direction) {
		case NORTH:

			if (i < 150) {
				i++;
			} else {
				i = 0;
				m_model.m_courant.m_carte[m_x][m_y] = null;
				m_x--;
				m_model.m_courant.m_carte[m_x][m_y] = this;
				System.out.println("North");
			}
			break;
		case SOUTH:

			if (i < 150) {
				i++;
			} else {

				i = 0;
				m_model.m_courant.m_carte[m_x][m_y] = null;
				m_x++;
				m_model.m_courant.m_carte[m_x][m_y] = this;
				System.out.println("South");

			}
			break;
		case EAST:

			if (i < 150) {
				i++;
			} else {

				i = 0;
				m_model.m_courant.m_carte[m_x][m_y] = null;
				m_y++;
				m_model.m_courant.m_carte[m_x][m_y] = this;
				System.out.println("East");

			}
			break;
		case WEST:

			if (i < 150) {
				i++;
			} else {
				i = 0;
				m_model.m_courant.m_carte[m_x][m_y] = null;
				m_y--;
				m_model.m_courant.m_carte[m_x][m_y] = this;
				System.out.println("West");
			}
			break;
		default:
			break;
		}
	}


	public boolean canmove(IDirection direction) {
		switch (direction) {
		case NORTH:
			if (m_x - 1 >= 0) {
				if (canNorth && m_model.m_courant.m_carte[m_x - 1][m_y] == null) {
					return true;
				}
			}
			break;
		case SOUTH:
			if (canSouth && m_x + 1 < Options.HAUTEUR_CARTE) {
				if (m_model.m_courant.m_carte[m_x + 1][m_y] == null) {
					return true;
				}
			}
			break;
		case EAST:
			if (canEast && m_y + 1 < Options.LARGEUR_CARTE) {
				if (m_model.m_courant.m_carte[m_x][m_y + 1] == null) {
					return true;
				}
			}
			break;
		case WEST:
			if (canWest && m_y - 1 >= 0) {
				if (m_model.m_courant.m_carte[m_x][m_y - 1] == null) {
					return true;
				}
			}
			break;
		}
		return false;
	}

}
