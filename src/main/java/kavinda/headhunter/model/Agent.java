package kavinda.headhunter.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "agents")
public class Agent extends Person {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agent")
    private Set<Worker> workers;
    
    private String feeString;
    
	protected void setWorkersInternal(Set<Worker> workers) {
        this.workers = workers;
    }

    protected Set<Worker> getWorkersInternal() {
        if (this.workers == null) {
            this.workers = new HashSet<>();
        }
        return this.workers;
    }

    public List<Worker> getWorkers() {
        List<Worker> sortedWorkers = new ArrayList<>(getWorkersInternal());
        PropertyComparator.sort(sortedWorkers, new MutableSortDefinition("id", true, true));
        return Collections.unmodifiableList(sortedWorkers);
    }
    
    public String getFeeString() {
		return feeString;
	}

	public void setFee(String feeString) {
		this.feeString = feeString;
	}
    
    public void addWorker(Worker worker) {
        getWorkersInternal().add(worker);
    }
    
    
    
    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("new", this.isNew())
                .append("lastName", this.getLastName())
                .append("firstName", this.getFirstName())
                .append("address", this.address)
                .append("telephone", this.telephone)
                .toString();
    }
}
