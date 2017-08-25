package com.rsmore.ejb.service.soap;

import javax.jws.WebService;

@WebService(targetNamespace="http://rsmore.com/wsdl")
public interface CalculatorBeanSoapService {

	int add(int a, int b);
	 
    int subtract(int a, int b);
	
}
