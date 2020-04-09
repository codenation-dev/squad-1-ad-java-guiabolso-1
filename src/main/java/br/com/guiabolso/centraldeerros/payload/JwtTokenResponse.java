package br.com.guiabolso.centraldeerros.payload;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenResponse implements Serializable {
	private static final long serialVersionUID = 5697782364246597321L;
	private String token;
}
