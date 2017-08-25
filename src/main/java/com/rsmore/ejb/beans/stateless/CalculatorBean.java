package com.rsmore.ejb.beans.stateless;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rsmore.ejb.service.remote.RemoteCalculator;
import com.rsmore.ejb.service.soap.CalculatorBeanSoapService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@WebService(
		portName="CalculatorBeanPort",
		serviceName="CalculatorBeanService",
		targetNamespace="http://rsmore.com/wsdl",
		endpointInterface="com.rsmore.ejb.service.soap.CalculatorBeanSoapService"
		)
public class CalculatorBean implements RemoteCalculator, CalculatorBeanSoapService {

	private static final Logger logger = LoggerFactory.getLogger(CalculatorBean.class);
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public int add(int a, int b) {
		logger.info("Called add method. Calculating " + a + " + " + b + ".");
		
		return a + b;
	}

	@Override
	public int subtract(int a, int b) {
		return a - b;
	}

}
