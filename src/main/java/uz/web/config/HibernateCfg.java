package uz.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
@PropertySource(value = "classpath:app.properties")
public class HibernateCfg {
    private final Environment environment;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource(
                environment.getRequiredProperty("spring.datasource.url"),
                environment.getRequiredProperty("spring.datasource.username"),
                environment.getRequiredProperty("spring.datasource.password")
        );

        dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean(@Autowired DataSource dataSource){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan("uz.web.domain.entity");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());

        return localSessionFactoryBean;
    }

    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager(@Autowired LocalSessionFactoryBean localSessionFactoryBean) {
        return new HibernateTransactionManager(localSessionFactoryBean.getObject());
    }
}
