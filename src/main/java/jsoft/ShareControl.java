package jsoft;

public interface ShareControl {

	// Chức năng điều khiển chia sẻ bộ quản lý kết nôi giữa các Basic
	public ConnectionPool getCP();

	// Chức năng yêu cầu trả lại kết nối
	public void releaseConnection();
}
