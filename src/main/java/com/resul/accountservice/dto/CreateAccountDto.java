package com.resul.accountservice.dto;

import com.resul.accountservice.entity.CurrencyEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountDto {
    private CurrencyEnum currency;
}
