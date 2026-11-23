<%-- 
    Document   : patientAppointments
    Created on : 21 Nov 2025, 2:20:44 pm
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.smartclinic.dao.AppointmentDAO" %>
<%@ page import="com.smartclinic.model.Appointment" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Patient Appointments</title>
</head>
<body>

<h2>View Appointments by Patient</h2>

<form method="get">
    Patient ID: <input type="number" name="patientId" required>
    <button type="submit">View</button>
</form>

<br>

<%
    String pIdStr = request.getParameter("patientId");

    if (pIdStr != null) {
        try {
            int patientId = Integer.parseInt(pIdStr);

            AppointmentDAO dao = new AppointmentDAO();
            List<Appointment> list = dao.getAppointmentsByPatient(patientId);

            if (list.isEmpty()) {
                out.println("<h3>No appointments found for this patient.</h3>");
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