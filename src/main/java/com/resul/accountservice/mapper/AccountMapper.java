package com.resul.accountservice.mapper;

import com.resul.accountservice.dto.AccountDto;
import com.resul.accountservice.dto.AccountWithUserDto;
import com.resul.accountservice.dto.CreateAccountDto;
import com.resul.accountservice.entity.AccountEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    List<AccountWithUserDto> toDtoList(List<AccountEntity> accountEntities);
    AccountEntity toAccountEntity(CreateAccountDto createAccountDto);
    AccountDto toAccountDto(AccountEntity accountEntity);
}
