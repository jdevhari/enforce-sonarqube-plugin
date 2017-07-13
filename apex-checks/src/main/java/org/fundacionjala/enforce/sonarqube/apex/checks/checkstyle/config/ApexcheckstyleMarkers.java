package org.fundacionjala.enforce.sonarqube.apex.checks.checkstyle.config;

public class ApexcheckstyleMarkers
{
	private Marker[] marker;

	public Marker[] getMarker ()
	{
	return marker;
	}

	public void setMarker(Marker[] marker) {
		this.marker = marker;
	}

	@Override
	public String toString() {
		return "ClassPojo [marker = " + marker + "]";
	}
}
