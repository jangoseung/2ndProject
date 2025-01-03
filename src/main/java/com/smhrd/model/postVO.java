package com.smhrd.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class postVO {

	private int postIdx;
	private String postTitle;
	private String postContent;
	private String postFile;
	private String createdAt;
	private int postViews;
	private int postLikes;
	private String userId;
	private int commIdx;

}
 
