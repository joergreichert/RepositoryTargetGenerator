package de.abg.jreichert.repositorytarget.transform.popup.actions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Scanner;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Target;

public class TargetDefGenerator extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
 		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object element = structuredSelection.getFirstElement();
			if(element instanceof IFile) {
				IFile file = (IFile) element;
				Scanner scanner = null;
				try {
					InputStream input = file.getContents();
					XMLReader xmlReader = XMLReaderFactory.createXMLReader();
					TargetHandler contentHandler = new TargetHandler(file.getName().replace("." + file.getFileExtension(), ""));
			      	xmlReader.setContentHandler(contentHandler);
			      	xmlReader.parse(new InputSource(input));
			      	Target target = contentHandler.getTarget();
			      	String path = file.getLocation().toFile().getAbsolutePath();
			      	int index = path.lastIndexOf(".target");
			      	if(index > 0) {
			      		path = path.substring(0, index) + ".targetdef";
			      	}
			      	URI uri = URI.createFileURI(path);
			      	XtextResourceSet resourceSet = IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(uri).get(XtextResourceSet.class);
			      	Resource resource = resourceSet.createResource(uri);
			      	resource.getContents().add(target);
			      	resource.save(Collections.emptyMap());
			      	file.getParent().refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
				} catch (CoreException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if(scanner != null) {
						scanner.close();
					}
				}
			}
		}
		
		MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
				"Target To Targetdef Transformator",
				"Transform to targetdef was executed.");
		return null;
	}

}
