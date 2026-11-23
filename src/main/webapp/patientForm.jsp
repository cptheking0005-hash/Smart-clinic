<%-- 
    Document   : patientForm
    Created on : 21 Nov 2025, 1:53:36 pm
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register Patient</title>
</head>
<body>

<h2>Patient Registration</h2>

<form action="registerPatient" method="post">
    Name: <input type="text" name="name" required><br><br>
    Phone: <input type="text" name="phone"><br><br>
    Email: <input type="email" name="email"><br><br>

    <button type="submit">Register</button>
</form>

</body>
</html>