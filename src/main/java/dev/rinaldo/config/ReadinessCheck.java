package dev.rinaldo.config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import dev.rinaldo.dao.FruitsDAO;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

    @Inject
    FruitsDAO fruitsDAO;

    @Override
    public HealthCheckResponse call() {
        try {
            fruitsDAO.findById(1L);
            return HealthCheckResponse.up("Application is ready.");
        } catch (Exception e) {
            return HealthCheckResponse
                    .named("Application is not ready")
                    .down()
                    .withData("error", e.getMessage())
                    .build();
        }
    }
}