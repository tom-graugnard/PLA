package interpreter;

public class IDirection {
	private enum directions{
		NORD, SUD, EST, OUEST, FRONT, BACK, RIGHT, LEFT
	}

	public directions m_direction;
	
	public IDirection(String direction) {
		set_direction(direction);
	}
	
	public void set_direction(String direction) {
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
	
	// Fonction avec direction absolue
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
	
	public int[] coordonnees() {
		int[] result = new int[2];
		result[0] = 0; //x
		result[1] = 0; //y
		if(nord()) {
			result[1] = -1;
		} else if(sud()) {
			result[1] = 1;
		} else if(est()) {
			result[0] = 1;
		} else if(ouest()) {
			result[0] = -1;
		}
		return result;
	}
	
	public IDirection absolue_front() {
		switch(m_direction) {
		case NORD:
			return new IDirection("N");
		case SUD:
			return new IDirection("S");
		case EST:
			return new IDirection("E");
		case OUEST:
			return new IDirection("O");
		default:
			return new IDirection("F");
		}
	}
	
	public IDirection absolue_back() {
		switch(m_direction) {
		case NORD:
			return new IDirection("S");
		case SUD:
			return new IDirection("N");
		case EST:
			return new IDirection("O");
		case OUEST:
			return new IDirection("E");
		default:
			return new IDirection("B");
		}
	}
	
	public IDirection absolue_right() {
		switch(m_direction) {
		case NORD:
			return new IDirection("E");
		case SUD:
			return new IDirection("O");
		case EST:
			return new IDirection("S");
		case OUEST:
			return new IDirection("N");
		default:
			return new IDirection("R");
		}
	}
	
	public IDirection absolue_left() {
		switch(m_direction) {
		case NORD:
			return new IDirection("O");
		case SUD:
			return new IDirection("E");
		case EST:
			return new IDirection("N");
		case OUEST:
			return new IDirection("S");
		default:
			return new IDirection("L");
		}
	}
	
	// Fonction avec direction relative
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
