package by.it_academy.fitness.service.api;

import by.it_academy.fitness.core.audit.Audit;

public interface IAuditService {
    public void send (Audit audit);
}
