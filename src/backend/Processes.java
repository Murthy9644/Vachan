package backend;

public class Processes{

    public static Boolean authenticateUser(String username, String pswd){
        String redgpswd = ServerCommunication.getLoginInfo(username);

        if (redgpswd == null) return null;
        if (pswd.equals(redgpswd)) return true;

        return false;
    }
}