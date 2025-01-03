package com.smhrd.model;

import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Results;

public class postDAO {

	private Connection conn;
	
	public postDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<postVO> getAllPosts() throws SQLException {
		String sql = "SELECT * FROM tb_post";
		List<postVO> posts = new ArrayList<>();
		
		try (PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				postVO post = new postVO();
				post.setPostIdx(rs.getInt("post_idx"));
				post.setPostTitle(rs.getString("post_title"));
				post.setPostContent(rs.getString("post_Content"));
				post.setPostFile(rs.getString("post_file"));
				post.setCreatedAt(rs.getString("created_at"));
				
				
			}
		}
 				
		
		
		return posts;
		
		
	}
	
}
