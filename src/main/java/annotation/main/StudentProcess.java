package annotation.main;

import java.lang.reflect.Method;

import annotation.annotation.Description;
import annotation.annotation.Name;

public class StudentProcess {
	public static void main(String[] args) throws ClassNotFoundException {
		String CLASS_NAME="annotation.entity.Student";
		Class<?> studentc=Class.forName(CLASS_NAME);
		
		Method[] methods=studentc.getMethods();
		
		boolean flag=studentc.isAnnotationPresent(Description.class);//判断student类是否包含Description注解
		if(flag) {
			Description description=studentc.getAnnotation(Description.class);//得到注解并且打印出注解的内容
			System.out.println("类描述："+description.value());
			System.out.println("---------------------------");
		}
		
		for(int i=0 ;i<methods.length;i++) {//遍历类方法找到有Name注解的方法，得到注解并打印出注解中的值
			Method m=methods[i];
			if(m.isAnnotationPresent(Name.class)) {
				Name name=m.getAnnotation(Name.class);
				System.out.println(name.className()+":"+name.name());
			}
		}
	}
}
