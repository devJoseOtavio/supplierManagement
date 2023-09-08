package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Dao {

	private String url = "jdbc:mysql://127.0.0.1:3306/selective_process?useTimezone=true&serverTimezone=UTC";

	private String user = "root";

	private String password = "root";

	private Connection connectionToHost() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void insertSupplier(Supplier supplier) {
		String create = "insert into selective_process.supplier (name, email, comment, cnpj) values (?, ?, ?, ?)";

		try {
			Connection con = connectionToHost();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, supplier.getName());
			pst.setString(1, supplier.getEmail());
			pst.setString(1, supplier.getComment());
			pst.setString(1, supplier.getCnpj());
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Supplier> supplierList() {
		List<Supplier> supplierList = new ArrayList<>();
		String read = "select * from selective_process.supplier order by name";

		try {
			Connection con = connectionToHost();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String comment = rs.getString(4);
				String cnpj = rs.getString(5);
				supplierList.add(new Supplier(id, name, email, comment, cnpj));
			}
			con.close();
			return supplierList;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void searchSupplier(Supplier supplier) {
		String search = "select * from selective_process.supplier where id = ?";

		try {
			Connection con = connectionToHost();
			PreparedStatement pst = con.prepareStatement(search);
			pst.setInt(1, supplier.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				supplier.setId(rs.getInt(1));
				supplier.setName(rs.getString(2));
				supplier.setEmail(rs.getString(3));
				supplier.setComment(rs.getString(4));
				supplier.setCnpj(rs.getString(5));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateSupplier(Supplier supplier) {
		String update = "update selective_process.supplier set name=?, email=?, comment=?, cnpj=? where id =?";

		try {
			Connection con = connectionToHost();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, supplier.getName());
			pst.setString(2, supplier.getEmail());
			pst.setString(3, supplier.getComment());
			pst.setString(4, supplier.getCnpj());
			pst.setInt(5, supplier.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteSupplier(Supplier supplier) {
		String delete = "delete * from selective_process.supplier where id = ?";

		try {
			Connection con = connectionToHost();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, supplier.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
