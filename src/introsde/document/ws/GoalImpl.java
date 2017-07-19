package src.introsde.document.ws;

import java.util.List;

import javax.jws.WebService;

import src.introsde.document.model.Goal;

@WebService(endpointInterface = "src.introsde.document.ws.Goals",
serviceName="GoalService")
public class GoalImpl implements Goals{
	public List<Goal> getGoals() {
        return Goal.getAll();
    }
    
    public Goal readGoal(int id) {
        System.out.println("---> Reading Goal by id = "+id);
        Goal p = Goal.getGoalById(id);
        if (p!=null) {
            System.out.println("---> Found Person by id = "+id+" => "+p.getLostWeight());
        } else {
            System.out.println("---> Didn't find any Person with  id = "+id);
        }
        return p;
    }
    
    @Override
    public int addGoal (Goal goal) {
    	Goal.saveGoal(goal);
    	return goal.getIdGoal();
    }
    
    public int updateGoal(Goal goal) {
    	Goal.updateGoal(goal);
        return goal.getIdGoal();
    }

    public int deleteGoal(int id) {
    	Goal p = Goal.getGoalById(id);
        if (p!=null) {
        	Goal.removeGoal(p);
            return 0;
        } else {
            return -1;
        }
    }
    
    public Goal getGoalByIdPerson(int idPerson){
    	
    	Goal g= Goal.getAllGoals(idPerson);
    	if (g!=null){return g;}
    	return null;
    	
    }

	
}
