package dev.rinaldo.config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import dev.rinaldo.dao.FrutasDAO;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

    @Inject
    FrutasDAO fruitsDAO;

    @Override
    public HealthCheckResponse call() {
        try {
            fruitsDAO.findById(1L);
            return HealthCheckResponse.up("Aplicação está pronta.");
        } catch (Exception e) {
            return HealthCheckResponse
                    .named("Aplicação não está pronta.")
                    .down()
                    .withData("erro", e.getMessage())
                    .build();
        }
    }
}