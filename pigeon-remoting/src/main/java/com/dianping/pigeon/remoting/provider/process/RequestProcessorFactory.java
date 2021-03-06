/**
 * Dianping.com Inc.
 * Copyright (c) 2003-2013 All Rights Reserved.
 */
package com.dianping.pigeon.remoting.provider.process;

import com.dianping.pigeon.config.ConfigManagerLoader;
import com.dianping.pigeon.remoting.common.util.Constants;
import com.dianping.pigeon.remoting.provider.config.ServerConfig;
import com.dianping.pigeon.remoting.provider.process.threadpool.RequestThreadPoolProcessor;

public class RequestProcessorFactory {

	public static final String PROCESS_TYPE = ConfigManagerLoader.getConfigManager().getStringValue(
			"pigeon.provider.processmodel", Constants.PROCESS_MODEL_THREAD);

	public static RequestProcessor selectProcessor(ServerConfig serverConfig) {
		return new RequestThreadPoolProcessor(serverConfig);
	}
}
