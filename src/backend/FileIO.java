package backend;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

class UserDataStructure{
    public String username;
    public String password;
}

public class FileIO{

    // Global things for this class:
    public String username, password;

    // Paths:
    private static String folderpath;
    private static String userdatapath;

    // File / Folder objects:
    private static File userdata;
    private static File folder;

    // Instances:
    private static ObjectMapper jsonmapper = new ObjectMapper();

    public Boolean chkUserData() throws IOException{

        if (userdata.exists()) return true;
                
        return false;
    }

    public String[] readUserData(){

        try {
            jsonmapper = new ObjectMapper();
            UserDataStructure data = jsonmapper.readValue(userdata, UserDataStructure.class);
            String userdataarr[] = {data.username, data.password};

            return userdataarr;
        }
        catch (Exception e){e.printStackTrace(); return null;}
    }

    public void writeUserData(String data[]){
        this.username = data[0];
        this.password = data[1];

        try {
            jsonmapper = new ObjectMapper();
            jsonmapper.writerWithDefaultPrettyPrinter().writeValue(userdata, this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public FileIO(){
        String appdata = System.getenv("APPDATA");
        folderpath = Paths.get(appdata, "Vachan").toString();
        folder = new File(folderpath);
        userdatapath = Paths.get(folderpath, "userdata.txt").toString();

        if (! folder.exists()) folder.mkdir();

        userdata = new File(userdatapath);
    }
}