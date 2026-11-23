<%-- 
    Document   : index
    Created on : 21 Nov 2025, 2:23:17 pm
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Smart Clinic System</title>
    <style>
        body {
            font-family: Arial;
            margin: 40px;
        }
        h1 {
            margin-bottom: 30px;
        }
        .btn {
            display: block;
            width: 300px;
            padding: 12px;
            margin: 10px 0;
            background: #2c3e50;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            font-size: 18px;
        }
        .btn:hover {
            background: #1a242f;
        }
    </style>
</head>
<body>

<h1>Smart Clinic Management System</h1>

<a href="patientForm.jsp" class="btn">Register Patient</a>
<a href="appointmentForm.jsp" class="btn">Book Appointment</a>
<a href="patientAppointments.jsp" class="btn">View Appointments by Patient</a>
<a href="doctorAppointments.jsp" class="btn">View Appointments by Doctor</a>

</body>
</html>