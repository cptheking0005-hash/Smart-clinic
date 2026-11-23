/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.smartclinic.servlet;

import com.smartclinic.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet(name = "SetupServlet", urlPatterns = {"/SetupServlet"})
public class SetupServlet extends HttpServlet {

@Override    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement()) {
            st.execute("CREATE DATABASE IF NOT EXISTS smartclinic");
            st.execute("USE smartclinic");
            // Create doctor table
            st.execute("CREATE TABLE IF NOT EXISTS doctor (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "specialization VARCHAR(100)," +
                    "phone VARCHAR(20)," +
                    "email VARCHAR(100) UNIQUE" +
                    ")");

            // Create patient table
            st.execute("CREATE TABLE IF NOT EXISTS patient (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "phone VARCHAR(20)," +
                    "email VARCHAR(100)" +
                    ")");

            // Create appointment table
            st.execute("CREATE TABLE IF NOT EXISTS appointment (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "patient_id INT NOT NULL," +
                    "doctor_id INT NOT NULL," +
                    "appointment_date DATE NOT NULL," +
                    "appointment_time TIME NOT NULL," +
                    "status VARCHAR(20) DEFAULT 'BOOKED'," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (patient_id) REFERENCES patient(id)," +
                    "FOREIGN KEY (doctor_id) REFERENCES doctor(id)" +
                    ")");

            // Insert sample doctors
           
            st.execute("INSERT IGNORE INTO doctor (name, specialization, phone, email) VALUES " +
                    "('Dr. Asha Verma','General Physician','9876543210','asha@clinic.com')," +
                    "('Dr. Rohit Singh','Pediatrician','9123456780','rohit@clinic.com')");

            response.getWriter().println("<h2>Setup Completed Successfully</h2>");

        } catch (Exception e) {
            System.err.println(e.getMessage());
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}