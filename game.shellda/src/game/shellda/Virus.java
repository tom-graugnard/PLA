package game.shellda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import interpreter.Direction;
import interpreter.IAutomaton;
import interpreter.IBehaviour;
import interpreter.ICondition;
import interpreter.ICondition.CanMove;

import interpreter.IState;
import interpreter.ITransition;

import interpreter.IAction.Move;
import interpreter.IAction.Pop;
import interpreter.IAction.Wizz;
import interpreter.IAction.Egg;

public class Virus extends Element {

	int display;
	boolean m_decouvert;

	int type;
	boolean m_worked;

	public Virus(Noeud courant, Model model, int x, int y) {
		super(courant, model, x, y);
		display = 0;
		model.m_virus.add(this);
		Random rand = new Random();
		type = rand.nextInt(4);
		c = Color.magenta;

		IState s1 = new IState("un");
		s1.id = 1;

		List<IBehaviour> b = new LinkedList<IBehaviour>();

		Pop suit = new Pop();
		ICondition con = new ICondition();

		Egg egg = new Egg();

		Wizz wizz = new Wizz();

		List<ITransition> t1 = new LinkedList<ITransition>();
		ITransition t_tmp1 = new ITransition(con, wizz, s1);
		t1.add(t_tmp1);
		IBehaviour b_tmp1 = new IBehaviour(s1, t1);
		b.add(b_tmp1);

		m_auto = new IAutomaton(s1, b);
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
		// System.out.print(this.nbpas);

	}

	public boolean canmove(Direction direction) {
		switch (direction) {
		case NORTH:
			if (m_y - 1 >= 0) {
				if (m_courant.m_carte[m_x][m_y - 1] == null) {
					return true;
				}
			}
			break;
		case SOUTH:
			if (m_y + 1 < Options.HAUTEUR_CARTE) {
				if (m_courant.m_carte[m_x][m_y + 1] == null) {
					return true;
				}
			}
			break;
		case EAST:
			if (m_x + 1 < Options.LARGEUR_CARTE) {
				if (m_courant.m_carte[m_x + 1][m_y] == null) {
					return true;
				}
			}
			break;
		case WEST:
			if (m_x - 1 >= 0) {
				if (m_courant.m_carte[m_x - 1][m_y] == null) {
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
			if (i < 800) {
				i++;
			} else {
				i = 0;
				if (m_y - 1 >= 0 && m_courant.m_carte[m_x][m_y - 1] == null) {
					m_courant.m_carte[m_x][m_y] = null;
					m_y--;
					m_courant.m_carte[m_x][m_y] = this;
					System.out.println("NORTH");
					m_worked = true;
				}
			}
			break;
		case SOUTH:
			if (i < 800) {
				i++;
			} else {
				i = 0;
				if (m_y + 1 < Options.HAUTEUR_CARTE && m_courant.m_carte[m_x][m_y + 1] == null) {
					m_courant.m_carte[m_x][m_y] = null;
					m_y++;
					m_courant.m_carte[m_x][m_y] = this;
					System.out.println("SOUTH");
					m_worked = true;
				}
			}
			break;
		case EAST:
			if (i < 800) {
				i++;
			} else {
				i = 0;
				if (m_x + 1 < Options.LARGEUR_CARTE && m_courant.m_carte[m_x + 1][m_y] == null) {
					m_courant.m_carte[m_x][m_y] = null;
					m_x++;
					m_courant.m_carte[m_x][m_y] = this;
					System.out.println("EAST");
					m_worked = true;
				}
			}
			break;
		case WEST:
			if (i < 800) {
				i++;
			} else {
				i = 0;
				if (m_x - 1 >= 0 && m_courant.m_carte[m_x - 1][m_y] == null) {
					m_courant.m_carte[m_x][m_y] = null;
					m_x--;
					m_courant.m_carte[m_x][m_y] = this;
					System.out.println("WEST");
					m_worked = true;
				}
			}
			break;
		default:
			break;
		}
	}

	public void hit() {

	}

	public void egg() {
		if (i < 300) {
			i++;
		} else {
			i = 0;
			if (canmove(Direction.WEST)) {
				Virus v = new Virus(m_courant, m_model, m_x - 1, m_y);
				v.m_decouvert = true;
				m_courant.m_carte[m_x - 1][m_y] = v;

			} else if (canmove(Direction.EAST)) {
				Virus v = new Virus(m_courant, m_model, m_x + 1, m_y);
				v.m_decouvert = true;
				m_courant.m_carte[m_x + 1][m_y] = v;
			} else if (canmove(Direction.NORTH)) {
				Virus v = new Virus(m_courant, m_model, m_x, m_y - 1);
				v.m_decouvert = true;
				m_courant.m_carte[m_x][m_y - 1] = v;
			} else if (canmove(Direction.SOUTH)) {
				Virus v = new Virus(m_courant, m_model, m_x, m_y + 1);
				v.m_decouvert = true;
				m_courant.m_carte[m_x][m_y + 1] = v;
			}
		}
	}

	public void pop() {

		int dis_ver = m_model.m_joueur.m_y - m_y;
		int dis_hor = m_model.m_joueur.m_x - m_x;

		if (Math.abs(dis_ver + dis_hor) > 1) {
			Direction dir1, dir2;

			if (dis_ver < dis_hor) {
				if (dis_ver < 0) {
					dir1 = Direction.NORTH;
				} else {
					dir1 = Direction.SOUTH;
				}
				if (dis_hor < 0) {
					dir2 = Direction.WEST;
				} else {
					dir2 = Direction.EAST;
				}
			} else {
				if (dis_ver < 0) {
					dir2 = Direction.NORTH;
				} else {
					dir2 = Direction.SOUTH;
				}
				if (dis_hor < 0) {
					dir1 = Direction.WEST;
				} else {
					dir1 = Direction.EAST;
				}
			}

			move(dir1);

			if (!m_worked) {
				move(dir2);

			}
			m_worked = false;
		}
	}

	Fichier fichierProche() {
		int min = 99;
		int tmp;
		Fichier res = null;
		for (int i = 0; i < Options.LARGEUR_CARTE; i++) {
			for (int j = 0; j < Options.HAUTEUR_CARTE; j++) {
				Element e = m_courant.m_carte[i][j];
				if (e instanceof Fichier) {
					Fichier f = (Fichier) e;
					tmp = Math.abs(f.m_y - m_y) + Math.abs(f.m_x - m_x);
					if (tmp < min) {
						min = tmp;
						res = f;
					}
				}
			}
		}
		return res;
	}

	public void wizz() {
		Fichier f = fichierProche();
		int dis_ver = f.m_y - m_y;
		int dis_hor = f.m_x - m_x;

		if (Math.abs(dis_ver + dis_hor) > 1) {

			Direction dir1, dir2;

			if (dis_ver < dis_hor) {
				if (dis_ver < 0) {
					dir1 = Direction.NORTH;
				} else {
					dir1 = Direction.SOUTH;
				}
				if (dis_hor <= 0 && m_x != 0) {
					dir2 = Direction.WEST;
				} else {
					dir2 = Direction.EAST;
				}
			} else {
				if (dis_ver <= 0 && m_y != 0) {
					dir2 = Direction.NORTH;
				} else {
					dir2 = Direction.SOUTH;
				}
				if (dis_hor < 0) {
					dir1 = Direction.WEST;
				} else {
					dir1 = Direction.EAST;
				}
			}

			move(dir1);

			if (!m_worked) {
				move(dir2);
			}
			m_worked = false;
		}

	}

	void actualiser() {
		display++;
	}

	public void die(LinkedList<Virus> virus) {
		virus.remove(this);
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
