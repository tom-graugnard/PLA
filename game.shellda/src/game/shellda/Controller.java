package game.shellda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import edu.ricm3.game.GameController;
import interpreter.IKey;

public class Controller extends GameController implements ActionListener {
	Model m_model;
	View m_view;

	public Controller(Model model, View view) {
		m_model = model;
		m_view = view;
		m_model.m_keys = new LinkedList<IKey>();
	}

	@Override
	public void notifyVisible() {
	}

	@Override
	public void step(long now) {
		if (m_model.gameStart) {
			m_model.step(now);
			m_view.step(now);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		switch (c) {
		case KeyEvent.VK_RIGHT:
			m_model.removeKey("FR");
			m_model.m_keys.add(new IKey("FR"));
			break;
		case KeyEvent.VK_LEFT:
			m_model.removeKey("FL");
			m_model.m_keys.add(new IKey("FL"));
			break;
		case KeyEvent.VK_UP:
			m_model.removeKey("FU");
			m_model.m_keys.add(new IKey("FU"));
			break;
		case KeyEvent.VK_DOWN:
			m_model.removeKey("FD");
			m_model.m_keys.add(new IKey("FD"));
			break;
		case KeyEvent.VK_H:
			m_model.removeKey("h");
			m_model.m_keys.add(new IKey("h"));
			break;
		case KeyEvent.VK_W:
			m_model.removeKey("w");
			m_model.m_keys.add(new IKey("w"));
			break;
		case KeyEvent.VK_P:
			m_model.removeKey("p");
			m_model.m_keys.add(new IKey("p"));
			break;
		case KeyEvent.VK_A:
			Options.PC_SPEED = 100;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int c = e.getKeyCode();
		switch (c) {
		case KeyEvent.VK_RIGHT:
			m_model.removeKey("FR");
			break;
		case KeyEvent.VK_LEFT:
			m_model.removeKey("FL");
			break;
		case KeyEvent.VK_UP:
			m_model.removeKey("FU");
			break;
		case KeyEvent.VK_DOWN:
			m_model.removeKey("FD");
			break;
		case KeyEvent.VK_H:
			m_model.removeKey("h");
			break;
		case KeyEvent.VK_W:
			m_model.removeKey("w");
			break;
		case KeyEvent.VK_P:
			m_model.removeKey("p");
			break;
		case KeyEvent.VK_A:
			Options.PC_SPEED = 1000;
			break;
		default:
			break;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// explosion quand on clique sur le fantome

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (m_model.m_boutonplay.inside(e.getX(), e.getY()) && m_model.gameStart == false)
			m_model.gameStart = true;
		if (m_model.m_boutonexit.inside(e.getX(), e.getY()) && m_model.gameStart == false
				&& m_model.gameOption == false)
			m_model.shutdown();
		if (m_model.m_boutonexit.inside(e.getX(), e.getY()) && m_model.gameStart == false && m_model.gameOption == true) {
			m_model.gameOption = false;
			m_view.choix=false;
		}
		if (m_model.m_boutonoption.inside(e.getX(), e.getY()) && m_model.gameStart == false
				&& m_model.gameOption == false)
			m_model.gameOption = true;
		if (m_view.choix == true) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if(m_view.inside(e.getX(), e.getY(),200 + j*100,20+i*80,80,50)) {
						System.out.println(j + " " + i);
						m_model.m_autoChoix[i]=j;
					}
				}
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
