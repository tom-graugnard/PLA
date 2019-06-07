package game.shellda;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import interpreter.IAutomaton;
import interpreter.IKey;

import parser.AutomataParser;

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
	
	IAutomaton m_automateVirus;
	IAutomaton m_automateJoueur;
	IAutomaton m_automateFichier;

	public LinkedList<IKey> m_keys;

	public Model() {
		// Lecture d'automate
		List<IAutomaton> automates = null;
		try {
			automates = AutomataParser.automatas_from_file("examples/shellda.txt");
		} catch (Exception e) {
			System.out.println("Erreur dans la récupération d'automates");
		}
		for(int i = 0; i < automates.size(); i++) {
			if(automates.get(i).m_name.equals("Virus")) {
				m_automateVirus = automates.get(i);
			}
			else if(automates.get(i).m_name.equals("Joueur")) {
				m_automateJoueur = automates.get(i);
			}
			else if(automates.get(i).m_name.equals("Fichier")) {
				m_automateFichier = automates.get(i);
			}
		}

		loadSprites();
		m_virus = new LinkedList<Virus>();
		m_corbeille = new Noeud(this, "Corbeille");
		m_joueur = new Clink(null, this, 3, 3);
		m_tree = new Tree(this);
		m_courant = m_tree.m_root;
		m_joueur.m_courant = m_courant;

		m_boutonplay = new BoutonPlay(this, 0, m_boutonplaySprite, 1, 1,
				Options.WIDTH / 2 - (int) (m_boutonplaySprite.getWidth() * Options.BoutonPlayScale) / 2,
				Options.HEIGHT / 2 - (int) (m_boutonplaySprite.getHeight() * Options.BoutonPlayScale) / 2,
				Options.BoutonPlayScale);
		m_boutonexit = new BoutonExit(this, 0, m_boutonexitSprite, 1, 1, Options.WIDTH - 40, 0,
				Options.BoutonExitScale);
	}
	
	public boolean removeKey(String key) {
		for(int i = 0; i < m_keys.size(); i++) {
			if(m_keys.get(i).m_key.equals(key)){
				m_keys.remove(i);
				return true;
			}
		}
		return false;
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
	
	long old = 0;
	
	@Override
	public void step(long now) {
		if(now - old > 1000) {
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
			old = now;
		}

		try {
			m_joueur.step(now);
		} catch (Exception e) {
			e.printStackTrace();
		}

		

	}

	@Override
	public void shutdown() {
		System.exit(0);
	}

}
