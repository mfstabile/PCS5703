package jia;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;

import java.util.ArrayList;
import java.util.List;

import model.Entity;
import model.graph.Graph;
import model.graph.Vertex;
import arch.MartianArch;
import arch.WorldModel;

/**
 * Returns true or false indicating if the agents is probably part of the teams zone.
 * Checks if the agents has at least one coworker within distance < 3.
 * </p>
 * Use: jia.is_on_team_zone;</br>
 * 
 * @author mafranko
 */
public class is_on_team_zone_old extends DefaultInternalAction {

	private static final long serialVersionUID = 1168992507523306792L;

	@Override
	public Object execute(TransitionSystem ts, Unifier un, Term[] terms) throws Exception {
		WorldModel model = ((MartianArch) ts.getUserAgArch()).getModel();
		Graph graph = model.getGraph();

		Vertex myPosition = model.getMyVertex();
		
		// verify that exists at least one coworker within distance < 3
		List<Entity> coworkers = new ArrayList<Entity> (model.getCoworkers().values());
		for (Entity agent : coworkers) {
			Vertex vertex = agent.getVertex();
			if (vertex != null) {
				int dist = graph.getDistance(myPosition, vertex);
				if (dist < 3) {
					return true;
				}
			}
			
		}
		return false;
	}
}
