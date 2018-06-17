package kavinda.headhunter.web;

import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import kavinda.headhunter.model.Agent;
import kavinda.headhunter.service.RecruitmentService;
import kavinda.headhunter.service.RecruitmentServiceImpl;


@Controller
@SessionAttributes(types = Agent.class)
public class AgentController {

    private final RecruitmentService recruitmentService;

    @Autowired
    public AgentController(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.recruitmentService = new RecruitmentServiceImpl(dataSource, namedParameterJdbcTemplate);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String processFindForm(Agent agent, BindingResult result, Model model) {
        // Load owners
        Collection<Agent> results = this.recruitmentService.loadAgentList();
        model.addAttribute("selections", results);
        return "agents/agentsList";

    }

    @RequestMapping("/agents/{agentId}")
    public ModelAndView showAgent(@PathVariable("agentId") int agentId) {
        ModelAndView mav = new ModelAndView("agents/agentDetails");
        mav.addObject(this.recruitmentService.findAgentById(agentId));
        return mav;
    }

}
