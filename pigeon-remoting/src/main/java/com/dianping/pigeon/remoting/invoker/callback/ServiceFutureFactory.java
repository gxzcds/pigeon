/**
 * 
 */
package com.dianping.pigeon.remoting.invoker.callback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.logging.log4j.Logger;

import com.dianping.pigeon.log.LoggerLoader;

/**
 * <p>
 * Title: ServiceFutureFactory.java
 * </p>
 * <p>
 * Description: 描述
 * </p>
 * 
 * @author saber miao
 * @version 1.0
 * @created 2011-3-22 上午09:45:27
 */
public class ServiceFutureFactory {

	private static Logger log = LoggerLoader.getLogger(ServiceFutureFactory.class);
	private static ThreadLocal<Future<?>> threadFuture = new ThreadLocal<Future<?>>();

	public static Future<?> getFuture() {
		Future<?> future = threadFuture.get();
		threadFuture.remove();
		return future;
	}

	public static <T> Future<T> getFuture(Class<T> type) {
		Future<T> future = (Future<T>) threadFuture.get();
		threadFuture.remove();
		return future;
	}

	public static void setFuture(Future<?> future) {
		threadFuture.set(future);
	}

	public static void remove() {
		threadFuture.remove();
	}

	/**
	 * 直接返回调用结果，用于异步调用配置情况下的同步调用
	 * 
	 * @param <T>
	 *            返回值类型
	 * @param res
	 *            返回值类
	 * @return 调用结果
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static <T> T getResult(Class<T> res) throws InterruptedException, ExecutionException {
		return (T) getFuture().get();
	}

	/**
	 * 直接返回调用结果，用于异步调用配置情况下的同步调用
	 * 
	 * @return 调用结果
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static Object getResult() throws InterruptedException, ExecutionException {
		return getFuture().get();
	}

}
