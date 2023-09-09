package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Dao {

	private String driver = "com.mysql.cj.jdbc.Driver";
	
	private String url = "jdbc:mysql://localhost:3306/selective_process";

	private String user = "root";

	private String password = "root";

	private Connection connectionToHost() {
		Connection con = null;
		try {
			Class.forName(driver);
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
			pst.setString(2, supplier.getEmail());
			pst.setString(3, supplier.getComment());
			pst.setString(4, supplier.getCnpj());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public ArrayList<Supplier> supplierList() {
		ArrayList<Supplier> supplierList = new ArrayList<>();
		String read = "select * from selective_process.supplier order by id";

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
		String delete = "delete from selective_process.supplier where id = ?";

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
	
	public boolean validateCnpj(String cnpj) {
		String verifyIfCnpjExists = "select count(*) from selective_process.supplier where cnpj = ?";
		
		try {
			Connection con = connectionToHost();
			PreparedStatement pst = con.prepareStatement(verifyIfCnpjExists);
			pst.setString(1, cnpj);
			ResultSet resultSet = pst.executeQuery(); 
			
			if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    System.out.println("CPF já existe no banco de dados. Não é possível registrar o fornecedor.");
                    return false;
                } else {
                    System.out.println("CPF não encontrado no banco de dados. Você pode registrar o fornecedor.");
                    return true;
                }
            }
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
