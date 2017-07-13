package org.fundacionjala.enforce.sonarqube.apex.checks.checkstyle.config;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="apexcheckstyleConfiguration")
public class ApexcheckstyleConfiguration
{
	private String fileExtensions;

	private ApexcheckstyleMarkers apexcheckstyleMarkers;

	public String getFileExtensions ()
    {
        return fileExtensions;
    }

	public void setFileExtensions(String fileExtensions) {
		this.fileExtensions = fileExtensions;
	}

	public ApexcheckstyleMarkers getApexcheckstyleMarkers() {
		return apexcheckstyleMarkers;
	}

	public void setApexcheckstyleMarkers(ApexcheckstyleMarkers ApexcheckstyleMarkers) {
		this.apexcheckstyleMarkers = ApexcheckstyleMarkers;
	}

	@Override
	public String toString() {
		return "ClassPojo [fileExtensions = " + fileExtensions + ", ApexcheckstyleMarkers = " + apexcheckstyleMarkers
				+ "]";
	}
}
