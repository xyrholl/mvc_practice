package dto;

public class CoffeeDto {

	private int seq;
	private String name;
	private String size_short;
	private String size_tall;
	private String size_grande;
	private int rowNum;

	
	public CoffeeDto() {
	}

	public CoffeeDto(int seq, String name, String size_short, String size_tall, String size_grande,	int rowNum) {
		super();
		this.seq = seq;
		this.name = name;
		this.size_short = size_short;
		this.size_tall = size_tall;
		this.size_grande = size_grande;
		this.rowNum = rowNum;
	}



	public int getRowNum() {
		return rowNum;
	}



	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}



	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize_short() {
		return size_short;
	}

	public void setSize_short(String size_short) {
		this.size_short = size_short;
	}

	public String getSize_tall() {
		return size_tall;
	}

	public void setSize_tall(String size_tall) {
		this.size_tall = size_tall;
	}

	public String getSize_grande() {
		return size_grande;
	}

	public void setSize_grande(String size_grande) {
		this.size_grande = size_grande;
	}

	@Override
	public String toString() {
		return "CoffeeDto [seq=" + seq + ", name=" + name + ", size_short=" + size_short + ", size_tall=" + size_tall
				+ ", size_grande=" + size_grande + "]";
	}


}
