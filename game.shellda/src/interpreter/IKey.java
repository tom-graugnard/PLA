package interpreter;

public class IKey {
	
	String m_key;
	
	public IKey(String key) {
		m_key = key;
	}
	
	public boolean equals(IKey key) {
		return m_key.equals(key.m_key);
	}
}
