package com.smartclinic.servlet;

import com.smartclinic.dao.AppointmentDAO;
import com.smartclinic.model.Appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "AppointmentBookingServlet", urlPatterns = {"/bookAppointment"})
public class AppointmentBookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Read form fields
            int patientId = Integer.parseInt(request.getParameter("patientId"));
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            Date appointmentDate = Date.valueOf(request.getParameter("date"));

            // Get raw time input
            String rawTime = request.getParameter("time").trim();

            // FIX 1: Convert 12-hour format to 24-hour format if AM/PM exists
            if (rawTime.toUpperCase().contains("AM") || rawTime.toUpperCase().contains("PM")) {
                LocalTime parsedTime = LocalTime.parse(rawTime,
                        DateTimeFormatter.ofPattern("hh:mm a"));
                rawTime = parsedTime.toString();  // produces HH:mm:ss
            }

            // FIX 2: If format is HH:mm → append seconds
            if (rawTime.length() == 5) {
                rawTime = rawTime + ":00";
            }

            // Convert to SQL Time
            Time appointmentTime = Time.valueOf(rawTime);

            // Prepare model object
            Appointment a = new Appointment();
            a.setPatientId(patientId);
            a.setDoctorId(doctorId);
            a.setAppointmentDate(appointmentDate);
            a.setAppointmentTime(appointmentTime);
            a.setStatus("BOOKED");

            // DAO call
            AppointmentDAO dao = new AppointmentDAO();
            boolean saved = dao.bookAppointment(a);

            if (saved) {
                // Redirect to success page
                response.sendRedirect("success.jsp");
            } else {
                response.getWriter().println("<h3>Failed to book appointment.</h3>");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}