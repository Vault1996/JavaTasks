package by.epam.cinemarating.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST,
		DispatcherType.FORWARD},
		urlPatterns = { "/*" },
		initParams = {
				@WebInitParam(name = "language", value = "en", description = "Language Param")
		})
public class LanguageFilter implements Filter {
	private static final String LANGUAGE = "language";
	private String language;
	public void init(FilterConfig fConfig) throws ServletException {
		language = fConfig.getInitParameter(LANGUAGE);
	}
	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
// установка языка из параметров фильтра, если не установлен
		if (httpRequest.getSession().getAttribute(LANGUAGE) == null) {
			httpRequest.getSession().setAttribute(LANGUAGE, language);
		}
		chain.doFilter(request, response);
	}
	public void destroy() {
		language = null;
	}
}