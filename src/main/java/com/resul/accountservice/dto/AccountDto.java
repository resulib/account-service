package com.resul.accountservice.dto;

import com.resul.accountservice.entity.AccountStatusEnum;
import com.resul.accountservice.entity.CurrencyEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class AccountDto {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private CurrencyEnum currency;
    private AccountStatusEnum accountStatus;
    private Long userId;
}
