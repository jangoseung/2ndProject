package com.smhrd.model;


import java.sql.*;
import java.time.LocalDateTime;
import java.sql.Statement;
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
				post.setPostViews(rs.getInt("post_views"));
				post.setPostLikes(rs.getInt("post_likes"));
				post.setUserId(rs.getString("user_id"));
				post.setCommIdx(rs.getInt("comm_idx"));
				posts.add(post);
				
			}
		}

		return posts;		
	}
	
	public int insertPost(postVO post) throws SQLException {
		String sql = "INSERT INTO tb_post (post_title, post_content, post_file, created_at, post_views, post_likes, user_id, comm_idx)"
				+ "VALUES (?, ?, ?, CURRENT_TIMESTAMP, 0, 0, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1,  post.getPostTitle());
			stmt.setString(2, post.getPostContent());
			stmt.setString(3, post.getPostFile());
			stmt.setString(4, post.getUserId());
			stmt.setInt(5, post.getCommIdx());
			stmt.executeUpdate();
			
			try(ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if(generatedKeys.next()) {
					return generatedKeys.getInt(1);
				}
			}
			
		}
		return -1;	
	}
	
	public boolean deletePost(int postIdx) throws SQLException {
        String sql = "DELETE FROM tb_post WHERE post_idx = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, postIdx);
            return stmt.executeUpdate() > 0;
		}
		// 다시 수정후 보냄
	}
	
}
