package com.security.spring_app.enums;

import org.apache.logging.log4j.util.Strings;

public enum AuditoryTypes {
    SUCCESS("SUCCESS"),
    EXCEPTION("EXCEPTION");

    private String text;

    AuditoryTypes(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
};
