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
import dta.spring.tpSpringAngular.poneyRace.Race.Race;
import dta.spring.tpSpringAngular.poneyRace.Repository.UserRepository;
import dta.spring.tpSpringAngular.poneyRace.User.MyUser;

@RestController
@RequestMapping("myuser")
public class UserController {

	@Autowired
	private UserRepository useRepo;

	@CrossOrigin(origins = "*")
	@GetMapping("/users")
	public List<MyUser> getAllUsers() {
		return useRepo.findAll();
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/addUser")
	public MyUser createUser(@RequestBody MyUser user) {
		MyUser user1 = useRepo.save(new MyUser(user.getUsername(), user.getSalt(), user.getEmail(), user.getToken()));
		return user1;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	public MyUser getById(@PathVariable(value = "id") Long useId) {
		return useRepo.findById(useId).orElseThrow(() -> new ResourceNotFoundException("race", "id", useId));
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/update")
	public MyUser updateBy(Long useId, @RequestBody MyUser user) {
		MyUser u = useRepo.findById(useId).orElseThrow(() -> new ResourceNotFoundException("race", "id", useId));
		u.setEmail(user.getEmail());
		u.setUsername(user.getUsername());

		MyUser userUp = useRepo.save(u);
		return userUp;
	}
	@CrossOrigin(origins = "*")
	@DeleteMapping("/deleteUser")
	public void deleteUser(Long useId)
	{
		MyUser u= useRepo.findById(useId).orElseThrow(() -> new ResourceNotFoundException("pony", "id", useId));
		useRepo.delete(u);
	}
}
