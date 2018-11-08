package dta.spring.tpSpringAngular.poneyRace.Controller;

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
import dta.spring.tpSpringAngular.poneyRace.Repository.PonyRepository;

@RestController
@RequestMapping("api/ponies")
public class PonyController {

	@Autowired
	PonyRepository ponyRepo;

	@CrossOrigin(origins = "*")
	@GetMapping("/lesPoneys")
	public List<Pony> getAll() {
		return ponyRepo.findAll();
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/pony/{id}")
	public Pony getById(@PathVariable(value = "id") Long ponyId) {
		return ponyRepo.findById(ponyId).orElseThrow(() 
		-> new ResourceNotFoundException("poney", "id", ponyId));
	}

	@CrossOrigin(origins = "*")
	@PostMapping(path = "/addPony")
	public Pony create(@RequestBody Pony pony) {
		return ponyRepo.save(pony);
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/update/{id}")
	public Pony updateBy(@PathVariable(value = "id") Long ponId, @RequestBody Pony pony) {
		Pony p = ponyRepo.findById(ponId).orElseThrow(() -> new ResourceNotFoundException("poney", "id", ponId));
		p.setName(pony.getName());
		p.setWeight(pony.getWeight());
		p.setAge(pony.getAge());
		p.setColor(pony.getColor());
		Pony ponyUp = ponyRepo.save(p);
		return ponyUp;
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/deletePony/{id}")
	public void deleteById(@PathVariable(value = "id") Long ponyId) {
		Pony p = ponyRepo.findById(ponyId).orElseThrow(() -> new ResourceNotFoundException("pony", "id", ponyId));
		ponyRepo.delete(p);
	}
}
