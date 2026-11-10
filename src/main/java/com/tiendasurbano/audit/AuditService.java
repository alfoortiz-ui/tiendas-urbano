package com.tiendasurbano.audit;

public interface AuditService {
    void record(String actor, String action, String entity, String reference);
}
