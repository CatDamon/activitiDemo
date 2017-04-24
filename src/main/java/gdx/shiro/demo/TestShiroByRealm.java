package gdx.shiro.demo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class TestShiroByRealm implements Realm{
	//根据认证获取token信息
		public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
			String name = (String) token.getPrincipal();
			String password =String.valueOf(token.getCredentials());
			
			//校验账号密码
			if(!"张三".equals(name)){
				throw new UnknownAccountException("用户名错误");
			}
			
			if(!"1234".equals(password)){
				throw new IncorrectCredentialsException("密码错误");
			}
			//如果身份认证验证成功，返回一个AuthenticationInfo实现；  
	        return new SimpleAuthenticationInfo(name, password, getName());
		}

		//返回一个唯一的realm名字
		public String getName() {
			// TODO Auto-generated method stub
			return "gdxRealm";
		}

		//判断此realm是否支持token
		public boolean supports(AuthenticationToken token) {
			//仅支持UsernamePasswordToken类型的Token  
	        return token instanceof UsernamePasswordToken;
		}
}
