package com.epam.aa.booking.dao;

import com.epam.aa.booking.model.Customer;
import com.epam.aa.booking.util.Util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//TODO: DAOException
public class CustomerDAO {
    private static boolean customersTableCreated;
    public void insert(Customer customer) {
        Connection con = null;
        Statement st = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String url = "jdbc:postgresql://localhost/booking";
        String user = "almas";
        String password = "Q1w2e3r4t5";

        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            if (!customersTableCreated) {
                st.execute("CREATE TABLE IF NOT EXISTS customers (" +
                        "id SERIAL PRIMARY KEY, " +
                        "deleted BOOLEAN DEFAULT FALSE, " +
                        "first_name VARCHAR(25), " +
                        "last_name VARCHAR(25), " +
                        "contact_info_id INTEGER, " +
                        "password VARCHAR(25), " +
                        "birth_date DATE" +
                        ");");
                customersTableCreated = true;
            }
            String stm = "INSERT INTO customers(first_name, last_name, birth_date) " +
                    "VALUES(?, ?, ?)";
            pst = con.prepareStatement(stm);
            pst.setString(1, customer.getFirstName());
            pst.setString(2, customer.getLastName());
//            if (customer.getContactInfo() != null) {
//                ContactInfoDAO contactInfoDAO = new ContactInfoDAO();
//                contactInfoDAO.insertWithReturningId(customer.getContactInfo());
//            }
//            //TODO: what to do about password?
            Date birthDateToStore = Util.getSQLDateFromLocalDate(customer.getBirthDate());
            pst.setDate(3, birthDateToStore);

            pst.executeUpdate();
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(CustomerDAO.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(CustomerDAO.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}