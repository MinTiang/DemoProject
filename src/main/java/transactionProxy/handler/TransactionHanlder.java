package transactionProxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;

import transactionProxy.connecManager.ConnectionManager;

public class TransactionHanlder implements InvocationHandler {
	
	private Object o;
	
	
	public TransactionHanlder(Object o) {
		super();
		this.o = o;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object obj=null;
		if(method.getName().startsWith("add")
				||method.getName().startsWith("save")
				||method.getName().startsWith("update")
				||method.getName().startsWith("del")
				||method.getName().startsWith("delete")) {
			Connection conn=ConnectionManager.getConnection();
			try {
				ConnectionManager.beginTransction(conn);
				obj=method.invoke(o, args);
				ConnectionManager.endTransction(conn);
			}catch (Exception e) {
				ConnectionManager.rollback(conn);
				if(e instanceof InvocationTargetException) {
					InvocationTargetException ine=(InvocationTargetException) e;
					throw ine;
				}else {
					throw new Exception("操作失败");
				}
			}finally {
				ConnectionManager.close();
			}
		}else {
			obj=method.invoke(o, args);
		}
		return obj;
	}

}
