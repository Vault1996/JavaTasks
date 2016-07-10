package by.epam.parsing.action;

import by.epam.parsing.entity.composite.Composite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextWriter {
	private static final Logger LOGGER = LogManager.getLogger(TextWriter.class);

	public void printToFile(String fileName, Composite text) {
		File file = new File(fileName);
		try (FileWriter fileWriter = new FileWriter(file)) {
			text.print(fileWriter);
		} catch (IOException e) {
			LOGGER.error("Can't write in file.");
		}
	}
}
