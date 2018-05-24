package proxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 实现InvocationHandler
 * @author mintiang
 *
 */
public class MyHandler implements InvocationHandler {

	private Object object;
	
	public MyHandler(Object o) {
		super();
		this.object = o;
	}
	/**
	 * 这里不能直接使用proxy要不然会陷入死循环，难道只能用构造传对象吗！
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		before(proxy,method,args);
		Object o=method.invoke(object, args);
		after();
		return o;
	}
	private void before(Object proxy, Method method, Object[] args) {
		System.out.println("开始调用:"+proxy.getClass()+":"+method.getName()+",参数为:"+Arrays.toString(args));
	}
	
	private void after() {
		System.out.println("调用结束");
	}
}
