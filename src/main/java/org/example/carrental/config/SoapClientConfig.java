package org.example.carrental.config;

import com.example.currencyconverter.CurrencyConverterService;
import jakarta.xml.ws.BindingProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.namespace.QName;
import java.net.URL;

@Configuration
public class SoapClientConfig {

    @Bean
    public CurrencyConverterService currencyConverterService() throws Exception {
        URL wsdl = new URL("http://localhost:57126/CurrencyConverterService.svc?wsdl");
        QName serviceName = new QName("http://tempuri.org/", "CurrencyConverterService");
        CurrencyConverterService svc = new CurrencyConverterService(wsdl, serviceName);

        // optional: tweak timeouts
        ((BindingProvider) svc
                .getBasicHttpBindingICurrencyConverterService())
                .getRequestContext()
                .put("com.sun.xml.ws.request.timeout", 5000);

        return svc;
    }
}