package by.it_academy.fitness.web.controllers;


import by.it_academy.fitness.core.dto.MessageAudit;
import by.it_academy.fitness.service.api.IAuditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/audit/send")
public class AuditControllerClosed {
    private final IAuditService auditService;

    public AuditControllerClosed(IAuditService auditService) {
            this.auditService = auditService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody MessageAudit messageAudit){
        auditService.save(messageAudit);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
