package game.shellda;


public class Shellda extends Executable{
	Shellda(Noeud courant, Model model, int x, int y) {
		super(courant, model, x, y, "Shellda");
	}
	

	public void interaction() {
		System.out.println("Bravo tu as gagné gros bg!");
		m_model.shutdown();
	}
}
