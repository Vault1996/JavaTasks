/*package by.epam.cinemarating.filter;

import by.epam.cinemarating.entity.Ban;
import by.epam.cinemarating.entity.User;
import by.epam.cinemarating.logic.BanLogic;
import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.resource.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = { "/controller" },
		servletNames = { "Controller" })
public class BanFilter {
	private static final String ACTIVE_USER = "activeUser";
	private static final String USER_ID = "userId";
	private static final String PAGE_BAN = "path.page.ban";

	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute(ACTIVE_USER);
		if (user != null) {
			try {
				BanLogic banLogic = new BanLogic();
				Optional<Ban> ban = banLogic.findBan(user.getUserId());
				if (ban.isPresent()) {
					request.setAttribute(USER_ID, user.getUserId());
					request.getRequestDispatcher(ConfigurationManager.getProperty(PAGE_BAN)).forward(request, response);
				}
			} catch (LogicException e) {
				//TODO:LOGGER
			}
		}
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}
}*/
