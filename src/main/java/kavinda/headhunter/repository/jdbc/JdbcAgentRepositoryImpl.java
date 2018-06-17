package kavinda.headhunter.repository.jdbc;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.stereotype.Repository;

import kavinda.headhunter.model.Agent;
import kavinda.headhunter.model.Worker;
import kavinda.headhunter.repository.AgentRepository;


@Repository
public class JdbcAgentRepositoryImpl implements AgentRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public JdbcAgentRepositoryImpl(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }
    
    @Override
    public Collection<Agent> loadAgentList(){
    	return  this.namedParameterJdbcTemplate.query(
                "SELECT id, first_name, last_name, address, telephone FROM agents",
                BeanPropertyRowMapper.newInstance(Agent.class)
        );
    }

    @Override
    public Agent findById(int id) {
        Agent agent = null;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            agent = this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT id, first_name, last_name, address, telephone FROM agents WHERE id= :id",
                    params,
                    BeanPropertyRowMapper.newInstance(Agent.class)
            );
            
        } catch (Exception ex) {ex.getMessage();}
        if(agent!=null) {
            loadAgentAndRecruitment(agent);
        }
        return agent;
    }

    public void loadAgentAndRecruitment(final Agent agent) {
        Map<String, Object> params = new HashMap<>();
        params.put("aid", agent.getId());
        final List<JdbcWorker> workers = this.namedParameterJdbcTemplate.query(
                "SELECT w.id, w.first_name,w.last_name,w.address,w.telephone,w.speciality, r.join_date"
                + " FROM workers w, recruitments r WHERE w.id=r.worker_id AND r.agent_id=:aid AND r.join_date >= DATEADD(month, -1, GETDATE()) "
                + " ORDER BY r.join_date desc,w.speciality",
                params,
                new JdbcWorkerRowMapper()
        );
        
        for (JdbcWorker worker : workers) {
        	agent.addWorker(worker);
        }
        getAgentFeeById(agent);
    }

	@Override
	public Agent getAgentFeeById(Agent agent) {	
		int masonCount= 0;
		int carpenterCount= 0;
		
        for (Worker worker : agent.getWorkers()) {
        	if(worker.getSpeciality().equals("Mason")) {
        		masonCount++;
        	}else if (worker.getSpeciality().equals("Carpenter")) {
        		carpenterCount++;
        	}
        }

        agent.setFee(calculateAgentFeeString(masonCount,carpenterCount));
		return agent;
	}
	
	@Override
	public String calculateAgentFeeString(int masonCount, int carpenterCount) {
		final double masonvalue=200;
		final double carpentervalue=250;
		double fee = 0.00;
		StringBuilder feeString = new StringBuilder();
        if(masonCount!=0) {
        	int masongroups = masonCount/5;
        	double masonFee = masonvalue*masonCount;
        	double masonBonus = masongroups*0.1*masonvalue*5;
        	double masonTotal = masonFee+masonBonus;
        	fee+=masonTotal;
        	feeString.append(masonFee+":"+masonBonus+":"+masonTotal);
        }else {feeString.append("0.00:0.00:0.00");}
        feeString.append("|");
        if(carpenterCount!=0) {
        	int carpentergroups = carpenterCount/5;
        	double carpenterFee = carpentervalue*carpenterCount;
        	double carpenterBonus = carpentergroups*0.1*carpentervalue*5;
        	double carpenterTotal = carpenterFee+carpenterBonus;
        	fee+=carpenterTotal;
        	feeString.append(carpenterFee+":"+carpenterBonus+":"+carpenterTotal);
        }else {feeString.append("0.00:0.00:0.00");}
        feeString.append("#"+fee);
		return feeString.toString();
	}

}
