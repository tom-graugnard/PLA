package interpreter;

import game.shellda.Element;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IAction {

	IAction() {
	}

	void exec(Element e) {
	}

	public class Egg extends IAction {
		Egg() {
		}

		void exec(Element e) {
			e.egg();
		}
	}

	public static class Hit extends IAction {
		Direction direction;

		public Hit(Direction direction) {
			this.direction = direction;
		}

		void exec(Element e) {
			e.hit(this.direction);
		}
	}

	public static class Pop extends IAction {
		Element cible;
		public Pop(Element cible) {
			this.cible=cible;
		}

		void exec(Element e) {
			e.pop(this.cible);
		}
	}

	public static class Wizz extends IAction {
		public Wizz() {
		}

		void exec(Element e) {
			e.wizz();
		}
	}

	public static class Move extends IAction {
		Direction direction;

		public Move(Direction direction) {
			this.direction = direction;
		}

		void exec(Element e) {
			e.move(this.direction);
		}
	}



}
