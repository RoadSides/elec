package test;

import org.junit.Test;

public class Demo {
	
	@Test
	public void fun1(){
		String hmac=PaymentUtil.buildHmac("Buy", "10001126856", "123456", "10",
				"CNY", "", "", "", "http://localhost:8080/bookstore/OrderServlet?method=back", "", "", 
				"ICBC-NET-B2C", "1", "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl");
		System.out.println(hmac);
	}
}

