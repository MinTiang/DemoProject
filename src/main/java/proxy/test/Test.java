package proxy.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import proxy.entity.Person;
import proxy.entity.Student;
import proxy.handler.MyHandler;

public class Test {
	public static void main(String[] args) {
		InvocationHandler handler=new MyHandler(new Student());
		
		Person s=(Person) Proxy.newProxyInstance(Student.class.getClassLoader(), new Class[] {Person.class}, handler);
		s.setName("mintiang");
		System.out.println(s.getName());
	}
}
