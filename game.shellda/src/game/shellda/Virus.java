package game.shellda;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Virus extends Element {

	int display;

	public Virus(Noeud courant, Model model, int x, int y) {
		super(courant, model, x, y);
		display = 0;
		model.m_virus.add(this);
		c = Color.magenta;
	}

	void actualiser() {
		display++;
	}

	public void die(LinkedList<Virus> virus) {
		virus.remove(this);
	}

}
