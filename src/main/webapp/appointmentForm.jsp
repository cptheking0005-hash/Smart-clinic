<%-- 
    Document   : appointmentForm.jsp
    Created on : 21 Nov 2025, 1:59:53 pm
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.smartclinic.dao.DoctorDAO" %>
<%@ page import="com.smartclinic.model.Doctor" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Book Appointment</title>
</head>
<body>

<h2>Book an Appointment</h2>

<%
    // Fetch doctor list for dropdown
    DoctorDAO dao = new DoctorDAO();
    List<Doctor> doctors = dao.getAllDoctors();
%>

<form action="bookAppointment" method="post">

    Patient ID:
    <input type="number" name="patientId" required><br><br>

    Doctor:
    <select name="doctorId" required>
        <option value="">-- Select Doctor --</option>
        <% for (Doctor d : doctors) { %>
            <option value="<%= d.getId() %>">
                <%= d.getName() %> ( <%= d.getSpecialization() %> )
            </option>
        <% } %>
    </select>
    <br><br>

    Appointment Date:
    <input type="date" name="date" required><br><br>

    Appointment Time:
    <input type="time" name="time" required><br><br>

    <button type="submit">Book Appointment</button>
</form>

</body>
</html>
