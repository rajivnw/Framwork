package testbase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

public class GenerateReport {
	public static void GenerateMasterthoughtReport() {
		try {
			File reportOutputDirectory = new File("target/Masterthought");
			List<String> jsonFiles = new ArrayList<String>();
			jsonFiles.add(
					"E:\\AWS\\GitCommit\\TestCommit\\RESULTS\\compareEPS\\cucumber.json");
			jsonFiles.add(
					"E:\\AWS\\GitCommit\\TestCommit\\RESULTS\\retrieveData\\cucumber.json");
			jsonFiles.add(
					"E:\\AWS\\GitCommit\\TestCommit\\RESULTS\\searchcmp\\cucumber.json");

			String buildNumber = "1";
			String projectName = "cucumberProject";
			boolean runWithJenkins = false;
			boolean parallelTesting = false;

			Configuration configuration =
					new Configuration(reportOutputDirectory, projectName);
			// optional configuration
			configuration.setParallelTesting(parallelTesting);
			configuration.setRunWithJenkins(runWithJenkins);
			configuration.setBuildNumber(buildNumber);
			// addidtional metadata presented on main page
			configuration.addClassifications("Platform", "Windows");
			configuration.addClassifications("Browser", "Firefox");
			configuration.addClassifications("Branch", "release/1.0");

			ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
			Reportable result = reportBuilder.generateReports();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
