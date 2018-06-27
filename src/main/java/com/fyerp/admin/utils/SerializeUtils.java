package com.fyerp.admin.utils;

import java.io.*;

public class SerializeUtils {

    public static byte[] serialize(Object o) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outo = new ObjectOutputStream(out);
            outo.writeObject(o);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return out.toByteArray();
    }

    public static Object deserialize(byte[] b) {
        ObjectInputStream oin;
        try {
            oin = new ObjectInputStream(new ByteArrayInputStream(b));
            try {
                return oin.readObject();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }
}
