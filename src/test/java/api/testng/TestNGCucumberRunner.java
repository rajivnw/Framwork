package api.testng;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;

import cucumber.runtime.ClassFinder;
import cucumber.runtime.CucumberException;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.RuntimeOptionsFactory;
import cucumber.runtime.formatter.PluginFactory;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import cucumber.runtime.model.CucumberFeature;
import gherkin.formatter.Formatter;

/**
 * Glue code for running Cucumber via TestNG.
 */
public class TestNGCucumberRunner {
	private Runtime runtime;
	private RuntimeOptions runtimeOptions;
	private ResourceLoader resourceLoader;
	private FeatureResultListener resultListener;
	private ClassLoader classLoader;

	/**
	 * Bootstrap the cucumber runtime
	 *
	 * @param clazz
	 *            Which has the cucumber.api.CucumberOptions and
	 *            org.testng.annotations.Test annotations
	 */
	public TestNGCucumberRunner(Class clazz, ITestContext context) {
		System.out.println("my class TestNGCucumberRunner");
		classLoader = clazz.getClassLoader();
		resourceLoader = new MultiLoader(classLoader);

		RuntimeOptionsFactory runtimeOptionsFactory = new RuntimeOptionsFactory(clazz);
		runtimeOptions = runtimeOptionsFactory.create();

		synchronized (this) {
			// String RESULTS = System.getProperty("buildid");
			// System.out.println("buildid : RESULTS" + RESULTS);
			String RESULTS = "RESULTS";
			File resultDir = new File(RESULTS);
			resultDir.mkdirs();

			PluginFactory pluginFactory = new PluginFactory();

			String[] plugins = {"pretty",
					"html:" + RESULTS + "/" + context.getCurrentXmlTest().getName(),
					"json:" + RESULTS + "/" + context.getCurrentXmlTest().getName()
							+ "/cucumber.json"};
			for (String plugin : plugins) {
				Object pluginObj = pluginFactory.create(plugin.trim());
				this.runtimeOptions.addPlugin(pluginObj);
			}
			System.out.println(
					"feature name" + context.getCurrentXmlTest().getParameter("feature"));

			runtimeOptions.getFeaturePaths()
					.add(context.getCurrentXmlTest().getParameter("feature"));

			runtimeOptions.getGlue()
					.add(context.getCurrentXmlTest().getParameter("glue"));

		}

		ClassFinder classFinder =
				new ResourceLoaderClassFinder(resourceLoader, classLoader);
		resultListener = new FeatureResultListener(runtimeOptions.reporter(classLoader),
				runtimeOptions.isStrict());
		runtime = new Runtime(resourceLoader, classFinder, classLoader, runtimeOptions);
	}

	/**
	 * Run the Cucumber features
	 */
	public void runCukes() {
		for (CucumberFeature cucumberFeature : getFeatures()) {
			cucumberFeature.run(runtimeOptions.formatter(classLoader), resultListener,
					runtime);
		}
		finish();
		if (!resultListener.isPassed()) {
			throw new CucumberException(resultListener.getFirstError());
		}
	}

	public void runCucumber(CucumberFeature cucumberFeature) {
		resultListener.startFeature();
		cucumberFeature.run(runtimeOptions.formatter(classLoader), resultListener,
				runtime);

		if (!resultListener.isPassed()) {
			throw new CucumberException(resultListener.getFirstError());
		}
	}

	public void finish() {
		Formatter formatter = runtimeOptions.formatter(classLoader);

		formatter.done();
		formatter.close();
		runtime.printSummary();
	}

	/**
	 * @return List of detected cucumber features
	 */
	public List<CucumberFeature> getFeatures() {
		return runtimeOptions.cucumberFeatures(resourceLoader);
	}

	/**
	 * @return returns the cucumber features as a two dimensional array of
	 *         {@link CucumberFeatureWrapper} objects.
	 */
	public Object[][] provideFeatures() {
		try {
			List<CucumberFeature> features = getFeatures();
			List<Object[]> featuresList = new ArrayList<Object[]>(features.size());
			for (CucumberFeature feature : features) {
				featuresList.add(new Object[]{new CucumberFeatureWrapperImpl(feature)});
			}
			return featuresList.toArray(new Object[][]{});
		} catch (CucumberException e) {
			return new Object[][]{new Object[]{new CucumberExceptionWrapper(e)}};
		}
	}

}
