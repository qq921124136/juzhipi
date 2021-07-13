package com.example.demo.cache;

import org.springframework.stereotype.Component;

@Component("Cache1")
public class Cache1 implements Cacheca{

	@Override
	public void haha() {
	System.out.println("haha1()");
	}

}
