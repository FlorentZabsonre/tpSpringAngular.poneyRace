package dta.spring.tpSpringAngular.poneyRace.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dta.spring.tpSpringAngular.poneyRace.User.MyUser;



public interface UserRepository  extends JpaRepository<MyUser,Long> {
 MyUser findByUsername(String username);
}
