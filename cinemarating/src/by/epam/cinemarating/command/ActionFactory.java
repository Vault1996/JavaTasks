package by.epam.cinemarating.command;

public class ActionFactory {
	public static ActionCommand defineCommand(String command) throws UnsupportedCommandException {
		command = command.toUpperCase();
		try {
			return ActionType.valueOf(command).getActionCommand();
		} catch (IllegalArgumentException e) {
			//TODO: LOGGER
			throw new UnsupportedCommandException(e);
		}
	}
}
