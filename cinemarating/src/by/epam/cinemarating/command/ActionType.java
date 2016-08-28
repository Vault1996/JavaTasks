package by.epam.cinemarating.command;

public enum ActionType {
	LOGIN(new LoginCommand()),
	LOGOUT(new LogoutCommand()),
	REGISTRATION(new RegistrationCommand()),
	CHANGE_LANGUAGE(new ChangeLanguageCommand()),
	REDIRECT(new RedirectCommand()),
	ALL_MOVIES(new AllMoviesCommand()),
	CHANGE_PAGE(new ChangePageCommand());

	private ActionCommand actionCommand;

	ActionType(ActionCommand actionCommand) {
		this.actionCommand = actionCommand;
	}

	public ActionCommand getActionCommand() {
		return actionCommand;
	}
}
