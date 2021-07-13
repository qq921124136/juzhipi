package com.example.demo;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Component{}

@Component
public class Test {
	static Map<String,Object> beanPool=new HashMap<>();
	public static void main(String[] args) throws Exception {
		Class<?> cls=Test.class;
		String pk=cls.getPackage().getName();
		System.out.println(pk);
		String replace = pk.replace(".", "/");
		System.out.println(replace);
		URL url=ClassLoader.getSystemResource(replace);
		System.out.println(url);
		File file = new File(url.getPath());
		String[] classNames = 
		file.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO 自动生成的方法存根
				return name.endsWith(".class");
			}
		});
		for(String className:classNames) {
			String classShortName = 
			className.substring(0,className.indexOf("."));
			System.out.println(classShortName);
			Class<?> clsObject=ClassLoader.getSystemClassLoader().loadClass(pk+"."+classShortName);
			System.out.println(clsObject.getName());
			boolean flag = clsObject.isAnnotationPresent(Component.class);
			Annotation an = clsObject.getAnnotation(Component.class);
			if(an==null)continue;
			Object instance=clsObject.newInstance();//底层通过构造方法对象
			beanPool.put(classShortName.substring(0,1).toLowerCase()+classShortName.substring(1), instance);
		}
		//7.输出 beanPool 中的对象
		System.out.println(beanPool);
		}
}
