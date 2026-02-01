package org.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class URLTrigger {
    public static void main(String[] args) throws MalformedURLException {
        URLStreamHandler urlStreamHandler = new URLStreamHandler() {
            @Override
            protected URLConnection openConnection(URL u) throws IOException {
                return null;
            }
        };
        URL url = new URL(null, "https://s7068o0qim10wmw053b0pfyv4mady4mt.oastify.com", urlStreamHandler);
        url.hashCode();
    }
}
