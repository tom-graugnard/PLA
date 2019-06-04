package game.shellda;

import java.awt.Dimension;

import edu.ricm3.game.GameUI;

public class GameMain {

	public static void main(String[] args) {

		// construct the game elements: model, controller, and view.
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model, view);

		Dimension d = new Dimension(Options.WIDTH, Options.HEIGHT);
		new GameUI(model, view, controller, d);
		
		return;
	}
}