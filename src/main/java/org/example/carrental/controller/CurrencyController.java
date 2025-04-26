package org.example.carrental.controller;

import org.example.carrental.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @GetMapping("/currencies/{fromCurrency}/{toCurrency}/{amount}")
    public Double getCurrencyConversion(
            @PathVariable String fromCurrency,
            @PathVariable String toCurrency,
            @PathVariable Double amount) {
        return currencyConversionService.getExchangeRate(fromCurrency, toCurrency, amount);
    }
}
