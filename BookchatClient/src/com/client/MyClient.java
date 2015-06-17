package com.client;

import java.net.*;
import java.util.*;
import java.io.*;
import com.common.*;

public class MyClient {
    public static void main(String[] args) {
        MyClient mc = new MyClient();
    }
    
    public MyClient()
    {
        User u = new User();
        u.setName("kevin2");
        u.setPassword("1243534567");
        boolean bmyc = sendLoginInfoToServer(u);
        System.out.println(bmyc);
    }
    
    public boolean sendLoginInfoToServer(Object o)
    {
        boolean b = false;
        try {
            Socket s = new Socket("127.0.0.1", 6666);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(o);
            
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            Message ms = (Message)ois.readObject();
            
            //1ÎªµÇÂ½³É¹¦
            if (ms.getMesType().equals("1")) {
                b = true;
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return b;
    }
}
