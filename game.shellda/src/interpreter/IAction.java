package interpreter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IAction {
	
	IAction(){}
	void exec(Entity e){}
	
	public class Egg extends IAction{
		Egg(){
		}
		void exec(Entity e) {
			e.egg();
		}
	}
	
	public static class Hit extends IAction {
		Direction direction ;
		
		Hit(Direction direction){
			this.direction = direction ;
		}
		
		void exec(Entity e){
			e.hit(this.direction);
		}
	}
	
	public class Pop extends IAction{
		Pop(){
			
		}
		void exec(Entity e) {
			e.pop();
		}
	}
	
	public class Wizz extends IAction{
		Wizz(){
			
		}
		void exec(Entity e) {
			e.wizz();
		}
	}
	
	
	
	public static class Move extends IAction {
		Direction direction ;
		
		Move(Direction direction){
			this.direction = direction ;
		}
		
		void exec(Entity e){
			e.move(this.direction) ;
		}
	}
	
}
