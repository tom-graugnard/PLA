package interpreter;

public class IKey {
	public enum keys{
		UP, DOWN, LEFT, RIGHT, H, W, P
	}
	
	keys m_key;
	
	public IKey(String key) {
		m_key = keys.UP;
	}
	
	public boolean equals(IKey key) {
		return m_key == key.m_key;
	}
}
