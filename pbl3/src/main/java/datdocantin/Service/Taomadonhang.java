package datdocantin.Service;

import java.util.Random;

import datdocantin.Dao.HoadonDAO;

public class Taomadonhang {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";                 
    private static final int LENGTH = 6;      

    public static String Creat() throws Exception {
    	StringBuilder noidung = new StringBuilder();
        Random random = new Random();
        
    	while (HoadonDAO.checkMadonhang(noidung.toString())) {
    		noidung = new StringBuilder();
    		for (int i = 0; i < LENGTH; i++) {
                int index = random.nextInt(CHARACTERS.length());
                char randomChar = CHARACTERS.charAt(index);
                noidung.append(randomChar);
            }
    	} 
    	return noidung.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Creat());
    }
}
