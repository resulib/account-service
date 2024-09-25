package com.resul.accountservice.service;

import com.resul.accountservice.dto.AccountDto;
import com.resul.accountservice.dto.AccountWithUserDto;
import com.resul.accountservice.dto.CreateAccountDto;
import com.resul.accountservice.entity.AccountEntity;
import com.resul.accountservice.entity.AccountStatusEnum;
import com.resul.accountservice.manager.AccountManager;
import com.resul.accountservice.mapper.AccountMapper;
import com.resul.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountManager accountManager;
    private final AccountMapper accountMapper;
    private final UserServiceClient userServiceClient;

    public List<AccountWithUserDto> findAll() {
        var accountList = accountRepository.findAll();
        List<AccountWithUserDto> accountWithUserDtoList;
        accountWithUserDtoList = accountList.stream()
                .map(accountEntity -> new AccountWithUserDto(accountMapper.toAccountDto(accountEntity), userServiceClient.getUserById(accountEntity.getUserId())))
                .toList();
        return accountWithUserDtoList;
    }

    public AccountDto findById(Long id) {
        var accountEntity = accountManager.getAccountEntity(id);
        return accountMapper.toAccountDto(accountEntity);
    }

    public void create(CreateAccountDto createAccountDto) {
        var userDto = accountManager.getUser();
        var accountEntity = accountMapper.toAccountEntity(createAccountDto);
        accountEntity.setAccountStatus(AccountStatusEnum.ACTIVE);
        accountEntity.setBalance(BigDecimal.valueOf(0));
        accountEntity.setUserId(userDto.getId());
        accountEntity.setAccountNumber(UUID.randomUUID().toString());
        accountRepository.save(accountEntity);
    }

    public void updateBalance(Long id, BigDecimal amount) {
        AccountEntity accountEntity = accountManager.getAccountEntity(id);
        accountEntity.setBalance(accountEntity.getBalance().add(amount));
        accountRepository.save(accountEntity);
    }

//public AccountWithUserDto findAccountById(Long id) {
//    var userDto = accountManager.getUser();
//    var accountEntity = accountManager.getAccountEntity(id);
//
//    var accountWithUserDto = new AccountWithUserDto();
//    accountWithUserDto.setAccountDto(accountMapper.toAccountDto(accountEntity));
//    accountWithUserDto.setUserDto(userDto);
//    return accountWithUserDto;
//}


}
