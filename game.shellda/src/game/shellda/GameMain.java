package game.shellda;

import java.awt.Dimension;

import edu.ricm3.game.GameUI;

public class GameMain {

	public static void main(String[] args) {
		Model model;
		// construct the game elements: model, controller, and view.
		if(args.length >= 1) {
			model = new Model(args[0]);
		}
		else {
			model = new Model("shellda.txt");
		}
		
		View view = new View(model);
		Controller controller = new Controller(model, view);

		Dimension d = new Dimension(Options.WIDTH, Options.HEIGHT);
		new GameUI(model, view, controller, d);
		
		return;
	}
}