import java.io.*;

import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;
public class PersonReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();
        final int FIELDS_LENGTH = 5;

        String id, first, last, title;
        int yob;
try {
    File workingDirectory = new File(System.getProperty("user.dir"));
    chooser.setCurrentDirectory(workingDirectory);
    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        selectedFile = chooser.getSelectedFile();
        Path file = selectedFile.toPath();

        InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        int line = 0;
        while (reader.ready()) {
            rec = reader.readLine();
            lines.add(rec);
            line++;
        }
        reader.close();
        //file has been read

        //making the output table
        System.out.println("ID     Firstname       Lastname     Title     YOB");
        System.out.println("===================================================");
        String[] fields;
        for (String a : lines) {
            fields = a.split(",", 0);

            if (fields.length == FIELDS_LENGTH) {
                id = fields[0].trim();
                first = fields[1].trim();
                last = fields[2].trim();
                title = fields[3].trim();
                yob = Integer.parseInt(fields[4].trim());
                System.out.printf("\n%-8s%-15s%-15s%-6s%6d", id, first, last, title, yob);
            } else {
                System.out.println("File was corrupt");
                System.out.println(a);
            }
        }
    }
}
catch (FileNotFoundException e)
{
    System.out.println("File not found");
    e.printStackTrace();
}
catch (IOException e)
{
    e.printStackTrace();
}
}
}


