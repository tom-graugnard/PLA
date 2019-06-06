package game.shellda;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Virus extends Element {

	int display;

	public Virus(Noeud n, Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale,
			LinkedList<Virus> virus) {
		super(n, model, no, sprite, rows, columns, x, y, scale);
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
	
	public void move() {
		
	}
	
	
	

}
