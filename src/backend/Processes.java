package backend;

public class Processes{

    public static Boolean authenticateUser(Long id, String pswd){
        String redgpswd = ServerCommunication.getLoginInfo(id);

        if (pswd.equals(redgpswd)) return true;

        return false;
    }
}