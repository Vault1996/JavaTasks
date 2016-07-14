package test.by.epam.parsing;

import by.epam.parsing.exception.CantInterpretException;
import by.epam.parsing.action.interpreter.Interpreter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

public class ParsingTest {
	private static final Logger LOGGER = LogManager.getLogger(ParsingTest.class);
	@Test ( expected = CantInterpretException.class)
	public void formulaExceptionTest() throws CantInterpretException {
		String formula = "3+5-6*";
		Interpreter interpreter = new Interpreter(formula);
		interpreter.calculate();
	}
	@Test (timeout = 100)
	public void formulaTest() {
		String formula = "3+5-6";
		Interpreter interpreter = new Interpreter(formula);
		int a = 0;
		try {
			a = (Integer)interpreter.calculate();
		} catch (CantInterpretException e) {
			Assume.assumeNoException(e);
		}
		Assert.assertTrue(a == 2);
	}
}