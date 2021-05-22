package com.example.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 9157080365686666615L;

	private Long id;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private LocalDateTime creationTime;

}
