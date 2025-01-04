package com.smhrd.controller;

import com.smhrd.Util.MyBatisUtil;
import com.smhrd.model.postDAO;
import com.smhrd.model.postVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/PostController")
public class PostController extends HttpServlet {
    private SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postTitle = request.getParameter("postTitle");
        String postContent = request.getParameter("postContent");
        String postFile = request.getParameter("postFile");
        String userId = request.getParameter("userId");
        int commIdx = Integer.parseInt(request.getParameter("commIdx"));

        postVO post = new postVO(0, postTitle, postContent, postFile, 0, 0, userId, commIdx);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            postDAO dao = session.getMapper(postDAO.class);
            int generatedId = dao.insertPost(post);
            session.commit(); // 커밋 필수
            response.getWriter().write("Post inserted with ID: " + generatedId);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error inserting post: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            try (SqlSession session = sqlSessionFactory.openSession()) {
                postDAO dao = session.getMapper(postDAO.class);
                List<postVO> posts = dao.getAllPosts();

                StringBuilder html = new StringBuilder();
                for (postVO post : posts) {
                    html.append("<p>")
                        .append("ID: ").append(post.getPostIdx()).append("<br>")
                        .append("Title: ").append(post.getPostTitle()).append("<br>")
                        .append("Content: ").append(post.getPostContent()).append("<br>")
                        .append("File: ").append(post.getPostFile()).append("<br>")
                        .append("Views: ").append(post.getPostViews()).append("<br>")
                        .append("Likes: ").append(post.getPostLikes()).append("<br>")
                        .append("User ID: ").append(post.getUserId()).append("<br>")
                        .append("Community ID: ").append(post.getCommIdx())
                        .append("</p><hr>");
                }
                response.setContentType("text/html");
                response.getWriter().write(html.toString());
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("Error retrieving posts: " + e.getMessage());
            }
        }
    }
}
