package com.smhrd.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userVO {

	private String id;
	private String pw;
	private String name;
	private String gender;
	private int age;
	private String role;
	private String time;
}
