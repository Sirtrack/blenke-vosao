package org.vosao.jsf;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vosao.servlet.MimeType;


public class ConfigBean extends AbstractJSFBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(ConfigBean.class);
	
	private String googleAnalyticsId;
	private String siteEmail;
	private String siteDomain;
	
	public void init() {
		googleAnalyticsId = getBusiness().getConfigBusiness()
				.getGoogleAnalyticsId();
		siteEmail = getBusiness().getConfigBusiness().getSiteEmail();
		siteDomain = getBusiness().getConfigBusiness().getSiteDomain();
	}
	
	public void export() throws IOException {
		log.debug("Exporting site.");
		HttpServletResponse response = JSFUtil.getResponse();
		response.setContentType(MimeType.getContentTypeByExt("zip"));
		String downloadFile = "exportSite.zip";
		response.addHeader("Content-Disposition", "attachment; filename=\"" 
				+ downloadFile + "\"");
		ServletOutputStream out = response.getOutputStream();
		byte[] file = getBusiness().getImportExportBusiness().createExportFile();
		response.setContentLength(file.length);
		out.write(file);
		out.flush();
		out.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	public void save() {
		getBusiness().getConfigBusiness().setGoogleAnalyticsId(
				googleAnalyticsId);
		getBusiness().getConfigBusiness().setSiteEmail(siteEmail);
		getBusiness().getConfigBusiness().setSiteDomain(siteDomain);
	}

	public String getGoogleAnalyticsId() {
		return googleAnalyticsId;
	}

	public void setGoogleAnalyticsId(String googleAnalyticsId) {
		this.googleAnalyticsId = googleAnalyticsId;
	}

	public String getSiteEmail() {
		return siteEmail;
	}

	public void setSiteEmail(String siteEmail) {
		this.siteEmail = siteEmail;
	}

	public String getSiteDomain() {
		return siteDomain;
	}

	public void setSiteDomain(String siteDomain) {
		this.siteDomain = siteDomain;
	}
	
}