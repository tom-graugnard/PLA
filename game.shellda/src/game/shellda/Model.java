package game.shellda;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import interpreter.IKey;

public class Model extends GameModel {

	Tree m_tree;
	Noeud m_corbeille;
	LinkedList<Virus> m_virus;

	Noeud m_courant;

	Clink m_joueur;

	BufferedImage m_boutonplaySprite;
	BoutonPlay m_boutonplay;
	BufferedImage m_boutonexitSprite;
	BoutonExit m_boutonexit;
	boolean gameStart = false;

	Font m_font;

	BufferedImage m_executableSprite;

	BufferedImage m_clinkSprite;
	BufferedImage m_dossierSprite;
	BufferedImage m_dossierRetourSprite;
	BufferedImage m_corbeilleSprite;
	BufferedImage m_fichierSprite;
	BufferedImage m_backgroundSprite;
	BufferedImage m_backgroundSelectedSprite;
	BufferedImage m_archiveSprite;

	BufferedImage m_virus1Sprite;
	BufferedImage m_virus2Sprite;
	BufferedImage m_virus3Sprite;
	BufferedImage m_virus4Sprite;
	
	public LinkedList<IKey> m_keys;

	public Model() {
		loadSprites();
		m_virus = new LinkedList<Virus>();
		m_corbeille = new Noeud(this, "Corbeille");
		m_joueur = new Clink(null, this, 3, 3);
		m_tree = new Tree(this);
		m_courant = m_tree.m_root;
		m_joueur.m_courant = m_courant;
		m_courant.m_carte[1][1] = m_joueur;

		m_boutonplay = new BoutonPlay(this, 0, m_boutonplaySprite, 1, 1,
				Options.WIDTH / 2 - (int) (m_boutonplaySprite.getWidth() * Options.BoutonPlayScale) / 2,
				Options.HEIGHT / 2 - (int) (m_boutonplaySprite.getHeight() * Options.BoutonPlayScale) / 2,
				Options.BoutonPlayScale);
		m_boutonexit = new BoutonExit(this, 0, m_boutonexitSprite, 1, 1, Options.WIDTH - 40, 0,
				Options.BoutonExitScale);
	}

	private void loadSprites() {
		m_font = new Font("Arial", 0, 9);
		File imageFile;
		imageFile = new File("ressources/virus_1.png");
		try {
			m_virus1Sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/virus_2.png");
		try {
			m_virus2Sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/virus_3.png");
		try {
			m_virus3Sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/virus_4.png");
		try {
			m_virus4Sprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/clink.png");
		try {
			m_clinkSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/background.png");
		try {
			m_backgroundSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/background_selected.png");
		try {
			m_backgroundSelectedSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/dossier.png");
		try {
			m_dossierSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/dossier_retour.png");
		try {
			m_dossierRetourSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/fichier.png");
		try {
			m_fichierSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/corbeille.png");
		try {
			m_corbeilleSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/archive.png");
		try {
			m_archiveSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/executable.png");
		try {
			m_executableSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// ===== Menu =====
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
	 * private void splitSprite() { int width = m_sprite.getWidth(null); int height
	 * = m_sprite.getHeight(null); m_sprites = new BufferedImage[m_nrows * m_ncols];
	 * m_w = width / m_ncols; m_h = height / m_nrows; for (int i = 0; i < m_nrows;
	 * i++) { for (int j = 0; j < m_ncols; j++) { int x = j * m_w; int y = i * m_h;
	 * m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w, m_h); } } }
	 */

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub
		for (int i = 0; i < Options.LARGEUR_CARTE; i++) {
			for (int j = 0; j < Options.HAUTEUR_CARTE; j++) {
				try {
					if (m_courant.m_carte[i][j] != null && !(m_courant.m_carte[i][j] instanceof Virus))
						m_courant.m_carte[i][j].step(now);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		try {
			m_joueur.step(now);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < m_virus.size(); i++) {
			Virus v = m_virus.get(i);
			if (v.isDiscovered()) {
				try {
					v.step(now);
				} catch (Exception e) {
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
