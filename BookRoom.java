package com.main;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/bookroom1")
public class BookRoom extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");
        String roomNo = req.getParameter("roomno");
        String phone = req.getParameter("phone");

        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO booking_table (name, room_no, phone) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, Integer.parseInt(roomNo));
            ps.setString(3, phone);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                out.println("<h2>Room booked successfully!</h2>");
            } else {
                out.println("<h2>Failed to book room.</h2>");
            }
        } catch (Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}
