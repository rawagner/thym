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
package org.eclipse.thym.browser.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.thym.ui.launch.HybridProjectLaunchShortcut;

public class BrowserAction extends HybridProjectLaunchShortcut{

	@Override
	protected boolean validateBuildToolsReady() throws CoreException {
		return true;
	}

	@Override
	protected String getLaunchConfigurationTypeID() {
		return "org.eclipse.thym.browser.core.BrowserLaunchConfigurationType";
	}

	@Override
	protected String getLaunchConfigurationNamePrefix(IProject project) {
		return "browser.launch";
	}

}
