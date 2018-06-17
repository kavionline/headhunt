package kavinda.headhunter.web;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
	private static final String TEMP_DIRECTORY = System.getProperty("java.io.tmpdir");

	@Bean(name = "mainDataSource")
	public DataSource createMainDataSource() {
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL("jdbc:h2:" + TEMP_DIRECTORY + "/headhunt;MODE=MySQL");
		return ds;
	}
	
    @Bean
    @Autowired
    @Qualifier("createMainDataSource")
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean
    @Autowired
    @Qualifier("createMainDataSource")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}