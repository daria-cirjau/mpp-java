package eu.ase.tcp;

import eu.ase.iojson.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketClientReadFromFile {
    public static List<User> readJSONFile(String fileName) throws IOException, JSONException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<User> users = new ArrayList<>();

        String line = null;
        String ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(line);
            stringBuilder.append(ls);
            String myNodeJSON = stringBuilder.toString();
            JSONObject jsonObject = new JSONObject(myNodeJSON);
            User u = new User(Integer.parseInt("" + jsonObject.get("id")), "" + jsonObject.get("name"),
                    "" + jsonObject.get("email"), "" + jsonObject.get("password")
            );
            users.add(u);
        }
        reader.close();
        return users;
    }

    public static void sendUsersTOServer(String ip, int port, List<User> users) throws IOException {
        Socket s = new Socket(ip, port);
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        out.writeObject(users);
        System.out.println(in.readUTF());
        if(out != null) {
            out.close();
        }
        if(s != null) {
            s.close();
        }
    }

    public static void main(String[] args) {
        try {
            List<User> users = readJSONFile("myUsers.json");
            sendUsersTOServer("127.0.0.1", 7997, users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
