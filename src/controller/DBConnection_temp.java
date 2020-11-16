package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Transaction;
import model.TransactionBuilder;
import model.UserData;

public class DBConnection_temp {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnection_temp() {
		// TODO Auto-generated constructor stub
		try {           
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/houseaffairs?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
			st = con.createStatement();
		}catch(Exception e)
		{
			System.out.println("실패");
		}
	}
	public boolean isUser(String userId, String userPwd, UserData u) {
		try {
			String sql = "select * from userlist where id ='"+userId+"' and pwd = '"+userPwd +"'";
			rs = st.executeQuery(sql);
			System.out.println("a");
			if(rs.next())
			{
				u.setIdx(rs.getInt("idx"));
				u.setId(rs.getString("id"));
				u.setPassword(rs.getString("pwd"));
				u.setNickname(rs.getString("name"));
				
				return true;
			}
		}catch(Exception e){
			
		}
		return false;
	}
	public boolean createTranTable(String userId) {
		String sql ="";
		try {
			StringBuilder sb = new StringBuilder();
			sql = sb.append("create table if not exists user_"+userId+"(")
					.append("name varchar(30),")
					.append("type varchar(30),")
					.append("amount int,")
					.append("memo varchar(300)")
					.append(");").toString();
			st.execute(sql);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(sql);
		}
		return false;
	}
	public List getTranTable(String userId) {
		String sql ="";
		try {
			sql = "select * from user_"+userId;
			rs = st.executeQuery(sql);
			List<Transaction> list =  new ArrayList<>();
			while(rs.next()) {
				Transaction t = new Transaction();
				TransactionBuilder tb = new TransactionBuilder(t);
				t = tb.name(rs.getString("name")).type(rs.getString("type")).amount(Double.parseDouble(rs.getString("amount"))).note(rs.getString("memo")).transaction();
				
				list.add(t);
			}
			return list;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(sql);
		}
		return null;
	}
	public boolean setTranTable(String userId, String name,String type,String amount,String memo) {
		String sql ="";
		try {
			sql = "insert into user_"+userId+"(name,type,amount,memo) values ('"+name+"', '"+type+"',"+Integer.parseInt(amount)+ ",'" +memo+"')";
			int affectCount = st.executeUpdate(sql);
			System.out.println("affectCount = "+ affectCount);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public boolean signUp(String nickname,String userId, String userPwd) {
		String sql;
		try {
			sql = "select * from userlist where id ='"+userId+"'";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				return false;
			}else {
				//회원 존재 x 가입 가능
				
				if(createTranTable(userId)) {
					try {
						sql = "insert into userlist(id,pwd,name) values ('"+userId+"', '"+userPwd+"', '" +nickname+"')";
						int affectCount = st.executeUpdate(sql);
						System.out.println("affectCount = "+ affectCount);
						return true;
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}catch(Exception e){
		}
		return false;
		
	}
}
