package org.jboss.pressgangccms.client.local.constants;

import org.jboss.pressgangccms.client.local.resources.ImageResources;
import org.jboss.pressgangccms.client.local.strings.PressGangCCMSUI;

import com.google.gwt.core.client.GWT;

public class Constants 
{
	public static final ImageResources resources = GWT.create(ImageResources.class);
	public static final PressGangCCMSUI pressGangCCMSUI = GWT.create(PressGangCCMSUI.class);
	
	public static final String BASE_URL = "http://skynet-dev.usersys.redhat.com:8080/TopicIndex/";
	public static final String REST_SERVER = BASE_URL + "seam/resource/rest";
	public static final String BUGZILLA_URL = "https://bugzilla.redhat.com/enter_bug.cgi?product=PressGang CCMS&component=Web-UI";
}
