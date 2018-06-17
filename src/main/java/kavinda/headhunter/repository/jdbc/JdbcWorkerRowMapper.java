package kavinda.headhunter.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

class JdbcWorkerRowMapper implements RowMapper<JdbcWorker> {

    @Override
    public JdbcWorker mapRow(ResultSet rs, int rownum) throws SQLException {
    	JdbcWorker worker = new JdbcWorker();
    	worker.setId(rs.getInt("id"));
    	worker.setFirstName(rs.getString("first_name"));
    	worker.setLastName(rs.getString("last_name"));
    	worker.setAddress(rs.getString("address"));
    	worker.setTelephone(rs.getString("telephone"));
    	worker.setspeciality(rs.getString("speciality"));
    	Date joindate = rs.getDate("join_date");
    	worker.setJoinDate(joindate);
        return worker;
    }
}