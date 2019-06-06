package interpreter;

public class IDirection {
	public enum point_cardinal {
		NORD, SUD, EST, OUEST
	};

	private point_cardinal m_direction;

	public IDirection(String direction) {
		if (direction.equals("N")) {
			m_direction = point_cardinal.NORD;
		} else if (direction.equals("S")) {
			m_direction = point_cardinal.SUD;
		} else if (direction.equals("E")) {
			m_direction = point_cardinal.EST;
		} else if (direction.equals("O")) {
			m_direction = point_cardinal.OUEST;
		}
	}
	
	public boolean nord() {
		return m_direction == point_cardinal.NORD;
	}
	
	public boolean sud() {
		return m_direction == point_cardinal.SUD;
	}
	
	public boolean est() {
		return m_direction == point_cardinal.EST;
	}
	
	public boolean ouest() {
		return m_direction == point_cardinal.OUEST;
	}
}
