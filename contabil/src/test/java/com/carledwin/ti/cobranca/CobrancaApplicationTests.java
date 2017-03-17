package com.carledwin.ti.cobranca;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.carledwin.ti.contabil.ContabilApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ContabilApplication.class)
@WebAppConfiguration
public class CobrancaApplicationTests {

	@Test
	public void contextLoads() {
	}

}
