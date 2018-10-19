package services;

import forms.SignInForm;
import forms.SignUpForm;

public interface UserService {

    boolean signUp (SignUpForm signUpForm);
    void signIn (SignInForm signInForm);
}
