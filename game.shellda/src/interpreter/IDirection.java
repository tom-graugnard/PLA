package interpreter;

public class IDirection {
	private enum directions{
		NORD, SUD, EST, OUEST, FRONT, BACK, RIGHT, LEFT
	}

	public directions m_direction;
	
	public IDirection(String direction) {
		if (direction.equals("N")) {
			m_direction = directions.NORD;
		} else if (direction.equals("S")) {
			m_direction = directions.SUD;
		} else if (direction.equals("E")) {
			m_direction = directions.EST;
		} else if (direction.equals("O")) {
			m_direction = directions.OUEST;
		} else if (direction.equals("F")) {
			m_direction = directions.FRONT;
		} else if (direction.equals("B")) {
			m_direction = directions.BACK;
		} else if (direction.equals("R")) {
			m_direction = directions.RIGHT;
		} else if (direction.equals("L")) {
			m_direction = directions.LEFT;
		}
	}
	
	public boolean absolue() {
		return nord() || sud() || est() || ouest();
	}
	
	public boolean nord() {
		return m_direction == directions.NORD;
	}
	
	public boolean sud() {
		return m_direction == directions.SUD;
	}
	
	public boolean est() {
		return m_direction == directions.EST;
	}
	
	public boolean ouest() {
		return m_direction == directions.OUEST;
	}
	
	public boolean relative() {
		return front() || back() || right() || left();
	}
	
	public boolean front() {
		return m_direction == directions.FRONT;
	}
	
	public boolean back() {
		return m_direction == directions.BACK;
	}
	
	public boolean right() {
		return m_direction == directions.RIGHT;
	}
	
	public boolean left() {
		return m_direction == directions.LEFT;
	}
}
