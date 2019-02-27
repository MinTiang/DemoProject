package shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import junit.framework.Assert;

public class LoginLogoutTest {
	
	@Test
	public void testHelloWorld() {
		//获取SecurityManager工厂，使用配置文件初始化
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
		//得到SecurityManager并赋值给SecurityUtils
		SecurityManager securityManager=factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken("zhang", "123");
		try {
			subject.login(token);
			System.out.println("登录成功");
		}catch (AuthenticationException e) {
			System.out.println("身份验证失败！");
		}
		
		Assert.assertEquals(true, subject.isAuthenticated());
		
		//退出
		subject.logout();
		
		
	}
	
}
