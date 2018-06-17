package kavinda.headhunter.service;

import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import kavinda.headhunter.model.Agent;
import kavinda.headhunter.repository.AgentRepository;
import kavinda.headhunter.repository.jdbc.JdbcAgentRepositoryImpl;


@Service
public class RecruitmentServiceImpl implements RecruitmentService {

    private AgentRepository agentRepository;

    @Autowired
    public RecruitmentServiceImpl(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.agentRepository = new JdbcAgentRepositoryImpl(dataSource, namedParameterJdbcTemplate);
    }

    @Override
    public Agent findAgentById(int id){
        return agentRepository.findById(id);
    }

	@Override
	public Collection<Agent> loadAgentList(){
		return agentRepository.loadAgentList();
	}

}
