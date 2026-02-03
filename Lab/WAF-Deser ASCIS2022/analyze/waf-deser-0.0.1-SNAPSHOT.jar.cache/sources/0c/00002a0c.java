package vcs.example.wafdeser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
/* loaded from: waf-deser-0.0.1-SNAPSHOT.jar:BOOT-INF/classes/vcs/example/wafdeser/UserController.class */
public class UserController {
    @GetMapping({"/"})
    public String sayHello() {
        return String.format("Hello ASCIS", new Object[0]);
    }

    @RequestMapping(value = {"/info/{info}"}, method = {RequestMethod.GET})
    public String getUser(@PathVariable("info") String info, @RequestParam(name = "compress", defaultValue = "false") Boolean isCompress) throws IOException {
        String returnData;
        String unencodedData = unEncode(info);
        byte[] data = Base64.getMimeDecoder().decode(unencodedData);
        if (isCompress.booleanValue()) {
            InputStream is = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(new GZIPInputStream(is));
            try {
                User user = (User) ois.readObject();
                returnData = user.getName();
                ois.close();
            } catch (Exception e) {
                returnData = "?????";
            }
        } else {
            returnData = new String(data, StandardCharsets.UTF_8);
        }
        return String.format("Hello %s", returnData);
    }

    private String unEncode(String s) {
        return s.replaceAll("-", "\\r\\n").replaceAll("%3D", "=").replaceAll("%2B", "\\+").replaceAll("_", "/");
    }
}