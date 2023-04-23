package by.it_academy.fitness.web.controllers;


import by.it_academy.fitness.core.dto.Audit;
import by.it_academy.fitness.core.dto.Page;
import by.it_academy.fitness.service.api.IAuditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/audit")
public class AuditController {
    private final IAuditService auditService;

    public AuditController(IAuditService auditService) {
            this.auditService = auditService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page> getPageAudits(@RequestParam (name = "page", defaultValue = "0") Integer page,
                                              @RequestParam (name = "size", defaultValue = "20") Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(auditService.getPageAudits(page, size));
    }

    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<Audit> getAudit(@PathVariable("uuid") UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(auditService.getAudit(uuid));
    }
}
