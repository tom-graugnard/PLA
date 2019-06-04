package interpreter;

import java.util.LinkedList;
import java.util.List;

import game.shellda.Element;
import interpreter.IAction.Hit;
import interpreter.IAction.Move;

public class Test_Automate {
	
	public static void main(String args[]) throws Exception{
		
		Element e= new Element(null, null, 0, null, 0, 0, 0, 0, 0);
		
		IState s1= new IState("un"); s1.id=1;
		IState s2 = new IState("deux"); s2.id=2;
		
		
		List<IBehaviour> b=new LinkedList<IBehaviour>();	
		
		List<ITransition> t1 = new LinkedList<ITransition>();		
		Hit hit=new Hit(Direction.NORTH);	
		ICondition con = new ICondition();
		ITransition t_tmp1=new ITransition(con, hit, s2);
		t1.add(t_tmp1);
		
		IBehaviour b_tmp1=new IBehaviour(s1,t1);
		
		b.add(b_tmp1);
		
				
		List<ITransition> t2 = new LinkedList<ITransition>();		
		Move move=new Move(Direction.NORTH);		
		ITransition t_tmp2=new ITransition(con, move, s1);
		t2.add(t_tmp2);
				
		IBehaviour b_tmp2=new IBehaviour(s2,t2);
		
		b.add(b_tmp2);
		
		IAutomaton auto=new IAutomaton(s1, b);
		for(int i=0;i<10;i++) {
			System.out.println(auto.current.name);
			auto.step(e);
			//System.out.print(e.x);
			
		}
		
		
	}
}
