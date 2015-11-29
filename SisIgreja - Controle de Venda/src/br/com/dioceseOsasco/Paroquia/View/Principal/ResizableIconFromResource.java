package br.com.dioceseOsasco.Paroquia.View.Principal;

import java.awt.Dimension;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

public class ResizableIconFromResource {
	
	   public static ResizableIcon getResizableIconFromResource(String resource) {
	        File right = new File(resource);
	        URL url;
	        try {
	            url = right.toURI().toURL();
	        } catch (MalformedURLException e) {            
	            throw new RuntimeException(e);
	        }        
	        return ImageWrapperResizableIcon.getIcon(url, new Dimension(18, 18));
	    }

	    public static ResizableIcon getResizableIconFromResource(URL url) {        
	        return ImageWrapperResizableIcon.getIcon(url, new Dimension(18, 18));
	    } 

}
