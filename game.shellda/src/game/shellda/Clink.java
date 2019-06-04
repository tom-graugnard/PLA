package game.shellda;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import interpreter.Direction;
import interpreter.IAutomaton;
import interpreter.IBehaviour;
import interpreter.ICondition;
import interpreter.IState;
import interpreter.ITransition;
import interpreter.IAction.Hit;
import interpreter.IAction.Move;

public class Clink extends Element {

	public Clink(Noeud n, Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale) {
		super(n, model, no, sprite, rows, columns, x, y, scale);
		c = Color.black;
		IState s1 = new IState("un");
		s1.id = 1;
		IState s2 = new IState("deux");
		s2.id = 2;

		List<IBehaviour> b = new LinkedList<IBehaviour>();

		List<ITransition> t1 = new LinkedList<ITransition>();
		Move move1 = new Move(Direction.EAST);
		ICondition con = new ICondition();
		ITransition t_tmp1 = new ITransition(con, move1, s2);
		t1.add(t_tmp1);
		IBehaviour b_tmp1 = new IBehaviour(s1, t1);
		b.add(b_tmp1);
		List<ITransition> t2 = new LinkedList<ITransition>();
		Move move2 = new Move(Direction.WEST);
		ITransition t_tmp2 = new ITransition(con, move2, s1);
		t2.add(t_tmp2);
		IBehaviour b_tmp2 = new IBehaviour(s2, t2);
		b.add(b_tmp2);

		auto = new IAutomaton(s1, b);
	}

	public void step(long now) throws Exception {
		if (auto != null)
			auto.step(this);
	}
	
	int i = 0;

	public void move(Direction direction) {
		switch (direction) {
		case NORTH:
			this.m_y--;
			break;
		case SOUTH:
			this.m_y++;
			break;
		case EAST:
			if (i < 500) {
				i++;
			} else {
				i = 0;
				m_model.m_courant.m_carte[m_x][m_y] = null;
				m_y++;
				m_model.m_courant.m_carte[m_x][m_y] = this;
				System.out.println("0");
			}

			break;
		case WEST:
			if (i < 500) {
				i++;
			} else {
				i = 0;
				m_model.m_courant.m_carte[m_x][m_y] = null;
				m_y--;
				m_model.m_courant.m_carte[m_x][m_y] = this;
				System.out.println("1");
			}
			break;
		default:
			break;
		}
	}

}
