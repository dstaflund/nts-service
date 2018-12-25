package com.github.dstaflund.nts.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.github.dstaflund.nts.search.NtsMap;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@WebListener
public class SessionFactoryListener implements ServletContextListener {
    private static final String sConfigFile = "hibernate.cfg.xml";
    private static SessionFactory sSessionFactory;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(sConfigFile)
                .build();
        MetadataSources sources = new MetadataSources(registry)
                .addAnnotatedClass(NtsMap.class);
        Metadata metadata = sources.getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .applyImplicitSchemaName("public")
                .build();

        sSessionFactory = metadata.buildSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (sSessionFactory == null || sSessionFactory.isClosed()) return;
        sSessionFactory.close();
    }

    public static SessionFactory getSessionFactory(){
        return sSessionFactory;
    }
}
