package kavinda.headhunter.service;

import static org.junit.Assert.assertEquals;
import java.util.Collection;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import kavinda.headhunter.model.Agent;
import kavinda.headhunter.service.RecruitmentService;

public abstract class AbstractRecruitmentServiceTests {

    @Autowired
    protected RecruitmentService recruitmentService;

    @Test
    @Transactional
    public void testloadAgentsList() {
        
        Collection<Agent> agents = this.recruitmentService.loadAgentList();
        assertEquals(10, agents.size());
        
        Agent agent = this.recruitmentService.findAgentById(15);
        assertEquals(null, agent);
        
        agent = this.recruitmentService.findAgentById(5);
        assertEquals("nawoditha", agent.getFirstName());
    }
}
