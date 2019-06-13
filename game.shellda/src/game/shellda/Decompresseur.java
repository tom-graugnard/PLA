package game.shellda;


public class Decompresseur extends Executable{
	
	Decompresseur(Noeud courant, Model model, int x, int y) {
		super(courant, model, x, y, "Dezippeur");
	}
	

	public void interaction() {
		int i = 0;
		int j = 0;
		for(i = 0; i < Options.LARGEUR_CARTE; i++) {
			for(j = 0; j < Options.HAUTEUR_CARTE; j++) {
				if(m_courant.get_element(i, j) instanceof Archive) {
					Archive a = (Archive) m_courant.m_carte[i][j];
					m_courant.m_carte[i][j] = a.decompression();
				}
			}
		}
	}
}
