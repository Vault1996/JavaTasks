package by.epam.cinemarating.command;

public class ActionFactory {
	private static final String ERROR_MESSAGE = "Illegal argument: ";

	public static ActionCommand defineCommand(String command) throws UnsupportedCommandException {
		command = command.toUpperCase();
		try {
			return ActionType.valueOf(command).getActionCommand();
		} catch (IllegalArgumentException e) {
			throw new UnsupportedCommandException(ERROR_MESSAGE + command, e);
		}
	}
}
