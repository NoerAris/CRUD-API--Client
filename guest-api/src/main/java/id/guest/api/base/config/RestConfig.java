package id.guest.api.base.config;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class RestConfig extends RepositoryRestConfigurerAdapter {

    @Autowired
    private WebApplicationContext context;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        Repositories repo = new Repositories(context);
        Iterator iterator = repo.iterator();
        while (iterator.hasNext()) {
            config.exposeIdsFor((Class<?>) iterator.next());
        }
    }
}
