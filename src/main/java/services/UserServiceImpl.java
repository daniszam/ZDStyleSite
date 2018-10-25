package services;

import Model.User;
import Repository.CrudRepository;
import Repository.UserCrudRepository;
import forms.SignInForm;
import forms.SignUpForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.Cookie;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class UserServiceImpl implements UserService {


    private UserCrudRepository userCrudRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserCrudRepository repository){
        this.userCrudRepository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public boolean signUp(SignUpForm signUpForm) {
        if(signUpForm.getPassword().equals(signUpForm.getRePassword())){
            Pattern emailPat = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
            Matcher matcher = emailPat.matcher(signUpForm.getEmail());
            if(matcher.find() && signUpForm.getPassword().length()>5){
                User user = User.builder()
                        .email(signUpForm.getEmail())
                        .hashPassword(passwordEncoder.encode(signUpForm.getPassword()))
                        .sex(signUpForm.getSex())
                        .birthday(signUpForm.getBirthday())
                        .country(signUpForm.getCountry())
                        .build();
                userCrudRepository.save(user);
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public void signIn(SignInForm signInForm) {
        Optional<User> userOptional = userCrudRepository.fingOneByEmail(signInForm.getEmail());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(!passwordEncoder.matches(signInForm.getPassword(), user.getHashPassword())){
                throw new IllegalArgumentException("Wrong Password");
            }
        }else {
            throw new IllegalArgumentException("Email is wrong");
        }
    }

    public Optional<User> getUser(SignInForm signInForm){
        Optional<User> userOptional = userCrudRepository.fingOneByEmail(signInForm.getEmail());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(!passwordEncoder.matches(signInForm.getPassword(), user.getHashPassword())){
               return null;
            }
            return Optional.of(user);
        }
        return null;
    }


}
