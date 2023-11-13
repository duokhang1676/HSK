package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import db.ConnectDB;
import entity.Kho;
import entity.Thuoc;

public class KhoDao {
	public ArrayList<Kho> getAllTBHoaDon() {
		ArrayList<Kho> dsKho = new ArrayList<Kho>();
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "Select * from Kho";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int soluong = rs.getInt(0);
				Thuoc thuoc = new Thuoc(rs.getInt(1));	
				Kho kho = new Kho(soluong ,thuoc);
				dsKho.add(kho);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKho;
	}

}
