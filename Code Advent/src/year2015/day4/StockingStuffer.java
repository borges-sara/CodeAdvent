package year2015.day4;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StockingStuffer {

    private static String getMd5(String input)
    {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer part1And2(int zeroes){
        String input = "yzbqklnj";

        for (var i = 1; i < Integer.MAX_VALUE; i++) {
            var temp = input.concat(String.valueOf(i));
            var md5Hash = getMd5(temp);

            var isMd5 = md5Hash.chars()
                    .mapToObj(Character::toString)
                    .limit(zeroes)
                    .allMatch(c -> c.equals("0"));

            if(isMd5){
                return i;
            }
        }
        return 0;
    }
}
