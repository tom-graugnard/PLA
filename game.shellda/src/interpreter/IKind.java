package interpreter;

public class IKind {
	public String m_type;
	
	public IKind(String type) {
		m_type = type;
	}
	
	public boolean equals(IKind kind) {
		if(m_type.equals("_") || kind.m_type.equals("_"))
			return true;
		return m_type.equals(kind.m_type);
	}
}
