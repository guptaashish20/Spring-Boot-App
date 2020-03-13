package com.exam01;

import java.net.InetAddress;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam01.AcmeProperties.Security;

@Service
public class MyService {

	private AcmeProperties properties;

	//@Autowired
	public MyService(AcmeProperties properties) {
		this.properties = properties;
	}
	

	public void readPropertiesValue() {
		Security security = properties.getSecurity();
		InetAddress inetAddress = properties.getRemoteAddress();
		System.out.println(security);
		System.out.println(inetAddress.getHostAddress());
	}
	
	
}
