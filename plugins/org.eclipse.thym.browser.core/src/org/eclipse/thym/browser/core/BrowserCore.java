/*******************************************************************************
 * Copyright (c) 2017 Red Hat, Inc. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * 	Contributors:
 * 		 Red Hat Inc. - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.thym.browser.core;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class BrowserCore implements BundleActivator{
	
	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.thym.browser.core"; //$NON-NLS-1$
	
	private static BundleContext context;

	private static ILog logger;

	static BundleContext getContext() {
		return context;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		BrowserCore.context = context;
		logger = Platform.getLog(getContext().getBundle());
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		BrowserCore.context = null;
		
	}
	
	public static void log(int status, String message, Throwable throwable) {
		logger.log(new Status(status, PLUGIN_ID, message, throwable));
	}

}
