package com.tyss.restdemo.controller;

import com.tyss.restdemo.service.TransactionDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionDemoController {

    private final TransactionDemoService transactionDemoService;

    @PostMapping("/rollback-demo")
    public Map<String, String> rollbackDemo(@RequestParam String departmentName) {
        transactionDemoService.rollbackDemo(departmentName);
        return Map.of("message", "This line is never reached");
    }

    @GetMapping("/read-only-demo")
    public Map<String, Object> readOnlyDemo() {
        return Map.of(
                "message", "Departments fetched inside a read-only transaction",
                "data", transactionDemoService.getDepartmentsReadOnly()
        );
    }

    @PostMapping("/no-rollback-demo")
    public Map<String, String> noRollbackDemo(@RequestParam String departmentName) {
        transactionDemoService.noRollbackDemo(departmentName);
        return Map.of("message", "This line is never reached");
    }
}
