package br.com.nikisgabriel.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.auth0.jwt.algorithms.Algorithm;

import br.com.nikisgabriel.data.vo.v1.security.TokenVO;
import jakarta.annotation.PostConstruct;

@Service
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";

	@Value("${security.jwt.token.expire-lenght:1000 * 60 * 60}")
	private Long validityInMilliSeconds = 1000 * 60 * 60L; // uma hora

	@Autowired
	private UserDetailsService userDatailsService;

	Algorithm algorithm = null;

	// é um método de ciclo de vida gerenciado pelo spring onde é executado após os
	// métodos beans gerenciados pelo spring já estiverem devidamente inicializados
	// evitando null pointer exceptions pro exemplo
	@PostConstruct
	protected void init() {
		// encripta o valor de secretKey e atribui ele novamente a variável
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		algorithm = Algorithm.HMAC256(secretKey.getBytes());
	}

	public TokenVO createAccessToken(String username, List<String> roles) {
		Date now = new Date();
		// gerando a data de expiração baseada na data de criação
		Date validity = new Date(now.getTime() + validityInMilliSeconds);
		String accessToken = getAcessToken(username, roles, now, validity);
		String refreshToken = getRefreshToken(username, roles, now, validity);
		return new TokenVO(username, true, now, validity, accessToken, refreshToken);
	}

	private String getAcessToken(String username, List<String> roles, Date now, Date validity) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getRefreshToken(String username, List<String> roles, Date now, Date validity) {
		// TODO Auto-generated method stub
		return null;
	}
}
