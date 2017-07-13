package org.fundacionjala.enforce.sonarqube.apex.checks.checkstyle;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.fundacionjala.enforce.sonarqube.apex.checks.checkstyle.config.ApexcheckstyleConfiguration;
import org.fundacionjala.enforce.sonarqube.apex.checks.checkstyle.config.Marker;
import org.sonar.check.Rule;
import org.sonar.squidbridge.checks.SquidCheck;
import org.sonar.sslr.parser.LexerlessGrammar;

import com.google.common.io.Files;
import com.sonar.sslr.api.AstNode;

@Rule(key = SfCheckStyle.CHECK_KEY)
public class SfCheckStyle extends SquidCheck<LexerlessGrammar> {

	public static final String CHECK_KEY = "A1030";

	ApexcheckstyleConfiguration config2 = new ApexcheckstyleConfiguration();

	private Charset charset = Charset.forName("UTF-8");

	@Override
	public void init() {
		try {
			File file = new File(
					"src\\main\\java\\org\\fundacionjala\\enforce\\sonarqube\\apex\\checks\\checkstyle\\apexcheckstyle-config.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ApexcheckstyleConfiguration.class);
			Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();
			config2 = (ApexcheckstyleConfiguration) jaxbMarshaller.unmarshal(file);
			System.out.println(config2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visitFile(AstNode astNode) {
		List<String> lines;
		try {
			lines = Files.readLines(getContext().getFile(), charset);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		for (int i = 0; i < lines.size(); ++i) {
			for (Marker marker : config2.getApexcheckstyleMarkers().getMarker()) {
				Matcher matcher = Pattern.compile(marker.getRegex()).matcher(lines.get(i));
				if (compare(false, matcher.find())) {
					String message = "Styling issue[" + marker.getMessage() + "] Sample Fix: "
							+ marker.getReplacement();
					getContext().createLineViolation(this, message, i + 1);
				}
			}
		}
	}
	private boolean compare(boolean invert, boolean condition) {
	    return invert ? !condition : condition;
	  }

}
