package interpreter;

import game.shellda.Element;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ICondition {

	boolean m_cond;

	public ICondition() {
	}

	boolean eval(Element e) {
		return true;
	}

	// Condition booléenne
	public static class True extends ICondition {

		public True() {
		}

		boolean eval(Element e) {
			return true;
		}
	}

	public static class Key extends ICondition {
		IKey m_key;
		
		public Key(IKey key) {
			m_key = key;
		}

		boolean eval(Element e) {
			return e.Key(m_key);
		}
	}
	
	public static class MyDir extends ICondition {
		IDirection m_direction;
		
		public MyDir(IDirection direction) {
			m_direction = direction;
		}

		boolean eval(Element e) {
			return e.Mydir(m_direction);
		}
	}

	public static class Cell extends ICondition {
		IDirection m_direction;
		IKind m_kind;
		int m_distance;

		public Cell(IDirection direction, IKind kind, int distance) {
			m_direction = direction;
			m_kind = kind;
			m_distance = distance;
		}

		public Cell(IDirection direction, IKind kind) {
			m_direction = direction;
			m_kind = kind;
			m_distance = 1;
		}

		boolean eval(Element e) {
			return e.Cell(m_direction, m_kind, m_distance);
		}
	}
	
	public static class Closest extends ICondition {
		IKind m_kind;
		IDirection m_direction;

		public Closest(IKind kind, IDirection direction) {
			m_kind = kind;
			m_direction = direction;
		}

		boolean eval(Element e) {
			return e.Closest(m_kind, m_direction);
		}
	}

	public static class GotPower extends ICondition {
		public GotPower() {
		}

		boolean eval(Element e) {
			return e.GotPower();
		}
	}
	
	public static class GotStuff extends ICondition {
		public GotStuff() {
		}

		boolean eval(Element e) {
			return e.GotStuff();
		}
	}

	// Opérateur sur condition
	public static class BooleanAnd extends ICondition {
		ICondition m_cond_1;
		ICondition m_cond_2;

		public BooleanAnd(ICondition cond_1, ICondition cond_2) {
			m_cond_1 = cond_1;
			m_cond_2 = cond_2;
		}

		public boolean eval(Element e) {
			return m_cond_1.eval(e) && m_cond_2.eval(e);
		}
	}

	public static class BooleanOr extends ICondition {
		ICondition m_cond_1;
		ICondition m_cond_2;

		public BooleanOr(ICondition cond_1, ICondition cond_2) {
			m_cond_1 = cond_1;
			m_cond_2 = cond_2;
		}

		public boolean eval(Element e) {
			return m_cond_1.eval(e) || m_cond_2.eval(e);
		}
	}

	public static class BooleanNot extends ICondition {
		ICondition m_cond;

		public BooleanNot(ICondition cond) {
			m_cond = cond;
		}

		public boolean eval(Element e) {
			return !m_cond.eval(e);
		}
	}

}