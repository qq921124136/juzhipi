package com.example.demo.cache;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("Cache")
public class Cache implements Cacheca{

	@Override
	public void haha() {
		System.out.println("haha()");
	}

}
