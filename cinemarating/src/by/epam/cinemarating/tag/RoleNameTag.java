package by.epam.cinemarating.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class RoleNameTag extends TagSupport{
	private static final String SEPARATOR = " : ";

	private String role;
	private String name;
	public void setRole(String role) {
		this.role = role;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			if (!role.isEmpty()) {
				pageContext.getOut().write(role + SEPARATOR + name);
			}
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}
}
