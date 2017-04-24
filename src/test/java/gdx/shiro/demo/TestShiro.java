package gdx.shiro.demo;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestShiro extends TestCase{
	public void testShiro () {
		TestShiroByClassPathFile denglu = new TestShiroByClassPathFile("classpath:Myshiro-Realm.ini");
		denglu.login();
	}
	
	@Test
	public void testShirByRealm () {
		// 1 獲取SecurtyManager工廠，此處使用INI配置文件初始化SceurtyManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:Myshiro-Realm.ini");

		// 2得到securityManager實例 ，并綁定給SecurityUtil
		org.apache.shiro.mgt.SecurityManager securityManager = (SecurityManager) factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3得到subject及創建用戶名/密碼身份驗證token
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("张三", "1234");

		try {
			// 4身份验证、登录
			subject.login(token);
			System.out.println("登录成功!");
		} catch (Exception e) {
			// 验证失败
			e.printStackTrace();
		}
		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录

		// 6、退出
		subject.logout();
	}
	
	@Test
	public void testRoles(){
		//TestShiroByClassPathFile2 account1 = new TestShiroByClassPathFile2("classpath:shiro.ini", "wang", "123");
		TestShiroByClassPathFile2 account2 = new TestShiroByClassPathFile2("classpath:shiro.ini", "zhang", "12345");
		//account1.login();
		account2.login();
		//是否拥有多个角色
		boolean[] role = account2.getSubject().hasRoles(Arrays.asList("teacher","master"));
		//是否拥有单个角色
		boolean role2 = account2.getSubject().hasRole("masr");
		for (boolean b : role) {
			System.out.print("hasRoles:" + b+",");
		}
		System.out.println();
		System.out.println("hasRole:" + role2);
		
		account2.getSubject().checkRole("aaa");
	}
	
	@Test
	public void testPermission(){
		TestShiroByClassPathFile2 account2 = new TestShiroByClassPathFile2("classpath:shiro.ini", "wang", "123");
		account2.login();
		
		boolean b = account2.getSubject().isPermitted("user:delete");
		//boolean b = account2.getSubject().isPermittedAll("user:create","user:update");
		System.out.println(b);
		//System.out.println(b);
		account2.getSubject().logout();
	}

}
