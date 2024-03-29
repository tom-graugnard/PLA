package game.shellda;

import java.util.Random;

public class Tree {

	Noeud m_root;

	Model m_model;

	public Tree(Model m) {
		m_root = new Noeud(m, "root");
		m_model = m;
		generer_aborescence();
	}

	public void generer_aborescence() {
		Random rand = new Random();
		m_root.generer_noeud(Options.PROFONDEUR_ARBORESCENCE);
		Noeud shellda = m_root;
		int position_shellda = rand.nextInt(2);
		System.out.println("Shellda:");
		while (shellda.m_enfants.size() > position_shellda) {
			System.out.print( " " + shellda.m_name + " ->");
			shellda = shellda.m_enfants.get(rand.nextInt(shellda.m_enfants.size()));
		}
		System.out.println(" " + shellda.m_name);
		int x;
		int y;
		do {
			x = rand.nextInt(Options.LARGEUR_CARTE);
			y = rand.nextInt(Options.HAUTEUR_CARTE);
		} while (!generer_shellda(shellda, x, y));

		Noeud AntiVirus;
		System.out.println("Anti-Virus:");
		String result = "";
		do {
			result = "";
		AntiVirus = m_root;
		int position_antivius = rand.nextInt(2);
		
		while (AntiVirus.m_enfants.size() > position_antivius) {
			result +=" " + AntiVirus.m_name + " ->";
			AntiVirus = AntiVirus.m_enfants.get(rand.nextInt(AntiVirus.m_enfants.size()));
		}
		result += " " + AntiVirus.m_name;
		} while(AntiVirus == shellda);
		System.out.println(result);
		do {
			x = rand.nextInt(Options.LARGEUR_CARTE);
			y = rand.nextInt(Options.HAUTEUR_CARTE);
		} while (AntiVirus.get_element(x, y) != null);
		AntiVirus.set_element(new AntiVirus(AntiVirus, m_model, x, y));

		Noeud decompresseur;
		System.out.println("Decompresseur:");
		do {
			result = "";
		decompresseur = m_root;
		int position_decompresseur = rand.nextInt(m_root.m_enfants.size());
		while (decompresseur.m_enfants.size() > position_decompresseur) {
			result +=  " " + decompresseur.m_name + " ->";
			decompresseur = decompresseur.m_enfants.get(rand.nextInt(decompresseur.m_enfants.size()));
		}
		result += " " + decompresseur.m_name;
		} while(decompresseur == shellda || decompresseur == AntiVirus);
		System.out.println(result);
		do {
			x = rand.nextInt(Options.LARGEUR_CARTE);
			y = rand.nextInt(Options.HAUTEUR_CARTE);
		} while (!generer_decompresseur(decompresseur, x, y));

		m_root.generer_contenue(Options.PROFONDEUR_ARBORESCENCE);
	}

	public boolean generer_shellda(Noeud n, int x, int y) {
		if (x <= 2 || x >= Options.LARGEUR_CARTE - 3)
			return false;
		if (y < 0 || y >= Options.HAUTEUR_CARTE - 3)
			return false;
		int i, j;
		for (i = -1; i <= 1; i++) {
			for (j = -1; j <= 1; j++) {
				if (n.get_element(x + i, y + j) != null)
					return false;
			}
		}
		n.set_element(new Shellda(n, m_model, x, y));

		for (i = -1; i <= 1; i++) {
			for (j = -1; j <= 1; j++) {
				if (n.get_element(x + i, y + j) == null)
					n.set_element(new Archive(n, m_model, x + i, y + j, m_model.m_generator.generate_compressed()));
			}
		}

		return true;
	}

	public boolean generer_decompresseur(Noeud n, int x, int y) {
		if (x <= 2 || x >= Options.LARGEUR_CARTE - 3)
			return false;
		if (y < 0 || y >= Options.HAUTEUR_CARTE - 3)
			return false;
		int i;
		for (i = -1; i <= 1; i++) {
			if (n.get_element(x + i, y) != null)
				return false;
			if (n.get_element(x, y + i) != null)
				return false;
		}
		n.set_element(new Decompresseur(n, m_model, x, y));

		for (i = -1; i <= 1; i++) {
			if (n.get_element(x + i, y) == null)
				n.set_element(new Archive(n, m_model, x + i, y, m_model.m_generator.generate_compressed()));
			if (n.get_element(x, y + i) == null)
				n.set_element(new Archive(n, m_model, x, y + i, m_model.m_generator.generate_compressed()));
		}

		return true;
	}
}
