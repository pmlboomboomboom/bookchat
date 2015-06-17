package com.server;

import java.net.*;
import java.util.*;
import java.io.*;

import com.common.Message;
import com.common.User;
import com.server.db.sqlHelper;;
/**
 * 
 * 后台服务器
 *
 */

public class Myserver {
    public static void main(String[] args)
    {
        Myserver ms = new Myserver();
        
    }
    public Myserver()
    {
        try {
            System.out.println("在6666端口监听...");
            //在6666端口监听
            ServerSocket ss = new ServerSocket(6666);
            while (true) {
                // 阻塞，等待链接
                Socket s = ss.accept();
                // 接受从客户端发来的对象流
                ObjectInputStream ois = new ObjectInputStream(
                        s.getInputStream());
                User u = (User) ois.readObject();

                System.out.println(u.getName() + " " + u.getPassword());
                
                sqlHelper sqlh = new sqlHelper();
                boolean b = sqlh.register(u.getName(), u.getPassword());
                Message ms = new Message();
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                
                if (b == true) {
                    ms.setMesType("1");
                    oos.writeObject(ms);
                } else {
                    ms.setMesType("2");
                    oos.writeObject(ms);
                    s.close();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
