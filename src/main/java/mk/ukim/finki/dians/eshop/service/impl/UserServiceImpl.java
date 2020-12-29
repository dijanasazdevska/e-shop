package mk.ukim.finki.dians.eshop.service.impl;


import mk.ukim.finki.dians.eshop.model.User;
import mk.ukim.finki.dians.eshop.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.dians.eshop.model.exceptions.PasswordsNotMatchException;
import mk.ukim.finki.dians.eshop.model.exceptions.UserAlreadyExistsException;
import mk.ukim.finki.dians.eshop.repository.UserRepository;
import mk.ukim.finki.dians.eshop.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()-> new UsernameNotFoundException(s));
    }

    @Override
    public User register(String username, String password, String repeatedPassword, String name, String surname) {
        if(username==null || username.isEmpty()|| password==null || password.isEmpty() || repeatedPassword==null || repeatedPassword.isEmpty() || name==null || name.isEmpty() || surname==null || surname.isEmpty())
            throw new InvalidArgumentsException();

        if(!password.equals(repeatedPassword)){
            throw new PasswordsNotMatchException();
        }

        if(userRepository.findByUsername(username).isPresent()){
            throw new UserAlreadyExistsException(username);
        }

        return userRepository.save(new User(username,passwordEncoder.encode(password),name,surname));
    }


}
