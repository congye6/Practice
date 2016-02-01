package nju.sec.yz.ExpressSystem.proxy;

import java.lang.reflect.Method;

public interface Interceptor {

	public void beforeInvoke(Method method,Object[] args) throws Exception;
	
	public void afterInvoke(Method method,Object[] args) throws Exception;
	
}
