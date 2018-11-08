package dta.spring.tpSpringAngular.poneyRace.Race;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.Table;

import dta.spring.tpSpringAngular.poneyRace.Poney.Pony;
@Entity
@Table(name="race")
public class Race 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	@ManyToMany(cascade = CascadeType.REMOVE)
	
	private List <Pony> ponies= new ArrayList<>();

	@Column
	private String location;

	@Column
	private Date date;

	public Race() {};

	public Race(List<Pony> ponies, String location, Date date) 
	{
		this.ponies = ponies;
		this.location = location;
		this.date = date;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Pony> getPonies() {
		return ponies;
	}
	public void setPonies(List<Pony> ponies) {
		this.ponies = ponies;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getDateR() {
		return date;
	}
	public void setDateR(Date date) {
		this.date = date;
	}



}
