package annotation.entity;

import annotation.annotation.Description;
import annotation.annotation.Name;

@Description("这是一个学生类")
public class Student {
	
	@Name(name="mintiang",className="一年级二班")
	public void getName() {
		
	}
	@Name(name="张三",className="一年级一班")
	public void getName2() {
		
	}

	
}
