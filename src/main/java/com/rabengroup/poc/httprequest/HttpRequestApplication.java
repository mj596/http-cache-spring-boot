package com.rabengroup.poc.httprequest;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableCaching
public class HttpRequestApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(HttpRequestApplication.class, args);
    }

    @Bean
    public TomcatServletWebServerFactory tomcatEmbeddedServletContainerFactory() {
        TomcatServletWebServerFactory tomcatFactory = new TomcatServletWebServerFactory();

        tomcatFactory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                //tomcat default nio connector
                Http11NioProtocol handler = (Http11NioProtocol)connector.getProtocolHandler();
                //acceptCount is backlog, default value is 100, you can change which you want value in here
                handler.setAcceptCount(1);
            }
        });

        return tomcatFactory;
    }

    @Bean
    public Executor executor() {
        return Executors.newFixedThreadPool(50);
    }

    @Bean
    public ExecutorService executorService() { return Executors.newFixedThreadPool(50); }

    @CacheEvict(allEntries = true, cacheNames = { "BusinessUnitService" })
    @Scheduled(fixedDelay = 20000)
    public void evictCache() {
    }

}

