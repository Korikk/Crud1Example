package com.Crud1.Crud1Example.dto;


import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class CustomerDto {
    private Long id;
    private String name;
    private String surname;
    private Long age;
}
