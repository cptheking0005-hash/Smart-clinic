<%-- 
    Document   : doctorAppointments
    Created on : 21 Nov 2025, 2:18:51 pm
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.smartclinic.dao.AppointmentDAO" %>
<%@ page import="com.smartclinic.model.Appointment" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Doctor Appointments</title>
</head>
<body>

<h2>View Appointments by Doctor</h2>

<form method="get">
    Doctor ID: <input type="number" name="doctorId" required>
    <button type="submit">View</button>
</form>

<br>

<%
    String docIdStr = request.getParameter("doctorId");

    if (docIdStr != null) {
        try {
            int doctorId = Integer.parseInt(docIdStr);

            AppointmentDAO dao = new AppointmentDAO();
            List<Appointment> list = dao.getAppointmentsByDoctor(doctorId);

            if (list.isEmpty()) {
                out.println("<h3>No appointments found.</h3>");
            } else {
%>

<table border="1" cellpadding="8">
    <tr>
        <th>ID</th>
        <th>Patient ID</th>
        <th>Doctor ID</th>
        <th>Date</th>
        <th>Time</th>
        <th>Status</th>
    </tr>

    <% for (Appointment a : list) { %>
    <tr>
        <td><%= a.getId() %></td>
        <td><%= a.getPatientId() %></td>
        <td><%= a.getDoctorId() %></td>
        <td><%= a.getAppointmentDate() %></td>
        <td><%= a.getAppointmentTime() %></td>
        <td><%= a.getStatus() %></td>
    </tr>
    <% } %>

</table>

<%
            }
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
%>

</body>
</html>
