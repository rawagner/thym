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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate2;
import org.eclipse.thym.core.HybridProject;
import org.eclipse.thym.core.HybridProjectLaunchConfigConstants;
import org.eclipse.thym.core.internal.cordova.CordovaCLI;
import org.eclipse.thym.core.internal.cordova.CordovaCLIStreamListener;
import org.eclipse.thym.core.internal.cordova.CordovaCLI.Command;

public class BrowserLaunchDelegate implements ILaunchConfigurationDelegate2 {

	@Override
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor)
			throws CoreException {
		IProject project = getProject(configuration);
		HybridProject hybridProject = HybridProject.getHybridProject(project);
		CordovaCLI cordova = CordovaCLI.newCLIforProject(hybridProject);
		CordovaCLIStreamListener streamListener = new CordovaCLIStreamListener();
		cordova.run(monitor, streamListener, "browser");
	}

	@Override
	public ILaunch getLaunch(ILaunchConfiguration configuration, String mode) throws CoreException {
		return null;
	}

	@Override
	public boolean buildForLaunch(ILaunchConfiguration configuration, String mode, IProgressMonitor monitor)
			throws CoreException {
		IProject project = getProject(configuration);
		HybridProject hybridProject = HybridProject.getHybridProject(project);
		if (hybridProject.getActiveEngineForPlatform("browser") == null) {
			CordovaCLI cordova = CordovaCLI.newCLIforProject(hybridProject);
			cordova.platform(Command.ADD, monitor, "browser");
		}
		monitor.done();
		return true;
	}

	@Override
	public boolean finalLaunchCheck(ILaunchConfiguration configuration, String mode, IProgressMonitor monitor)
			throws CoreException {
		monitor.done();
		return true;
	}

	@Override
	public boolean preLaunchCheck(ILaunchConfiguration configuration, String mode, IProgressMonitor monitor)
			throws CoreException {
		monitor.done();
		return true;
	}

	// TODO: duplicated form IOSLaunchDelegate... move both to a common utility.
	private IProject getProject(ILaunchConfiguration configuration) {
		try {
			String projectName = configuration.getAttribute(HybridProjectLaunchConfigConstants.ATTR_BUILD_SCOPE,
					(String) null);
			if (projectName != null) {
				return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
			}
		} catch (CoreException e) {
			return null;
		}
		return null;
	}
}
