package game.shellda;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

import edu.ricm3.game.GameController;
import interpreter.IDirection;

public class Controller extends GameController implements ActionListener {
	Model m_model;
	View m_view;

	public Controller(Model model, View view) {
		// TODO Auto-generated constructor stub
		m_model = model;
		m_view = view;
	}

	@Override
	public void notifyVisible() {
		// TODO Auto-generated method stub

	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub
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
			m_model.m_joueur.mouvement = Direction.EAST;
			break;
		case KeyEvent.VK_LEFT:
			m_model.m_joueur.mouvement = Direction.WEST;
			break;
		case KeyEvent.VK_UP:
			m_model.m_joueur.mouvement = Direction.NORTH;
			break;
		case KeyEvent.VK_DOWN:
			m_model.m_joueur.mouvement = Direction.SOUTH;
			break;
		case KeyEvent.VK_H:
			m_model.m_joueur.isHitting = true;
			break;
		case KeyEvent.VK_W:
			m_model.m_joueur.isWizzing = true;
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
			break;
		case KeyEvent.VK_LEFT:
			break;
		case KeyEvent.VK_UP:
			break;
		case KeyEvent.VK_DOWN:
			break;
		case KeyEvent.VK_H:
			m_model.m_joueur.isHitting = false;
			break;
		case KeyEvent.VK_W:
			m_model.m_joueur.isWizzing = false;
			break;
		default:
			break;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (m_model.m_boutonplay.inside(e.getX(), e.getY()) && m_model.gameStart == false)
			m_model.gameStart = true;
		if (m_model.m_boutonexit.inside(e.getX(), e.getY()))
			m_model.shutdown();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// explosion quand on clique sur le fantome

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

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
