package es.manu.holamundo.cdi;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationScopedDependency {

    private final Long timestamp;

    public ApplicationScopedDependency() {
        this.timestamp = System.currentTimeMillis();
    }

    Long getTimestamp() {
        return timestamp;
    }

}
