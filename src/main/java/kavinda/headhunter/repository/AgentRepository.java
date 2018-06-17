package kavinda.headhunter.repository;

import java.util.Collection;

import kavinda.headhunter.model.Agent;

public interface AgentRepository {

    Agent findById(int id);

    Agent getAgentFeeById(Agent agent);
    
    Collection<Agent> loadAgentList();

	String calculateAgentFeeString(int masonCount, int carpenterCount);
}
