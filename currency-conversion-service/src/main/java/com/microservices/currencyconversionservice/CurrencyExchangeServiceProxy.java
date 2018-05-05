package com.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// URL should be http://localhost:8765/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/10000  
//if we want to first go to zuul and then conversion and then again zuul and again exchange service

//URL should be http://localhost:8765/currency-exchange-service/currency-exchange/from/AUD/to/INR   if we want to intercept zuul 
//directly to exchange service through zuul

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
//@FeignClient(name = "currency-exchange-service")
@RibbonClient(name = "currency-exchange-service")
@FeignClient(name = "netflix-zuul-api-gateway-server")
public interface CurrencyExchangeServiceProxy {
    
	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
    @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
	
	
	//OR
	/*@RequestMapping(method = RequestMethod.GET,value = "/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);*/
	
	
}
