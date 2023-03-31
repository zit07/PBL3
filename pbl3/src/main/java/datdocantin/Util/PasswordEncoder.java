package datdocantin.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
	public static String encode(String password) {
        try {
            // Tạo đối tượng MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Chuyển đổi mật khẩu sang dạng byte array
            byte[] passwordBytes = password.getBytes();
            // Mã hoá byte array của mật khẩu bằng MD5
            byte[] hashBytes = md.digest(passwordBytes);
            // Chuyển đổi kết quả mã hoá sang chuỗi hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
