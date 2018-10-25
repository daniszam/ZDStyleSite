package services;

import Model.User;
import forms.SignInForm;
import forms.SignUpForm;

import java.util.Optional;

public interface UserService {

    boolean signUp (SignUpForm signUpForm);
    void signIn (SignInForm signInForm);
    Optional<User> getUser(SignInForm signInForm);
}
