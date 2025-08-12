package com.main;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/deletebooking")
public class DeleteBooking extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id");

        try (Connection con = DBConnection.getConnection()) {
            String sql = "DELETE FROM booking_table WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));

            int rows = ps.executeUpdate();
            if (rows > 0) {
                out.println("<h2>Booking deleted successfully!</h2>");
            } else {
                out.println("<h2>No booking found for given ID.</h2>");
            }
        } catch (Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}
