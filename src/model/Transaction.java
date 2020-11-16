package model;

public class Transaction {
	private String name;
	private String type;
	private double amount;
	private String note;
	private String date;
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	public Transaction(String name, String type, double amount, String note,String date) {
		super();
		this.name = name;
		this.type = type;
		this.amount = amount;
		this.note = note;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Transaction [name=" + name + ", type=" + type + ", amount=" + amount + ", note=" + note + "]";
	}
	
}
