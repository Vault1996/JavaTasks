package by.epam.cinemarating.command;

public enum ActionType {
	LOGIN(new LoginCommand()),
	LOGOUT(new LogoutCommand()),
	REGISTRATION(new RegistrationCommand()),
	CHANGE_LANGUAGE(new ChangeLanguageCommand()),
	REDIRECT(new RedirectCommand()),
	ALL_MOVIES(new AllMoviesCommand()),
	CHANGE_PAGE(new ChangePageCommand()),
	ALL_USERS(new AllUsersCommand()),
	SHOW_MOVIE(new ShowMovieCommand()),
	SHOW_USER(new ShowUserCommand()),
	EDIT_PROFILE(new EditProfileCommand()),
	LEAVE_REVIEW(new LeaveReviewCommand()),
	SHOW_MAIN_PAGE(new ShowMainPageCommand()),
	SHOW_BAN(new ShowBanCommand()),
	EDIT_REVIEW(new EditReviewCommand()),
	DELETE_REVIEW(new DeleteReviewCommand()),
	SHOW_EDIT_REVIEW(new ShowEditReviewCommand()),
	SHOW_EDIT_PROFILE(new ShowEditProfileCommand());

	private ActionCommand actionCommand;

	ActionType(ActionCommand actionCommand) {
		this.actionCommand = actionCommand;
	}

	public ActionCommand getActionCommand() {
		return actionCommand;
	}
}
