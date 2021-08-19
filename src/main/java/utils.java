import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class utils {
    static final String path = "src//main//resources//";

    public static String readFile(String fileName) {
        String fileContent = "";
        try(FileReader fr = new FileReader(getPath(fileName))){
            while(fr.ready()) {
                fileContent += (char)fr.read();
            }
        }catch (IOException e) {
            System.out.println("Error while file reading: " + e);
        }
        return fileContent;
    }
    public static String getPath(String fileName) {
        File f = new File(path + "//" + fileName);
        return f.getAbsolutePath();
    }
}
