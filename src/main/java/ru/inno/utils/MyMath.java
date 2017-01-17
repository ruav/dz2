package ru.inno.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Alexander Rudnev
 */
public class MyMath {

    public static String MD5Salt(String arg){
        return new Md5PasswordEncoder().encodePassword(arg,"123456");
    }

    public static String createMD5(String arg){
        String pass = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(arg.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            pass = DatatypeConverter.printHexBinary(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return pass;
    }
}
