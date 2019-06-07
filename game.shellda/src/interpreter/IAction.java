package interpreter;

import game.shellda.Element;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IAction {

	IAction() {
	}

	void exec(Element e) {
	}
	
	public static class Wait extends IAction{
		public Wait() {
			
		}
		
		void exec(Element e) {
			
		}
	}
	
	public static class Wizz extends IAction{
		IDirection m_direction;
		
		public Wizz() {
			m_direction = new IDirection("F");
		}
		
		public Wizz(IDirection direction) {
			m_direction = direction;
		}
		
		void exec(Element e) {
			e.wizz(m_direction);
		}
	}
	
	public static class Pop extends IAction{
		IDirection m_direction;
		
		public Pop() {
			m_direction = new IDirection("F");
		}
		
		public Pop(IDirection direction) {
			m_direction = direction;
		}
		
		void exec(Element e) {
			e.pop(m_direction);

		}
	}
	
	public static class Move extends IAction{
		IDirection m_direction;
		
		public Move() {
			m_direction = new IDirection("F");
		}
		
		public Move(IDirection direction) {
			m_direction = direction;
		}
		
		void exec(Element e) {
			e.move(m_direction);
		}
	}
	
	public static class Jump extends IAction{
		IDirection m_direction;
		
		public Jump() {
			m_direction = new IDirection("F");
		}
		
		public Jump(IDirection direction) {
			m_direction = direction;
		}
		
		void exec(Element e) {
			
		}
	}
	
	public static class Turn extends IAction{
		IDirection m_direction;
		
		public Turn() {
			m_direction = new IDirection("R");
		}
		
		public Turn(IDirection direction) {
			m_direction = direction;
		}
		
		void exec(Element e) {
			
		}
	}
	
	public static class Hit extends IAction{
		IDirection m_direction;
		
		public Hit() {
			m_direction = new IDirection("F");
		}
		
		public Hit(IDirection direction) {
			m_direction = direction;
		}
		
		void exec(Element e) {
			e.hit(m_direction);
		}
	}
	
	public static class Protect extends IAction{
		IDirection m_direction;
		
		public Protect() {
			m_direction = new IDirection("F");
		}
		
		public Protect(IDirection direction) {
			m_direction = direction;
		}
		
		void exec(Element e) {
			
		}
	}
	
	public static class Pick extends IAction{
		IDirection m_direction;
		
		public Pick() {
			m_direction = new IDirection("F");
		}
		
		public Pick(IDirection direction) {
			m_direction = direction;
		}
		
		void exec(Element e) {
			
		}
	}
	
	public static class Throw extends IAction{
		IDirection m_direction;
		
		public Throw() {
			m_direction = new IDirection("F");
		}
		
		public Throw(IDirection direction) {
			m_direction = direction;
		}
		
		void exec(Element e) {
			
		}
	}
	
	public static class Store extends IAction{
		public Store() {
			
		}
		
		void exec(Element e) {
			
		}
	}
	
	public static class Get extends IAction{
		public Get() {
			
		}
		
		void exec(Element e) {
			
		}
	}
	
	public static class Power extends IAction{
		public Power() {
			
		}
		
		void exec(Element e) {
			
		}
	}
	
	public static class Kamikaze extends IAction{
		public Kamikaze() {
			
		}
		
		void exec(Element e) {
			
		}
	}
	
	public static class Egg extends IAction {
		public Egg() {
		}

		void exec(Element e) {
			e.egg();
		}
	}
}
