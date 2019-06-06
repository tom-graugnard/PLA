package game.shellda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import interpreter.Direction;
import interpreter.IAutomaton;
import interpreter.IBehaviour;
import interpreter.ICondition;
import interpreter.ICondition.CanMove;

import interpreter.IState;
import interpreter.ITransition;

import interpreter.IAction.Move;
import interpreter.IAction.Pop;

public class Virus extends Element {

	int display;

	public Virus(Noeud courant, Model model, int x, int y) {
		super(courant, model, x, y);
		display = 0;
		model.m_virus.add(this);
		c = Color.magenta;

		IState s1 = new IState("un");
		s1.id = 1;
		IState s2 = new IState("deux");
		s2.id = 2;
		IState s3 = new IState("trois");
		s3.id = 3;
		IState s4 = new IState("quatre");
		s4.id = 4;

		List<IBehaviour> b = new LinkedList<IBehaviour>();

		Move move1 = new Move(Direction.EAST);
		Move move2 = new Move(Direction.WEST);
		Pop suit = new Pop(m_model.m_joueur);
		CanMove conwest = new CanMove(Direction.WEST);
		CanMove coneast = new CanMove(Direction.EAST);
		ICondition con = new ICondition();

		List<ITransition> t1 = new LinkedList<ITransition>();
		ITransition t_tmp1 = new ITransition(con, suit, s1);
		t1.add(t_tmp1);
		IBehaviour b_tmp1 = new IBehaviour(s1, t1);
		b.add(b_tmp1);


		m_auto = new IAutomaton(s1, b);
	}

	public void step(long now) throws Exception {
		if (m_auto != null)
			m_auto.step(this);
		// System.out.print(this.nbpas);

	}

	
	public boolean canmove(Direction direction) {
		switch (direction) {
		case NORTH:
			if (m_x - 1 >= 0) {
				if (m_model.m_courant.m_carte[m_x - 1][m_y] == null) {
					return true;
				}
			}
			break;
		case SOUTH:
			if (m_x + 1 < Options.HAUTEUR_CARTE) {
				if (m_model.m_courant.m_carte[m_x + 1][m_y] == null) {
					return true;
				}
			}
			break;
		case EAST:
			if (m_y + 1 < Options.LARGEUR_CARTE) {
				if (m_model.m_courant.m_carte[m_x][m_y + 1] == null) {
					return true;
				}
			}
			break;
		case WEST:
			if (m_y - 1 >= 0) {
				if (m_model.m_courant.m_carte[m_x][m_y - 1] == null) {
					return true;
				}
			}
			break;
		}
		return false;
	}

	int i = 0;

	public void move(Direction direction) {
		switch (direction) {
		case NORTH:
			if (i < 500) {
				i++;
			} else {
				i = 0;
				m_model.m_courant.m_carte[m_x][m_y] = null;
				m_x--;
				m_model.m_courant.m_carte[m_x][m_y] = this;
				System.out.println("0");
			}
			break;
		case SOUTH:
			if (i < 500) {
				i++;
			} else {
				i = 0;
				m_model.m_courant.m_carte[m_x][m_y] = null;
				m_x++;
				m_model.m_courant.m_carte[m_x][m_y] = this;
				System.out.println("0");
			}
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
	

	public void hit() {

	}

	public void egg() {

	}

	public void pop(Element e) {
		if ((Math.abs(e.m_x - m_x)) >= (Math.abs(e.m_y - m_y))) {
			if (e.m_x >= m_x) {
				if (i < 1000) {
					i++;
				} else {
					i = 0;
					m_model.m_courant.m_carte[m_x][m_y] = null;
					m_x++;
					m_model.m_courant.m_carte[m_x][m_y] = this;
					System.out.println("NORTH");
				}
			}
			else {
				if (i < 1000) {
					i++;
				} else {
					i = 0;
					m_model.m_courant.m_carte[m_x][m_y] = null;
					m_x--;
					m_model.m_courant.m_carte[m_x][m_y] = this;
					System.out.println("SOUTH");
				}
			}
		}
		else {
			if (e.m_y >= m_y) {
				if (i < 1000) {
					i++;
				} else {
					i = 0;
					m_model.m_courant.m_carte[m_x][m_y] = null;
					m_y++;
					m_model.m_courant.m_carte[m_x][m_y] = this;
					System.out.println("EAST");
				}
			}
			else {
				if (i < 1000) {
					i++;
				} else {
					i = 0;
					m_model.m_courant.m_carte[m_x][m_y] = null;
					m_y--;
					m_model.m_courant.m_carte[m_x][m_y] = this;
					System.out.println("WEST");
				}
			}
		}
	}

	public void wizz() {

	}

	void actualiser() {
		display++;
	}

	public void die(LinkedList<Virus> virus) {
		virus.remove(this);
	}
	
	public void paint(Graphics g) {
		g.drawImage(m_model.m_virusSprite, m_x*48 + 8, m_y*48 + 8, 32, 32, null);
	}

}
