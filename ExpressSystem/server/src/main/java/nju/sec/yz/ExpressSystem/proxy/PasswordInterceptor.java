package nju.sec.yz.ExpressSystem.proxy;

import java.lang.reflect.Method;

import nju.sec.yz.ExpressSystem.exception.InvalidPasswordException;

public class PasswordInterceptor implements Interceptor{

	@Override
	public void beforeInvoke(Method method, Object[] args) throws InvalidPasswordException{
		if(args.length<2)
			throw new InvalidPasswordException("Invalid Password");
		if(!args[0].equals(args[1]))
			throw new InvalidPasswordException("Invalid Password");
	}

	@Override
	public void afterInvoke(Method method, Object[] args) throws Exception{
		// TODO Auto-generated method stub
		
	}

}
