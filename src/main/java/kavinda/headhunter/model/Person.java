package kavinda.headhunter.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Digits;


@MappedSuperclass
public class Person extends BaseEntity {

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;
    
    @Column(name = "address")
    protected String address;

    @Column(name = "telephone")
    @Digits(fraction = 0, integer = 10)
    protected String telephone;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
