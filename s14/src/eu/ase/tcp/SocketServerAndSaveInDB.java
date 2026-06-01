package eu.ase.tcp;

import eu.ase.iojson.User;
import eu.ase.sqldao.SqlDAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class SocketServerAndSaveInDB {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        SqlDAO sqlDAO = SqlDAO.getInstance();

        int port = 7997;
        try {
            serverSocket = new ServerSocket(7997);
            System.out.println("Server listens in port " + port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while(true) {
            try {
                Socket client = serverSocket.accept();
                Runnable rClient = () -> {
                    try {
                        ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                        ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

                        List<User> users = null;
                        Object list = in.readObject();
                        if(list instanceof List<?>) {
                            users = (List<User>) list;
                        }
                        for(User u: users) {
                            System.out.println("user = " + u.getJsonString());
                            sqlDAO.insertIntoDB(u.getId(), u.getName(), u.getEmail(), u.getPassword());
                        }
                        out.writeUTF("OK!");
                        out.close();
                        client.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                };
                Thread thread = new Thread(rClient);
                thread.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
