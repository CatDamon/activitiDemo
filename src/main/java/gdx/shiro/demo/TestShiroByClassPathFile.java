package gdx.shiro.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import junit.framework.Assert;


public class TestShiroByClassPathFile {
	
	private String path;
	public TestShiroByClassPathFile(String path){
		this.path = path;
	}
	public void login(){
		//1 獲取SecurtyManager工廠，此處使用INI配置文件初始化SceurtyManager
		Factory<SecurityManager> factory =  
	            new IniSecurityManagerFactory(path);  
		
		//2得到securityManager實例 ，并綁定給SecurityUtil
		org.apache.shiro.mgt.SecurityManager securityManager = (SecurityManager) factory.getInstance();  
	    SecurityUtils.setSecurityManager(securityManager);
	    
	    //3得到subject及創建用戶名/密碼身份驗證token
	    Subject subject = SecurityUtils.getSubject();
	    UsernamePasswordToken token = new UsernamePasswordToken("wa","123");
	    
	    try {
	    	//4身份验证、登录
		    subject.login(token);
		    System.out.println("登录成功!");
		} catch (Exception e) {
			//验证失败
			e.printStackTrace();
		}
	    Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录  
	    
	    //6、退出  
	    subject.logout();  
	}
	
}

