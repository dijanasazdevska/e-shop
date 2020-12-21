package mk.ukim.finki.dians.eshop.repository;

import mk.ukim.finki.dians.eshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByUsername(String username);
    public User findUserByUsernameAndPassword(String username,String password);

}
