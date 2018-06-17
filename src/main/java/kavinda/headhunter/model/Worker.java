package kavinda.headhunter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.core.style.ToStringCreator;


@Entity
@Table(name = "worker")
public class Worker extends Person {

    @Column(name = "speciality")
    private String speciality;
         
    public String getSpeciality() {
        return this.speciality;
    }

    public void setspeciality(String speciality) {
        this.speciality = speciality;
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
                .append("speciality", this.speciality)
                .toString();
        
    }
}
