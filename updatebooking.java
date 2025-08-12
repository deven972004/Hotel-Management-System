package com.main;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/updatebooking")
public class UpdateBooking extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");
        String phone = req.getParameter("phone");

        try (Connection con = DBConnection.getConnection()) {
            String sql = "UPDATE booking_table SET phone=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, phone);
            ps.setInt(2, Integer.parseInt(id));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                out.println("<h2>Booking updated successfully!</h2>");
            } else {
                out.println("<h2>No booking found for given ID.</h2>");
            }
        } catch (Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}
