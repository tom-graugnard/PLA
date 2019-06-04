package game.shellda;

import java.awt.Color;
import java.util.LinkedList;

public class Virus extends Element {

	int display;

	public Virus(Noeud n, int x, int y, LinkedList<Virus> virus) {
		super(n, x, y);
		display = 0;
		virus.add(this);
		c = Color.magenta;
	}

	void actualiser() {
		display++;
	}

	public void die(LinkedList<Virus> virus) {
		virus.remove(this);
	}

}
