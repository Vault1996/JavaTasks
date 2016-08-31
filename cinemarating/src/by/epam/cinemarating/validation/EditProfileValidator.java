package by.epam.cinemarating.validation;

public class EditProfileValidator {
	private static final int MAX_NAME_LENGTH = 30;
	private static final int MAX_SURNAME_LENGTH = 30;
	private static final int MAX_PASSWORD_LENGTH = 20;
	private static final int MIN_PASSWORD_LENGTH = 4;
	private static final int MIN_NAME_LENGTH = 1;
	private static final int MIN_SURNAME_LENGTH = 1;

	public boolean validate(String name, String surname, String password) {
		boolean isValid = true;
		if (name != null && !name.isEmpty()) {
			if (name.length() > MAX_NAME_LENGTH || name.length() < MIN_NAME_LENGTH) {
				isValid = false;
			}
		}
		if (surname != null && !surname.isEmpty()) {
			if (surname.length() > MAX_SURNAME_LENGTH || surname.length() < MIN_SURNAME_LENGTH) {
				isValid = false;
			}
		}
		if (password != null && !password.isEmpty()) {
			if (password.length() < MIN_PASSWORD_LENGTH ||
					password.length() > MAX_PASSWORD_LENGTH) {
				isValid = false;
			}
		}
		return isValid;
	}
}
