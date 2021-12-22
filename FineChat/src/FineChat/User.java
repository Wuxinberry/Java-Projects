package FineChat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
	String nickname;
	String account;
	private String psword;
	
	private int roomId; // chat room id, 0: no room
	private boolean status; // true: already in a chat room
	
	private void setUser(String nickname, String account, String psword) {
		this.nickname = nickname;
		this.account = account;
		this.setPsword(psword);
		this.setRoomId(0);
		this.setStatus(false);
	}
	
	
	public int signUp(String nickname, String account, String psword) throws SQLException {
		// check validation
		if ("".equals(nickname) || "".equals(account) || "".equals(account)) {
			return 1; // invalid input
		}
		// check account
		MysqlHandler handler = new MysqlHandler();
		String queryAccount = "select * from users where account=" + account;
		ResultSet rs = handler.query(queryAccount);
		if (rs.next()) { 
			return 2; // account already exist
		}
		
		// set user information
		setUser(nickname, account, psword);
		
		// insert into database
		String value = nickname + "','" + account + "','" + psword;
		String insertUser = "insert into users values('" + value + "')";
		handler.execute(insertUser);
		return 0;
	}
	
	
	public boolean logIn(String account, String psword) throws SQLException {
		// check account and password
		if ("".equals(account) || "".equals(psword)) {
			return false;
		}
		
		// register mysql handler
		MysqlHandler handler = new MysqlHandler();
		
		// search for account
		String queryAccount = "select * from users where account=" + account;
		ResultSet rs = handler.query(queryAccount);
		if (rs == null) { // invalid account
			return  false;
		}
		
		String rightPsword = "";
		while (rs.next()) {
			rightPsword = rs.getString("psword");
		}
		
		// check password
		if (rightPsword.equals(psword)) {
			return true;
		}

		return false;		
	}
	
	
	public void enterChatRoom(int roomId) {
		this.setRoomId(roomId);
		this.setStatus(true);
	}
	
	
	public void exitChatRoom() {
		this.setRoomId(0);
		this.setStatus(false);
	}
	
	
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("nickname: ");
			String nickname = sc.nextLine();
			System.out.print("account: ");
			String account = sc.nextLine();
			System.out.print("password: ");
			String psword = sc.nextLine();
			User user = new User();
			user.signUp(nickname, account, psword);
			if (user.logIn(account, psword)) {
				System.out.println("log In");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String getPsword() {
		return psword;
	}


	public void setPsword(String psword) {
		this.psword = psword;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public int getRoomId() {
		return roomId;
	}


	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
}
