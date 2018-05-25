package transactionProxy.test;


import java.lang.reflect.Proxy;
import java.sql.SQLException;

import transactionProxy.entity.Student;
import transactionProxy.handler.TransactionHanlder;
import transactionProxy.service.StudentService;
import transactionProxy.service.StudentServiceImpl;

public class Test {
	public static void main(String[] args) throws SQLException {
		TransactionHanlder handler=new TransactionHanlder(new StudentServiceImpl());
		
		StudentService service=(StudentService) Proxy.newProxyInstance(StudentServiceImpl.class.getClassLoader(), StudentServiceImpl.class.getInterfaces(), handler);
		Student student=new Student();
		student.setName("mintiang");
		student.setSex("女");
		
		
//		service.save(student);
		
		
		student.setId(1);
		student.setSex("男2");
		service.update(student);
		
		
		System.out.println(service.query(1));
	}
}
