package dta.spring.tpSpringAngular.poneyRace.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyUser implements UserDetails {

	@Id
	private ObjectId _id;

	@JsonProperty
	private final Date createdAt = new Date();

	@JsonProperty
	private String userId;

	@JsonProperty
	private String password;

	@JsonProperty
	private String salt;

	@JsonProperty
	private String email;

	@JsonProperty
	private String token;

	@JsonProperty
	private String username;

	public void setUsername(String username) {
		this.username = username;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//
		GrantedAuthority auth = () -> "USERS";
		return Arrays.asList(auth);
	}

	@Override
	public String getUsername() {
		//
		return this.userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		//
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		//
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public MyUser( String salt, String email, String token, String username) {
		super();
	
		this.salt = salt;
		this.email = email;
		this.token = token;
		this.username = username;
	}
}
