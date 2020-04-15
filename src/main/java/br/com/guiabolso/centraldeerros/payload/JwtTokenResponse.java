package br.com.guiabolso.centraldeerros.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JwtTokenResponse implements Serializable {
	private static final long serialVersionUID = 5697782364246597321L;
	private String token;
}
