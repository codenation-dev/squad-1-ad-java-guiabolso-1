package br.com.guiabolso.centraldeerros.validation;

import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AccountValidator implements Validator {

	@Autowired
	private AccountService accountService;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Account.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Account account = (Account) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		
		if (account.getUsername().length() < 6 || account.getUsername().length() > 20)
			errors.rejectValue("username", "Size.accountForm.username");
		
		if (accountService.findByUsername(account.getUsername()) != null)
			errors.rejectValue("username", "Duplicate.accountForm.username");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (account.getPassword().length() < 8 || account.getPassword().length() > 20) {
            errors.rejectValue("password", "Size.userForm.password");
        }

	}
	
}
