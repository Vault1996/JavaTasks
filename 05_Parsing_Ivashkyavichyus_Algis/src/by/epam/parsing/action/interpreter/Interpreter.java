package by.epam.parsing.action.interpreter;

import by.epam.parsing.action.interpreter.exception.CantInterpretException;
import by.epam.parsing.main.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Interpreter {
	private static final Logger LOGGER = LogManager.getLogger(Main.class);
	private ArrayList<AbstractMathExpression> listExpression;
	public Interpreter(String expression) {
		listExpression = new ArrayList<>();
		parse(toPolishForm(expression));
	}
	private void parse(String expression) { // синтаксический анализ
		for (String lexeme : expression.split("\\p{Blank}+")) {
			if (lexeme.isEmpty()) {
				continue;
			}
			char temp = lexeme.charAt(0);
			switch (temp) {
				case '+':
					listExpression.add(new TerminalExpressionPlus());
					break;
				case '-':
					listExpression.add(new TerminalExpressionMinus());
					break;
				case '*':
					listExpression.add(new TerminalExpressionMultiply());
					break;
				case '/':
					listExpression.add(new TerminalExpressionDivide());
					break;
				default:
					Scanner scan = new Scanner(lexeme);
					if (scan.hasNextInt()) {
						listExpression.add(
								new NonTerminalExpressionNumber(scan.nextInt()));
					}
			}
		}
	}
	private int priority(String ch){
		switch (ch){
			case "(":
				return 0;
			case "+": case "-":
				return 1;
			case "*": case "/":
				return 2;
			case ")":
				return 5;
		}
		return 0;
	}

	private String toPolishForm(String expression){
		String result = "";
		ArrayDeque<String> operators = new ArrayDeque<>();
//*********************************
		if (expression.contains("++")) {
			int index = expression.indexOf("++");
			if (index < expression.length() - 2 && expression.charAt(index + 2) <= '9' &&
					expression.charAt(index + 2) >= '0') {
				expression = expression.replace("++", "1+");
			} else if (index > 0 && expression.charAt(index - 1) <= '9' &&
					expression.charAt(index - 1) >= '0') {
				expression = expression.replace("++", "+1");
			}

		}
		if (expression.contains("--")) {
			int index = expression.indexOf("--");
			if (index < expression.length() - 2 && expression.charAt(index + 2) <= '9' &&
					expression.charAt(index + 2) >= '0') {
				expression = expression.replace("--", "-1+");
			} else if (index > 0 && expression.charAt(index - 1) <= '9' &&
					expression.charAt(index - 1) >= '0') {
				expression = expression.replace("--", "-1");
			}

		}
		if(!expression.isEmpty()){
			if (expression.charAt(0) == '+' || expression.charAt(0) == '-') {
				expression = "0" + expression;
			}
		}
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '(' && (expression.charAt(i+1) == '-' || expression.charAt(i+1) == '+')) {
				String buff1 = expression, buff2 = expression;
				expression = buff1.substring(0, i + 1) + "0" + buff2.substring(i + 1, expression.length());
			}
		}
		//*****************

		StringTokenizer stringTokenizer = new StringTokenizer(expression, "/+*-()", true);
		while (stringTokenizer.hasMoreTokens()) {
			String lexeme = stringTokenizer.nextToken();
			switch (lexeme) {
				case "+":
					if (operators.isEmpty()) {
						operators.addLast("+");
					} else {
						while (!operators.isEmpty() && priority(operators.peekLast()) >= priority("+")) {
							result += operators.pollLast() + " ";
						}
						operators.addLast("+");
					}
					break;
				case "-":
					if (operators.isEmpty()) {
						operators.addLast("-");
					} else {
						while (!operators.isEmpty() && priority(operators.peekLast()) >= priority("-")) {
							result += operators.pollLast() + " ";
						}
						operators.addLast("-");
					}
					break;
				case "*":
					if (operators.isEmpty()) {
						operators.addLast("*");
					} else {
						while (!operators.isEmpty() && priority(operators.peekLast()) >= priority("*")) {
							result += operators.pollLast() + " ";
						}
						operators.addLast("*");
					}
					break;
				case "/":
					if (operators.isEmpty()) {
						operators.addLast("/");
					} else {
						while (!operators.isEmpty() && priority(operators.peekLast()) >= priority("/")) {
							result += operators.pollLast() + " ";
						}
						operators.addLast("/");
					}
					break;
				case "(":
					operators.addLast(lexeme);
					break;
				case ")":
					while (!operators.isEmpty() && !"(".equals(operators.peekLast())) {
						result += operators.pollLast() + " ";
					}
					if(operators.isEmpty()){
						LOGGER.error("Can't interpret formula.");
					}
					operators.pollLast();
					break;
				default:
					result += lexeme + " ";
			}
		}
		while (!operators.isEmpty()) {
			result += operators.pollLast() + " ";
		}
		return result;
	}
	public Number calculate() throws CantInterpretException{
		Context context = new Context();
// выполнение простых задач и сборка результата
		for (AbstractMathExpression terminal : listExpression) {
			terminal.interpret(context);
		}
		return context.popValue();
	}
}
