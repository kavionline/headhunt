package kavinda.headhunter.repository.jdbc;

import java.util.Date;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import kavinda.headhunter.model.Worker;

class JdbcWorker extends Worker {
    
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date joinDate;   

    public void setJoinDate(Date joindate) {
        this.joinDate = joindate;
    }

    public Date getJoinDate() {
        return this.joinDate;
    }
}
