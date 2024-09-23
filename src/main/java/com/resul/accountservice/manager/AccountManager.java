package com.resul.accountservice.manager;

import com.resul.accountservice.auth.JwtService;
import com.resul.accountservice.dto.UserDto;
import com.resul.accountservice.entity.AccountEntity;
import com.resul.accountservice.exception.AccountNotFoundException;
import com.resul.accountservice.repository.AccountRepository;
import com.resul.accountservice.service.UserServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountManager {
    private final JwtService jwtService;
    private final UserServiceClient userServiceClient;
    private final AccountRepository accountRepository;

    public UserDto getUser() {
        return userServiceClient.getUserByUsername(jwtService.getUserFromToken().getUsername());
    }

    public AccountEntity getAccountEntity(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with Id: " + id));
    }
}
