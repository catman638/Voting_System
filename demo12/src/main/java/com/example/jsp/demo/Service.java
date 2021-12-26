package com.example.jsp.demo;


import org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class Service {

    @GetMapping("/voteD")
    public Integer getVoteD() {
        int store = -1;
         final String DB_URL = "jdbc:mysql://localhost:3306/demo";
         final String USER = "root";
        final String PASS = "3016";
         final String QUERY = "SELECT VoteDeepak from vote";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
        ) {

            while(rs.next()) {
                store = rs.getInt("VoteDeepak");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return store;
    }

    @GetMapping("/voteM")
    public Integer getVoteM() {
        int store = -1;
        final String DB_URL = "jdbc:mysql://localhost:3306/demo";
        final String USER = "root";
        final String PASS = "3016";
        final String QUERY = "SELECT * from vote";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
        ) {

            while(rs.next()) {
                store = rs.getInt("VoteModi");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return store;
    }

    @PostMapping("/submit/{value}")
    public String submitChoice(@PathVariable String value) {
        final String DB_URL = "jdbc:mysql://localhost:3306/demo";
        final String USER = "root";
        final String PASS = "3016";

        //final String QUERY = "INSERT into VoteDeepak from vote";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            // Execute a query
            //System.out.println("Inserting records into the table...");
            if(value.equals("1"))
            {
                String sql = "UPDATE vote SET VoteDeepak = VoteDeepak + 1 ";
                stmt.executeUpdate(sql);

            }
            else if(value.equals("2"))
            {
                String sql = "UPDATE vote SET VoteModi = VoteModi + 1";
                stmt.executeUpdate(sql);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;

    }

    @PostMapping("/reset")
    public void reset() {
        final String DB_URL = "jdbc:mysql://localhost:3306/demo";
        final String USER = "root";
        final String PASS = "3016";
        String sql = "UPDATE vote SET VoteDeepak = 0, VoteModi= 0";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
