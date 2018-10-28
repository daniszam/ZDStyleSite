package services;

import Model.Session;
import Model.User;
import Repository.SessionRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class SessionService  {

    private SessionRepository sessionRepository;
    private PasswordEncoder passwordEncoder;


    public SessionService (SessionRepository sessionRepository){
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();

    }

    public void create(User user){
        sessionRepository.create(Session.builder()
                .key(passwordEncoder
                        .encode(user.getHashPassword()))
                .user(user).build());
    }

    public boolean check(Session session){
        if(sessionRepository.get(session.getUser().getId()) != null){
            Session sessionR = sessionRepository.get(session.getUser().getId()).get();
            if(!passwordEncoder.matches(sessionR.getKey(), session.getKey())){
                return false;
            }
            return true;
        }
        return false;
    }

    public String getKey(User user){
        Optional<Session> sessionOptional = sessionRepository.getKey(user.getId());
        if(sessionOptional.isPresent()){
            return passwordEncoder.encode(sessionOptional.get().getKey());
        }else{
            return null;
        }
    }
}
