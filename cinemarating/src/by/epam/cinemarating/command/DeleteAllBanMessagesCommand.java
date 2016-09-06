package by.epam.cinemarating.command;

import by.epam.cinemarating.logic.BanLogic;
import by.epam.cinemarating.logic.LogicException;

import javax.servlet.http.HttpServletRequest;

public class DeleteAllBanMessagesCommand implements ActionCommand {
	private static final String SHOW_BAN_MESSAGES_COMMAND = "/controller?command=show_ban_messages";
	private static final String ERROR_MESSAGE = "Problem in Delete All Ban Messages Command";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		BanLogic banLogic = new BanLogic();
		try {
			banLogic.deleteAllBanMessages();
		} catch (LogicException e) {
			throw new CommandException(ERROR_MESSAGE, e);
		}
		return SHOW_BAN_MESSAGES_COMMAND;
	}
}
