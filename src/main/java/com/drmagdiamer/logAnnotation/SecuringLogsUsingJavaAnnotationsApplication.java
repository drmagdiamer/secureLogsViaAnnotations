package com.drmagdiamer.logAnnotation;

import com.drmagdiamer.logAnnotation.secureLogAnnotation.core.CreditCardMasker;
import com.drmagdiamer.logAnnotation.secureLogAnnotation.core.DtoLogSecurity;
import com.drmagdiamer.logAnnotation.secureLogAnnotation.core.SSNMasker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Formating reference: https://medium.com/@nirav-shah/code-formatting-using-spotless-google-java-format-for-java-maven-projects-with-git-pre-commit-860d6c15bf7c

@SpringBootApplication
public class SecuringLogsUsingJavaAnnotationsApplication {

	public static void main(String[] args) {
				SpringApplication app = new SpringApplication(SecuringLogsUsingJavaAnnotationsApplication.class);
				app.setWebApplicationType(WebApplicationType.REACTIVE);
				app.run(args);
				initLogLibrary();
	}

	private static void initLogLibrary() {
		DtoLogSecurity.addToMapLibrary("ssnMasker", new SSNMasker());
		DtoLogSecurity.addToMapLibrary("ccMasker", new CreditCardMasker());
	}

}
