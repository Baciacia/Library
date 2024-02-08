package com.bacia.quickstart.Domain.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
}
