package nju.sec.yz.ExpressSystem.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy implements InvocationHandler{

	private Object target;

	private Interceptor interceptor;
	
	public Proxy(Interceptor interceptor){
		this.interceptor=interceptor;
	}
	
	/**
	 * 绑定委托对象并返回一个代理类
	 * 
	 * @param target
	 * @return
	 */
	public Object bind(Object target) {
		this.target = target;
		// 取得代理对象
		return java.lang.reflect.Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this); // 要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
		Object result = null;
		
		interceptor.beforeInvoke(method, args);
		
		// 执行方法
		result = method.invoke(target, args);
	
		interceptor.afterInvoke(method, args);
	
		return result;
	}

}
