package by.epam.cinemarating.command;

public enum ActionType {
	LOGIN(new LoginCommand()),
	LOGOUT(new LogoutCommand()),
	REGISTRATION(new RegistrationCommand()),
	CHANGE_LANGUAGE(new ChangeLanguageCommand());

	private ActionCommand actionCommand;

	ActionType(ActionCommand actionCommand) {
		this.actionCommand = actionCommand;
	}

	public ActionCommand getActionCommand() {
		return actionCommand;
	}
}
