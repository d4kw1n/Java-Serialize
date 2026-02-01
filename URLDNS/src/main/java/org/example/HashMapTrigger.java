package org.example;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashMap;

public class HashMapTrigger {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        URLStreamHandler urlStreamHandler = new URLStreamHandler() {
            @Override
            protected URLConnection openConnection(URL u) throws IOException {
                return null;
            }
        };
        URL url = new URL(null, "https://tda7ep6ron712n21b4h1vg4wange48sx.oastify.com", urlStreamHandler);
        HashMap map = new HashMap();

        map.put(url, null);

    }
}
