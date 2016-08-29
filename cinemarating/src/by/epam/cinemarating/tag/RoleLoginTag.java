package by.epam.cinemarating.tag;

import by.epam.cinemarating.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class RoleLoginTag extends TagSupport{
	private static final String SEPARATOR = " : ";

	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			if (user != null) {
				pageContext.getOut().write(user.getRole() + SEPARATOR + user.getLogin());
			}
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}
}
