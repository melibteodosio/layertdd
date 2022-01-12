package br.com.packagingby.layer.user.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {

    private Long id;
    private String name;
    private String email;
    private String username;
}
