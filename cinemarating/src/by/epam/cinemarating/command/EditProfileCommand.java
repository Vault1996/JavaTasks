package by.epam.cinemarating.command;

import by.epam.cinemarating.entity.User;
import by.epam.cinemarating.hash.MD5Hash;
import by.epam.cinemarating.logic.EditProfileLogic;
import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.memento.Caretaker;
import by.epam.cinemarating.memento.MementoRequestAttributes;
import by.epam.cinemarating.resource.ConfigurationManager;
import by.epam.cinemarating.validation.EditProfileValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;


public class EditProfileCommand implements ActionCommand {
	private static final String PAGE_PROFILE = "path.page.profile";
	private static final String PAGE_EDIT_PROFILE = "path.page.editProfile";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String PHOTO = "photo";
	private static final String PASSWORD = "password";
	private static final String ACTIVE_USER = "activeUser";
	private static final String USER = "user";
	private static final String MEMENTO = "memento";

	@Override
	public String execute(HttpServletRequest request) throws CommandException, IOException, ServletException {
		String name = request.getParameter(NAME);
		String surname = request.getParameter(SURNAME);
		String password = request.getParameter(PASSWORD);

		Part photoPart = request.getPart(PHOTO);
		String fileName = Paths.get(photoPart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.


		MementoRequestAttributes memento = (MementoRequestAttributes) request.getSession().getAttribute(MEMENTO);
		Caretaker caretaker = new Caretaker(memento);
		caretaker.fillRequest(request);

		EditProfileValidator editProfileValidator = new EditProfileValidator();

		if (editProfileValidator.validate(name, surname, password)) {
			try {
				User user = (User) request.getAttribute(USER);
				User activeUser = (User) request.getSession().getAttribute(ACTIVE_USER);
				if (name != null && !name.isEmpty()) {
					user.setName(name);
					activeUser.setName(name);
				}
				if (surname != null && !surname.isEmpty()) {
					user.setSurname(surname);
					activeUser.setSurname(surname);
				}
				if (password != null && !password.isEmpty()) {
					user.setPassword(new MD5Hash().hexHash(password));
				}
				if (photoPart.getSize() != 0) {
					user.setPhoto("images/user/" + fileName);
					System.out.println(request.getContextPath());
					photoPart.write(request.getContextPath() + "/images/user/" + fileName);
				}
				EditProfileLogic editProfileLogic = new EditProfileLogic();
				editProfileLogic.updateProfile(user);
				request.setAttribute(USER, user);
				request.getSession().setAttribute(ACTIVE_USER, activeUser);
				return ConfigurationManager.getProperty(PAGE_PROFILE);
			} catch (LogicException e) {
				throw new CommandException(e);
			}
		} else {
			request.setAttribute("invalidDataMessage", "Invalid data");
			return ConfigurationManager.getProperty(PAGE_EDIT_PROFILE);
		}

	}
}
