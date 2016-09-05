package by.epam.cinemarating.command;

import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements ActionCommand {
	private static final String USER_ID = "user_id";

	private static final String DELETE_USER_STATUS = "deleteUserStatus";

	private static final String SHOW_USER_PAGE = "/controller?command=show_user&user_id=";

	private static final String ERROR_MESSAGE = "Problem in Delete User Command";
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		long userId = Long.valueOf(request.getParameter(USER_ID));
		UserLogic userLogic = new UserLogic();
		try {
			userLogic.deleteUser(userId);
			request.setAttribute(DELETE_USER_STATUS, true);
		} catch (LogicException e) {
			throw new CommandException(ERROR_MESSAGE, e);
		}
		return SHOW_USER_PAGE + userId;
	}
}
