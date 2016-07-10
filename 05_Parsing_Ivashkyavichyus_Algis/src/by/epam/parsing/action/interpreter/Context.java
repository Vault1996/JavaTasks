package by.epam.parsing.action.interpreter;

import by.epam.parsing.action.interpreter.exception.CantInterpretException;

import java.util.ArrayDeque;
public class Context {
	private ArrayDeque<Integer> contextValues = new ArrayDeque<>();
	public Integer popValue() throws CantInterpretException{
		if(contextValues.isEmpty()){
			throw new CantInterpretException();
		}
		return contextValues.pop();
	}
	public void pushValue(Integer value) {
		this.contextValues.push(value);
	}
}
