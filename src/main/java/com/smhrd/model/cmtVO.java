package com.smhrd.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class cmtVO {

	private int cmtIdx;
	private int postIdx;
	private String cmtContent;
	private String createdAt;
	private int cmtLikes;
	private String userId;


}