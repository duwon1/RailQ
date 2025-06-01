package dto;

public class RegionDto {
	private String nodeid;
	private String nodename;
	
	public String getNodeid() {
		return nodeid;
	}
	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}
	public String getNodename() {
		return nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	
	@Override
	public String toString() {
	    return "RegionDto { nodeid = " + nodeid + ", nodename = '" + nodename + "' }";
	}
	
	
}
