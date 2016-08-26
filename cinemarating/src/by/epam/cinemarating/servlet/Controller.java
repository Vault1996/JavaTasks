package by.epam.cinemarating.servlet;

import by.epam.cinemarating.command.ActionCommand;
import by.epam.cinemarating.command.ActionFactory;
import by.epam.cinemarating.command.UnsupportedCommandException;
import by.epam.cinemarating.database.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final String COMMAND = "command";

	private static final Logger LOGGER = LogManager.getLogger(Controller.class);

	public Controller() {
		super();
	}
	@Override
	public void init() throws ServletException {
		super.init();
		LOGGER.info("Initializing servlet.");
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//TODO: ЗАЩИТА ОТ F5; session.lastCommand

		String command = request.getParameter(COMMAND);
		String page = "";
		try {
			ActionCommand actionCommand = ActionFactory.defineCommand(command);
			page = actionCommand.execute(request);
			request.getRequestDispatcher(page).forward(request, response);
		} catch (UnsupportedCommandException e) {
			// TODO: НЕ РАБОТАЕТ
			LOGGER.error(e);
			request.getSession().setAttribute("exception", e.getMessage());
			response.sendError(500, "Unsupported command.\n" + e.getMessage());
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		LOGGER.info("Servlet destroyed.");
		ConnectionPool.getInstance().closePool();
	}
}
