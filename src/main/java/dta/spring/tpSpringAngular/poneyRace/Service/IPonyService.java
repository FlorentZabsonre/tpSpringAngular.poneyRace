package dta.spring.tpSpringAngular.poneyRace.Service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import dta.spring.tpSpringAngular.poneyRace.Poney.Pony;



public interface IPonyService 
{
	Pony updateById(@PathVariable(value="id") Long usId, @RequestBody Pony ponyUp);
}
