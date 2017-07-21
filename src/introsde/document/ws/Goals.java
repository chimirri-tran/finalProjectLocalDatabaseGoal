package introsde.document.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import introsde.document.model.Goal;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL) // optional
public interface  Goals {
	
	// Method #7
		@WebMethod(operationName = "createGoal")
		@WebResult(name = "message")
		public int addGoal(@WebParam(name = "goal") Goal goal);

		// Method #8
		@WebMethod(operationName = "readGoalList")
		@WebResult(name = "goal")
		public List<Goal> getGoals();

		// Method #9
		@WebMethod(operationName = "readGoal")
		@WebResult(name = "goal")
		public Goal readGoal(@WebParam(name = "idGoal") int id);
		
		// Method #10
		@WebMethod(operationName = "updateGoal")
		@WebResult(name = "goalId")
		public int updateGoal(@WebParam(name = "goal") Goal goal);
		
		@WebMethod(operationName = "deleteGoal")
		@WebResult(name = "message")
		public int deleteGoal(@WebParam(name = "goalId") int id);
		
		@WebMethod(operationName = "getGoalByIdPerson")
		@WebResult(name = "message")
		public Goal getGoalByIdPerson(@WebParam(name = "idPerson") int id);

}
