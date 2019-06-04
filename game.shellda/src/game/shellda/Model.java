package game.shellda;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;

public class Model extends GameModel {

	Tree m_tree;
	Noeud m_corbeille;
	LinkedList<Virus> m_virus;
	
	Noeud m_courant;
	
	BufferedImage m_virusSprite;

	public Model() {
		loadSprites();
		m_virus = new LinkedList<Virus>();
		m_corbeille = new Noeud(null);
		m_tree = new Tree(m_corbeille, m_virus);
		m_courant = m_tree.m_root;
	}
	
	private void loadSprites() {
		File imageFile = new File("ressources/Virus.png");
		try {
			m_virusSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutdown() {
		System.exit(0);
	}

}
