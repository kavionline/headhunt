package kavinda.headhunter.repositiory;

import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import kavinda.headhunter.repository.AgentRepository;
import kavinda.headhunter.repository.jdbc.JdbcAgentRepositoryImpl;
import kavinda.headhunter.web.DataSourceConfig;


@ContextConfiguration(classes = {JdbcAgentRepositoryImpl.class,DataSourceConfig.class})
@RunWith(SpringRunner.class)
public class JdbcAgentRepositoryTests {
	
	
    @Autowired
    protected AgentRepository agentRepository;
	
    
    @Test
    public void calculateAgentFeeString() {
    	
        assertEquals("400.0:0.0:400.0|500.0:0.0:500.0#900.0", agentRepository.calculateAgentFeeString(2, 2));
        
        assertEquals("1000.0:100.0:1100.0|500.0:0.0:500.0#1600.0", agentRepository.calculateAgentFeeString(5, 2));
        
        assertEquals("1400.0:100.0:1500.0|3000.0:250.0:3250.0#4750.0", agentRepository.calculateAgentFeeString(7, 12));
        
        assertEquals("2000.0:200.0:2200.0|500.0:0.0:500.0#2700.0", agentRepository.calculateAgentFeeString(10, 2));
        
        assertEquals("2200.0:200.0:2400.0|3250.0:250.0:3500.0#5900.0", agentRepository.calculateAgentFeeString(11, 13));

    }
}

