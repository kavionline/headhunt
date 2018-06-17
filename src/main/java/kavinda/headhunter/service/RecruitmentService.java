package kavinda.headhunter.service;

import java.util.Collection;

import kavinda.headhunter.model.Agent;


public interface RecruitmentService {

    public Agent findAgentById(int id);
    
    Collection<Agent> loadAgentList();

}
