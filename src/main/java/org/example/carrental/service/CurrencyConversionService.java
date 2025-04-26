package org.example.carrental.service;

import com.example.currencyconverter.CurrencyConverterService;
import com.example.currencyconverter.ICurrencyConverterService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService {

    private final ICurrencyConverterService port;

    public CurrencyConversionService(CurrencyConverterService soapSvc) {
        this.port = soapSvc.getBasicHttpBindingICurrencyConverterService();
    }

    public Double getExchangeRate(String fromCurrency,
                                  String toCurrency,
                                  Double amount) {
        // Match the stub signature exactly:
        return port.convertCurrency(fromCurrency, toCurrency, amount);
    }
}
