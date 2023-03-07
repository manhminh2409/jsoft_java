package jsoft.library;

import net.htmlparser.jericho.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.*;


public class Utilities {

	/**
	 * Phương thức lấy thông tin tên tác giả hoặc tên người quản lý được nối thêm
	 * vào mô tả chi tiết. Phương thức này được dùng trong SectionLirary,
	 * CategoryLibrary,... <br>
	 * <u><i>Update: 23/10/2022 </i></u></br>
	 * 
	 * @param notes
	 * @param index
	 * @return
	 * @author Mạnh
	 */
	public static String getInfo(String notes, byte index) {
		String tmp = "";

		if (notes != null && !notes.equalsIgnoreCase("")) {
			String[] note_name = notes.split("@@@");

			if (note_name != null && note_name.length > 0 && index < note_name.length) {
				tmp = note_name[index];
			}
		}

		return tmp;
	}

	/**
	 * 
	 * @param unicode
	 * @return
	 */
	public static String encodeToHtml(String uni) {

		return CharacterReference.encode(uni);
	}

	/**
	 * 
	 * @param html
	 * @return
	 */
	public static String decodeFromHtml(String html) {

		return CharacterReference.decode(html);
	}
	
	public static String formatDate(String date) {
		String tmp = "";

		date = date.trim();

		if (date != null) {
			String year = date.substring(0, date.indexOf("-"));
			date = date.replace(year, "");
			date = date.replace("-", " ");
			date = date.trim();
			String month = date.substring(0, date.indexOf(" "));
			date = date.replace(month, "");
			tmp = date + "/" + month + "/" + year;
		}

		
		return tmp.trim();
	}
	
	
	/**
	 * Phương thức 
	 * @param request
	 * @param name
	 * @return
	 */
	public static int getIntParam(ServletRequest request, String name) {
		int value = 0;
		
		String strvalue = request.getParameter(name);
		
		if(strvalue != null && !strvalue.equalsIgnoreCase("")) {
			value = Integer.parseInt(strvalue);
		}
		
		return value;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String convertToMD5(String str) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] messageDigest = md.digest(str.getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		String result = number.toString(16);
		
		return result;
	}
}
