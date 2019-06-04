package game.shellda;

import java.io.IOException;

public class Test {

	public static void main(String args[]) {
		Model m = new Model();
		
		while(true) {
			for (int i = 0; i < m.m_tree.tous.size(); i++) {
				m.m_tree.tous.get(i).print();
				System.out.println();
			}
			for(int i = 0; i < m.m_virus.size(); i++) {
				m.m_virus.get(i).actualiser();
			}
			try {
				System.in.read();
			} catch (IOException e) {
			}
			System.out.println("------------------------------------------------");
		}
	}
}
