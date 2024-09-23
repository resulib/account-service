package com.resul.accountservice.controller;

import com.resul.accountservice.dto.AccountWithUserDto;
import com.resul.accountservice.dto.CreateAccountDto;
import com.resul.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<AccountWithUserDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.findAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<AccountWithUserDto> findAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.findAccountById(id));
    }


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateAccountDto createAccountDto){
        accountService.create(createAccountDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
