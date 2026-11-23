/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.smartclinic.servlet;

import com.smartclinic.dao.PatientDAO;
import com.smartclinic.model.Patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "PatientRegistrationServlet", urlPatterns = {"/registerPatient"})
public class PatientRegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");

            Patient p = new Patient();
            p.setName(name);
            p.setPhone(phone);
            p.setEmail(email);

            PatientDAO dao = new PatientDAO();
            boolean saved = dao.addPatient(p);   // BOOLEAN RETURN

            if (saved) {
                response.sendRedirect("success.jsp");   // SUCCESS PAGE
            } else {
                response.getWriter().println("<h3>Failed to register patient!</h3>");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}