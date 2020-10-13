package simpletest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class TestProxy {
	public static void main(String[] args) {
		final List<String> list = new ArrayList<String>();
		List<String> proxyInstance = (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(),
				list.getClass().getInterfaces(), new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						return method.invoke(list, args);
					}
				});
		proxyInstance.add("���");
		
		System.out.println(list);
	}
}
