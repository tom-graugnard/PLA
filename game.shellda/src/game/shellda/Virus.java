package game.shellda;

import java.util.LinkedList;

public class Virus extends Element {

	int display;

	public Virus(Noeud n, int x, int y, LinkedList<Virus> virus) {
		super(n, x, y);
		display = 0;
		virus.add(this);
	}

	void actualiser() {
		display++;
	}

	public void die(LinkedList<Virus> virus) {
		virus.remove(this);
	}

}
