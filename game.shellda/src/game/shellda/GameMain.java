package game.shellda;

import java.awt.Dimension;

import edu.ricm3.game.GameUI;

public class GameMain {

	public static void main(String[] args) {

		// construct the game elements: model, controller, and view.
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model, view);

		Dimension d = new Dimension(1024, 768);
		new GameUI(model, view, controller, d);

		// notice that the main thread will exit here,
		// but not your program... hence the hooking
		// of the window events to System.exit(0) when
		// the window is closed. See class WindowListener.

		/*
		 * *** WARNING *** WARNING *** WARNING *** WARNING *** If you do something here,
		 * on this "main" thread, you will have parallelism and thus race conditions.
		 * 
		 * ONLY FOR ADVANCED DEVELOPERS
		 * 
		 * *** WARNING *** WARNING *** WARNING *** WARNING ***
		 */
		return;
	}
}