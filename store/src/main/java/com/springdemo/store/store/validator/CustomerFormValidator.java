package com.springdemo.store.store.validator;

import org.apache.commons.validator.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.springdemo.store.store.form.CustomerForm;

@Component
public class CustomerFormValidator {

	 @SuppressWarnings("deprecation")
	private EmailValidator emailValidator = EmailValidator.getInstance();
	 
	   // This validator only checks for the CustomerForm.
	   @Override
	   public boolean supports(Class<?> clazz) {
	      return clazz == CustomerForm.class;
	   }
	 
	   @Override
	   public void validate(Object target, Errors errors) {
	      CustomerForm custInfo = (CustomerForm) target;
	 
	      // Check the fields of CustomerForm.
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.customerForm.name");
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.customerForm.email");
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.customerForm.address");
	      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.customerForm.phone");
	 
	      if (!emailValidator.isValid(custInfo.getEmail())) {
	         errors.rejectValue("email", "Pattern.customerForm.email");
	      }
	   }
}