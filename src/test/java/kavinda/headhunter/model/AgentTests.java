package kavinda.headhunter.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;


public class AgentTests {

    @Test
    @Transactional
    public void testHasWorker() {
        Agent agent = new Agent();
        Worker worker = new Worker();
        worker.setFirstName("kavinda");
        worker.setLastName("bandara");
        assertEquals(0, agent.getWorkers().size());
        agent.addWorker(worker);
        assertEquals(worker, agent.getWorkers().get(0));
        assertEquals(1, agent.getWorkers().size());
    }

}
