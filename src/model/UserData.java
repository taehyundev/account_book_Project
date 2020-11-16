package model;
public class UserData {
	private int idx;
	private String id;
	private String password;
	private String nickname;
	public String getId() {
		return id;
	}
	public String getNickname() {
		return nickname;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
