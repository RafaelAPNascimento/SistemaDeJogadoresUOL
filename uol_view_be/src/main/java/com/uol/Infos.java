package com.uol;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//para teste
@RestController("/")
public class Infos {

	@Autowired
	Environment env;
	
	@RequestMapping("/infos")
	public String hello() throws UnknownHostException {
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E ',' dd 'de' MMMM 'de' yyyy ', 'H:m:ss",
				new Locale("pt", "BR"));
		
		String host = InetAddress.getLocalHost().getHostName();
		String port = env.getProperty("local.server.port");
		
		return "Rafael<br/><h1>"+ dtf.format(now) +"<br>From host "+host+"<br>From port: "+ port +"</h1>";
	}
}
