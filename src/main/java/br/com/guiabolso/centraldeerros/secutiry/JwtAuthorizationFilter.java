package br.com.guiabolso.centraldeerros.secutiry;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
@SwaggerDefinition(
		securityDefinition = @SecurityDefinition(
				apiKeyAuthDefinitions = {
						@ApiKeyAuthDefinition (key = "user", name = "Authorization", in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER)
				}
		)
)
public class JwtAuthorizationFilter extends OncePerRequestFilter {
	
	private static final String HEADER = "Authorization";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = request.getHeader(HEADER); 
			if (TokenAuthenticationService.hasJwtToken(token)) {
				String user = TokenAuthenticationService.validateToken(token);
				if (user != null) {
					Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} else {
					SecurityContextHolder.clearContext();
				}
			} else {
				SecurityContextHolder.clearContext();
			}
			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			return;
		}

	}

}