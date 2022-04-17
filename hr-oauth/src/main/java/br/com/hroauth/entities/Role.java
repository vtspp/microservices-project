package br.com.hroauth.entities;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Role {

    private Long id;

    private String roleName;
}