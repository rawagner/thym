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

import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.thym.browser.internal.core.Messages;
import org.eclipse.thym.core.HybridCore;
import org.eclipse.thym.core.engine.HybridMobileLibraryResolver;

public class BrowserLibraryResolver extends HybridMobileLibraryResolver {
	
	public static final String CORDOVA_BROWSER = "cordova-browser";

	@Override
	public URL getTemplateFile(IPath destination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStatus isLibraryConsistent() {
		if (version != null) {
			String name = getLibraryName();
			if(name != null && name.equals(CORDOVA_BROWSER)){
				return Status.OK_STATUS;
			}
		}
		return new Status(IStatus.ERROR, HybridCore.PLUGIN_ID, Messages.BrowserLibraryResolver_NotCompatibleError);
	}

	@Override
	public void preCompile(IProgressMonitor monitor) throws CoreException {
		
	}

	@Override
	public boolean needsPreCompilation() {
		return false;
	}

	@Override
	public String getDefiningJsonFile() {
		return "package.json";
	}

}
