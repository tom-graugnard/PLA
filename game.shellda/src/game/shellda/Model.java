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
	
	Clink m_joueur;

	BufferedImage m_virusSprite;

	BufferedImage m_boutonplaySprite;
	BoutonPlay m_boutonplay;
	BufferedImage m_boutonexitSprite;
	BoutonExit m_boutonexit;
	boolean gameStart = false;
	BufferedImage m_clinkSprite;
	BufferedImage m_dossierSprite;
	BufferedImage m_corbeilleSprite;

	public Model() {
		loadSprites();
		m_virus = new LinkedList<Virus>();
		m_corbeille = new Noeud(this);
		m_tree = new Tree(this);
		m_courant = m_tree.m_root;
		m_joueur = new Clink(m_courant, this, 1, 1);
		m_courant.m_carte[1][1] = m_joueur;

		m_boutonplay = new BoutonPlay(this, 0, m_boutonplaySprite, 1, 1,
				Options.WIDTH / 2 - (int) (m_boutonplaySprite.getWidth() * Options.BoutonPlayScale) / 2,
				Options.HEIGHT / 2 - (int) (m_boutonplaySprite.getHeight() * Options.BoutonPlayScale) / 2,
				Options.BoutonPlayScale);
		m_boutonexit = new BoutonExit(this, 0, m_boutonexitSprite, 1, 1, Options.WIDTH - 40, 0,
				Options.BoutonExitScale);
	}

	private void loadSprites() {
		File imageFile = new File("ressources/Virus.png");
		try {
			m_virusSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/bouton.png");
		try {
			m_boutonplaySprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/exit.png");
		try {
			m_boutonexitSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}

	/*
	private void splitSprite() {
		int width = m_sprite.getWidth(null);
		int height = m_sprite.getHeight(null);
		m_sprites = new BufferedImage[m_nrows * m_ncols];
		m_w = width / m_ncols;
		m_h = height / m_nrows;
		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				int x = j * m_w;
				int y = i * m_h;
				m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
			}
		}
	}
	*/
	
	@Override
	public void step(long now) {
		// TODO Auto-generated method stub
		for (int i = 0; i < Options.LARGEUR_CARTE; i++) {
			for (int j = 0; j < Options.HAUTEUR_CARTE; j++) {
				try {
					if (m_courant.m_carte[i][j] != null)
						m_courant.m_carte[i][j].step(now);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void shutdown() {
		System.exit(0);
	}

}
