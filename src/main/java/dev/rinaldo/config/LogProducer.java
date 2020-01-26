package dev.rinaldo.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class LogProducer {

    @Produces
    public Logger produceLog(InjectionPoint ip) {
        return produceLog(ip.getMember().getDeclaringClass());
    }
    
    public static Logger produceLog(Class<?> declaringClass) {
        return produceLog(declaringClass.getName());
    }
    
    public static Logger produceLog(String className) {
        return LoggerFactory.getLogger(className);
    }
    
}
