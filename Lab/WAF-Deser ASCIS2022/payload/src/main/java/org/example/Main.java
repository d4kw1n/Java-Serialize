package org.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;


public class Main {
    public static void main(String[] args) throws IOException {
        User user = new User("d4kw1n", 22);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (GZIPOutputStream gzos = new GZIPOutputStream(baos);
             ObjectOutputStream oos = new ObjectOutputStream(gzos)) {
                oos.writeObject(user);
        }
        byte[] compressedData = baos.toByteArray();
        String base64payload = Base64.getEncoder().encodeToString(compressedData);
        base64payload = doEncode(base64payload);
        System.out.println(base64payload);
    }

    private static String doEncode(String s) {
        return s.replace("/", "_")
                .replace("+", "%2B")
                .replace("=", "%3D")
                .replace("\r\n", "-");
    }
}
