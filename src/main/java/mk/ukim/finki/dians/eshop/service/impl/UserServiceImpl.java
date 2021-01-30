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

/**
 * Service za UserServiceImpl.
 */

@Service
public class UserServiceImpl implements UserService {
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

    /**
     * Constructor so argumenti za UserServiceImpl.
     * @param userRepository - interfejs za pronaogjanje na eden korisnik.
     * @param passwordEncoder - promenliva koja ja enkriptira lozinkata pred da se zacuva vo bazata.
     */
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * So ovoj metod se prebaruva bazata na korisnici,
     * Se dobiva korisnikot cij username e ednakov na vneseniot argument,
     * Dokolku ne postoi se vrakja error.
     * @param s - String vo koj se cuva korisnickoto ime na korisnikot.
     * @return go stava korisnickoto ime vo userRepository ili ako ne postoi frla isklucok.
     * @throws UsernameNotFoundException - Korisnickoto ime ne e pronajdeno.
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()-> new UsernameNotFoundException(s));
    }

    /**
     * So ovoj metod se vrsi registracija na nov korisnik,
     * Potrebni se 5 informacii od korisnikot. Vnesenite informacii se proveruvaat dali se postecki i validni,
     * Vnesenite informacii dokolku se validni se zacuvuvaat vo userRepository.
     * @param username - pole kade se cuva privremeno korisnickoto ime na korisnikot.
     * @param password - pole kade se cuva privremeno passwordot na korisnikot.
     * @param repeatedPassword - pole za potvrda na passwordot.
     * @param name - pole kade se cuva privremeno imeto na korisnikot.
     * @param surname - pole kade se cuva privremeno prezimeto na korisnikot.
     * @return da zacuvuva nov korisnik vo bazata.
     */
    @Override
    public User register(String username, String password, String repeatedPassword, String name, String surname) {
        if(username==null || username.equals("") || password==null || password.equals("") || repeatedPassword==null || repeatedPassword.equals("") || name==null || name.equals("") || surname==null || surname.equals(""))
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
