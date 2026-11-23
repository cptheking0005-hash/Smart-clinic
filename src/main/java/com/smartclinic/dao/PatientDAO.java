/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.smartclinic.dao;

import com.smartclinic.model.Patient;
import com.smartclinic.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PatientDAO {

    // Insert new patient
    public boolean addPatient(Patient p) throws Exception {

        String sql = "INSERT INTO patient (name, phone, email) VALUES (?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setString(2, p.getPhone());
            ps.setString(3, p.getEmail());

            return ps.executeUpdate() > 0;
        }
    }

    // Fetch patient by ID
    public Patient getPatientById(int id) throws Exception {

        Patient p = null;
        String sql = "SELECT * FROM patient WHERE id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Patient();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPhone(rs.getString("phone"));
                p.setEmail(rs.getString("email"));
            }
        }
        return p;
    }
}
