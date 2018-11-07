package dta.spring.tpSpringAngular.poneyRace.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dta.spring.tpSpringAngular.poneyRace.Exception.ResourceNotFoundException;
import dta.spring.tpSpringAngular.poneyRace.Poney.Pony;
import dta.spring.tpSpringAngular.poneyRace.Race.Race;
import dta.spring.tpSpringAngular.poneyRace.Repository.RaceRepository;

@RestController
@RequestMapping("api/races")
public class RaceController {
	@Autowired
	RaceRepository raceRepo;

	@CrossOrigin(origins = "*")
	@GetMapping("/")
	public List<Race> getAll() {
		return raceRepo.findAll();
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public Race getById(@PathVariable(value = "id") Long raceId) {
		return raceRepo.findById(raceId).orElseThrow(() -> new ResourceNotFoundException("race", "id", raceId));
	}

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/")
	public Race create(@RequestBody Race race) {

		return raceRepo.save(race);
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/{id}")
	public Race updateBy(@PathVariable(value = "id") Long raceId, @RequestBody Race race) {
		List<Pony> ponies = new ArrayList<>();
		Race r = raceRepo.findById(raceId).orElseThrow(() -> new ResourceNotFoundException("race", "id", raceId));
		r.setDateR(race.getDateR());
		r.setLocation(race.getLocation());
		r.setPonies(race.getPonies());
		Race raceUp = raceRepo.save(r);
		return raceUp;
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") Long raceId) {
		Race p = raceRepo.findById(raceId).orElseThrow(() -> new ResourceNotFoundException("pony", "id", raceId));
		raceRepo.delete(p);
	}
}
