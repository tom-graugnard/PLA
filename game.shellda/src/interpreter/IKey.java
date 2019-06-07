package interpreter;

public class IKey {
	
	public String m_key;
	
	public IKey(String key) {
		m_key = key;
	}
	
	public boolean equals(IKey key) {
		return m_key.equals(key.m_key);
	}
	
	public String toString() {
		return m_key;
	}
}
