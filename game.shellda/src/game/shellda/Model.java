package game.shellda;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import game.shellda.Clink.ClinkNorm;
import interpreter.IAutomaton;
import interpreter.IKey;

import parser.AutomataParser;

public class Model extends GameModel {

	Tree m_tree;
	Noeud m_corbeille;
	LinkedList<Virus> m_virus;
	Balle m_balle;


	Noeud m_courant;

	int m_pourcentage_defaite;
	int m_nb_fichier_corbeille;

	Clink m_joueur;
	Noeud corb_parent;

	BufferedImage m_boutonplaySprite;
	BoutonPlay m_boutonplay;
	BufferedImage m_boutonexitSprite;
	BoutonExit m_boutonexit;
	BufferedImage m_boutonOptionSprite;
	BoutonOption m_boutonoption;

	BufferedImage m_logoSprite;
	Logo m_logo;

	BoutonExit1 m_boutonexit1;
	Boutonreplay m_boutonreplay;
	BufferedImage m_boutonreplaySprite;
	boolean gameStart = false;
	boolean gameOption = false;

	Font m_font;

	BufferedImage m_executableSprite;

	BufferedImage m_clink_nSprite;
	BufferedImage[] m_clink_cSprite;
	BufferedImage m_dossierSprite;
	BufferedImage m_dossierRetourSprite;
	BufferedImage m_corbeilleSprite;
	BufferedImage[] m_fichierSprite;
	BufferedImage m_fichierCorrompuSprite;
	BufferedImage m_backgroundSprite;
	BufferedImage m_backgroundSelectedSprite;
	BufferedImage m_archiveSprite;
	BufferedImage[] m_balleSprite;
	BufferedImage m_executableCorbeilleSprite;
	BufferedImage m_winSprite;
;


	BufferedImage m_virus1Sprite;
	BufferedImage m_virus2Sprite;
	BufferedImage m_virus3Sprite;
	BufferedImage m_virus4Sprite;
	
	public boolean m_defaite=false;
	public boolean m_victoire=false;

	IAutomaton[] m_automate = new IAutomaton[6];
	int[] m_autoChoix = { 0, 1, 2, 3, 4, 5 };

	public LinkedList<IKey> m_keys;

	NameGenerator m_generator;

	public Model(String autoName) {
		m_clink_cSprite = new BufferedImage[7];
		m_fichierSprite = new BufferedImage[4];
		m_balleSprite = new BufferedImage[6];
		m_generator = new NameGenerator();
		// Lecture d'automate
		List<IAutomaton> automates = null;
		try {
			automates = AutomataParser.automatas_from_file("examples/"+autoName);
		} catch (Exception e) {
			System.out.println("Erreur dans la récupération d'automates");
		}
		for (int i = 0; i < automates.size(); i++) {
			if (automates.get(i).m_name.equals("Virus")) {
				m_automate[0] = automates.get(i);
			} else if (automates.get(i).m_name.equals("Joueur1")) {
				m_automate[1] = automates.get(i);
			} else if (automates.get(i).m_name.equals("Joueur2")) {
				m_automate[2] = automates.get(i);
			} else if (automates.get(i).m_name.equals("Fichier")) {
				m_automate[3] = automates.get(i);
			} else if (automates.get(i).m_name.equals("Balle")) {
				m_automate[4] = automates.get(i);
			} else if (automates.get(i).m_name.equals("Archive")) {
				m_automate[5] = automates.get(i);
			}

		}

		loadSprites();
		m_virus = new LinkedList<Virus>();
		m_corbeille = new Noeud(this, "Corbeille");
		m_joueur = new ClinkNorm(null, this, 0, 0);
		m_tree = new Tree(this);
		m_courant = m_tree.m_root;
		m_joueur.m_courant = m_courant;

		m_boutonplay = new BoutonPlay(this, 0, m_boutonplaySprite, 1, 1,
				Options.WIDTH / 2 - (int) (m_boutonplaySprite.getWidth() * Options.BoutonPlayScale) / 2,
				Options.HEIGHT / 2 - (int) (m_boutonplaySprite.getHeight() * Options.BoutonPlayScale) / 2,
				Options.BoutonPlayScale);
		m_boutonexit = new BoutonExit(this, 0, m_boutonexitSprite, 1, 1, Options.WIDTH - 40, 0,
				Options.BoutonExitScale);
		m_boutonoption = new BoutonOption(this, 0, m_boutonOptionSprite, 1, 1,
				Options.WIDTH / 2 - (int) (m_boutonOptionSprite.getWidth() * Options.BoutonOptionScale) / 2,
				Options.HEIGHT - 200, Options.BoutonOptionScale);
		m_logo = new Logo(this, 0, m_logoSprite, 1, 1,
				Options.WIDTH / 2 - (int) (m_logoSprite.getWidth() * Options.LogoScale) / 2,
				Options.HEIGHT / 2 - 150 - (int) (m_logoSprite.getHeight() * Options.LogoScale) / 2, Options.LogoScale);
		
		m_boutonreplay = new Boutonreplay(this, 0, m_boutonreplaySprite, 1, 1,Options.WIDTH / 2 - (int) (m_boutonreplaySprite.getWidth()) / 2 -200,
				Options.HEIGHT / 2 - (int) (m_boutonreplaySprite.getHeight()) / 2-100, Options.BoutonReplayScale);
		
		m_boutonexit1 = new BoutonExit1(this, 0, m_boutonexitSprite, 1, 1, Options.WIDTH / 2 - (int) (m_boutonexitSprite.getWidth()*Options.BoutonExitScale1) / 2+100, Options.HEIGHT / 2 - (int) (m_boutonreplaySprite.getHeight()*Options.BoutonExitScale1) / 2-100,
				Options.BoutonExitScale1);
		
		
	}

	public boolean removeKey(String key) {
		for (int i = 0; i < m_keys.size(); i++) {
			if (m_keys.get(i).m_key.equals(key)) {
				m_keys.remove(i);
				return true;
			}
		}
		return false;
	}

	public void pourcentageDefaite() {
		int i, j, total = 0;
		for (i = 0; i < Options.LARGEUR_CARTE; i++) {
			for (j = 0; j < Options.HAUTEUR_CARTE; j++) {
				if (m_corbeille.m_carte[i][j] instanceof Fichier)
					total++;
			}
		}
		m_pourcentage_defaite = total * (100 / Options.CORROMPU_DEFAITE);

	}
	
	private void loadSprites() {
		int i;
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
			m_clink_nSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		for (i = 0; i < 7; i++) {
			imageFile = new File("ressources/clink_corbeille_" + i + ".png");
			try {
				m_clink_cSprite[i] = ImageIO.read(imageFile);
			} catch (IOException ex) {
				ex.printStackTrace();
				System.exit(-1);
			}
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
			m_fichierSprite[0] = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/fichier_25.png");
		try {
			m_fichierSprite[1] = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/fichier_50.png");
		try {
			m_fichierSprite[2] = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/fichier_75.png");
		try {
			m_fichierSprite[3] = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		imageFile = new File("ressources/fichier_corbeille.png");
		try {
			m_fichierCorrompuSprite = ImageIO.read(imageFile);
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
		imageFile = new File("ressources/executable_corbeille.png");
		try {
			m_executableCorbeilleSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		for (i = 1; i < 7; i++) {
			imageFile = new File("ressources/code_" + i + ".png");
			try {
				m_balleSprite[i - 1] = ImageIO.read(imageFile);
			} catch (IOException ex) {
				ex.printStackTrace();
				System.exit(-1);
			}
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
		imageFile = new File("ressources/boutonoption.png");
		try {
			m_boutonOptionSprite = ImageIO.read(imageFile);
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
		imageFile = new File("ressources/logo.png");
		try {
			m_logoSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
		imageFile = new File("ressources/Boutonreplay.png");
		try {
			m_boutonreplaySprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
		imageFile = new File("ressources/win.jpg");
		try {
			m_winSprite = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		

		
	}

	long m_old_courant = 0;
	long m_old_tree = 0;
	long m = 0;



	@Override
	public void step(long now) {

		for (int i = 0; i < Options.LARGEUR_CARTE; i++) {
			for (int j = 0; j < Options.HAUTEUR_CARTE; j++) {
				try {
					if (m_courant.m_carte[i][j] != null)
						m_courant.m_carte[i][j].update(now);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// Mise à jour des éléments
		if (now - m_old_courant > Options.PC_SPEED) {
			LinkedList<Element> elems = new LinkedList<Element>();
			for (int i = 0; i < Options.LARGEUR_CARTE; i++) {
				for (int j = 0; j < Options.HAUTEUR_CARTE; j++) {
					try {
						if (m_courant.m_carte[i][j] != null && !elems.contains(m_courant.m_carte[i][j])
								&& !(m_courant.m_carte[i][j] instanceof Virus)
								&& !(m_courant.m_carte[i][j] instanceof Balle)) {
							elems.add(m_courant.m_carte[i][j]);
							m_courant.m_carte[i][j].step(now);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			for (int i = 0; i < m_virus.size(); i++) {
				Virus v = m_virus.get(i);
				if (v.m_courant == m_courant) {
					if (v.isDiscovered()) {
						try {
							v.step(now);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			m_old_courant = now;
		}

		if (now - m_old_tree > Options.PC_SPEED * 4) {
			for (int i = 0; i < m_virus.size(); i++) {
				Virus v = m_virus.get(i);
				if (v.m_courant != m_courant) {
					if (v.isDiscovered()) {
						try {
							v.step(now);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			m_old_tree = now;
		}

		m_joueur.update(now);
		try {
			m_joueur.step(now);
		} catch (Exception e) {
			e.printStackTrace();
		}

		pourcentageDefaite();
		if(m_pourcentage_defaite >= 100) {
			this.m_defaite=true;
		}
	}

	@Override
	public void shutdown() {
		System.exit(0);
	}
	
	public void replay() {
		m_virus=new LinkedList<Virus>();
		m_corbeille=new Noeud(this, "Corbeille");
		m_joueur= new ClinkNorm(null,this,3,3);
		m_tree=new Tree(this);
		m_courant=m_tree.m_root;
		m_joueur.m_courant=m_courant;
		m_defaite=false;
		gameStart=false;
		m_keys=new LinkedList<IKey>();
		m_pourcentage_defaite=0;
		m_nb_fichier_corbeille=0;
		m_victoire=false;
		
		
		
		
	}

}
