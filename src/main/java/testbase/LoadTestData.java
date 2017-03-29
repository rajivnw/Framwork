package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class LoadTestData {

	private static Properties properties = new Properties();

	public void loadProperties(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				loadProperties(fileEntry);
			} else {
				System.out.println(fileEntry.getName());
				try {
					File file = new File(
							"src/test/resources/prop/" + fileEntry.getName() + "");
					FileInputStream fileInput = new FileInputStream(file);

					properties.load(fileInput);
					fileInput.close();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getKey(String key) {

		Enumeration enuKeys = properties.keys();
		while (enuKeys.hasMoreElements()) {
			String keyValue = (String) enuKeys.nextElement();
			if (keyValue.equalsIgnoreCase(key)) {
				return properties.getProperty(keyValue);
			}
			// String value = properties.getProperty(key);
			// System.out.println(key + ": " + value);
		}
		return null;
	}
}
