package mk.ukim.finki.dians.eshop.service.impl;

import mk.ukim.finki.dians.eshop.model.ShoppingCart;
import mk.ukim.finki.dians.eshop.model.User;
import mk.ukim.finki.dians.eshop.model.exceptions.*;
import mk.ukim.finki.dians.eshop.repository.ShoppingCartRepository;
import mk.ukim.finki.dians.eshop.repository.UserRepository;
import mk.ukim.finki.dians.eshop.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public AuthServiceImpl(UserRepository userRepository, ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public User register(String username, String password, String repeatedPassword, String name, String surname) {


        if(username==null || username.isEmpty()|| password==null || password.isEmpty() || repeatedPassword==null || repeatedPassword.isEmpty() || name==null || name.isEmpty() || surname==null || surname.isEmpty())
            throw new InvalidArgumentsException();

        if(!password.equals(repeatedPassword)){
            throw new PasswordsNotMatchException();
        }

        if(userRepository.findUserByUsername(username)!=null){
            throw new UserAlreadyExistsException(username);
        }

        return userRepository.save(new User(username,password,name,surname));
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password.isEmpty() || password == null)
            throw new InvalidUserCredentialsException();
        if(userRepository.findUserByUsername(username)==null){
            throw new UserNotExistsException();
        }
        User user = userRepository.findUserByUsername(username);

        if(!user.getPassword().equals(password))
            throw new InvalidUserCredentialsException();

        return user;



    }

    @Override
    public ShoppingCart findShoppingCart(User user) {

        if(shoppingCartRepository.findByUser(user)==null){
            shoppingCartRepository.save(new ShoppingCart(user));
        }
        return shoppingCartRepository.findByUser(user);
    }

}
