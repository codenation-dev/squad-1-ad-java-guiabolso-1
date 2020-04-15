package br.com.guiabolso.centraldeerros.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO {

    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;

}
