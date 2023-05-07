package com.toolshare.toolshare;

import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FlywayMigration {

    private final Flyway flyway;

    public FlywayMigration(Flyway flyway) {
        this.flyway = flyway;
    }

    @PostConstruct
    public void migrateDatabase() {
        flyway.migrate();
    }
}