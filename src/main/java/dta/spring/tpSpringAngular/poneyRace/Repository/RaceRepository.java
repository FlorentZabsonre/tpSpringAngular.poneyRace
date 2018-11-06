package dta.spring.tpSpringAngular.poneyRace.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dta.spring.tpSpringAngular.poneyRace.Race.Race;

public interface RaceRepository   extends JpaRepository<Race,Long>{

}
