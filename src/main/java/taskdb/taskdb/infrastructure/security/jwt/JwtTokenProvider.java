package taskdb.taskdb.infrastructure.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.infrastructure.security.exception.ExpiredTokenException;
import taskdb.taskdb.infrastructure.security.exception.InvalidTokenException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    @Value("spring.jwt.secret")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    private static final String HEADER_ACCESS_TOKEN = "ACCESS_TOKEN";

    public String createAccessToken(User user) {
        final String email = user.getEmail();
        final String role = user.getRole().name();
        final long ACCESS_TOKEN_VALID_TIME = 30 * 24 * 60 * 60 * 1000L;
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String resolveAccessToken(HttpServletRequest request) {
        return request.getHeader(HEADER_ACCESS_TOKEN);
    }

    public String getUserEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }
}
