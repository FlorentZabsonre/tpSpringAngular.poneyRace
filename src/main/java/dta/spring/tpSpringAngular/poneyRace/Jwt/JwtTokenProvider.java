package dta.spring.tpSpringAngular.poneyRace.Jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Role;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import dta.spring.tpSpringAngular.poneyRace.User.MyUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length}")
	private long validityInMilliseconds; // 24H

	@Autowired
	//private ReactiveMongoOperations operations;
//use userRepository a la place doperation
	public String createToken(String username, List<Role> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("auth", roles.stream().map(s -> new SimpleGrantedAuthority(((GrantedAuthority) s).getAuthority()))
				.filter(Objects::nonNull).collect(Collectors.toList()));
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, encodedSecretKey).compact();
	}

	public Optional<Jws<Claims>> getClaims(Optional<String> token) {
		if (!token.isPresent()) {
			return Optional.empty();
		}
		String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		return Optional.of(Jwts.parser().setSigningKey(encodedSecretKey).parseClaimsJws(token.get()));
	}
	public Authentication getAuthentication(String token) {
	    Query query = new Query();
	    query.addCriteria(Criteria.where("userId").is(getUsername(token)));
	    //MyUser user = operations.findOne(query, MyUser.class).block();
	    //return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
	    return null;
	  }
	  public String getUsername(String token) {
		    String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		    return Jwts.parser().setSigningKey(encodedSecretKey).parseClaimsJws(token).getBody().getSubject();
		  }
}
