package com.main;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/viewroom")
public class ViewRoom extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM booking_table WHERE id=? AND name=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.setString(2, name);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                out.println("<h2>Booking Details:</h2>");
                out.println("<p>ID: " + rs.getInt("id") + "</p>");
                out.println("<p>Name: " + rs.getString("name") + "</p>");
                out.println("<p>Room No: " + rs.getString("room_no") + "</p>");
                out.println("<p>Phone: " + rs.getString("phone") + "</p>");
                out.println("<p>Time: " + rs.getTimestamp("booking_time") + "</p>");
            } else {
                out.println("<h2>No booking found.</h2>");
            }
        } catch (Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}
