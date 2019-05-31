package system.model;

public class Page {
	int nowPage = 0;
	int beginNum = 0;
	int endNum = 0;
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int pageNum) {
		this.nowPage = pageNum;
	}
	public int getBeginNum() {
		return beginNum;
	}
	public void setBeginNum(int beginNum) {
		this.beginNum = beginNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
}
