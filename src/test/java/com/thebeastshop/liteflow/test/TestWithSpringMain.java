package com.thebeastshop.liteflow.test;

import com.thebeastshop.liteflow.core.FlowExecutor;
import com.thebeastshop.liteflow.entity.data.Slot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml"})
public class TestWithSpringMain {
	
	@Resource(name="flowExecutor")
	private FlowExecutor flowExecutor;
	
	@Test
	public void test1() throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		for(int i=0;i<1;i++){
			executorService.submit(new Thread(){
				@Override
				public void run() {
					Slot slot = flowExecutor.execute("chain1", "it's a request");
					System.out.println(slot);
				}
			});
		}
		System.out.println("done!");
		System.in.read();
	}
	
	@Test
	public void test2() throws Exception {
		try {
			Slot slot = flowExecutor.execute("chain3", "it's a request");
			System.out.println(slot);
			System.in.read();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test3() throws Exception {
		try {
			while(true) {
				Slot slot = flowExecutor.execute("chain3", "it's a request");
				Thread.sleep(2000);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}


	@Test
	public void test4() throws Exception {
		try {
			Slot slot = flowExecutor.execute("chain2", "it's a request");
			System.out.println("done!");
			System.in.read();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
