import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.ArrayList;
import java.util.Scanner;
public class PersonGenerator {
    public static void main(String[] args) {
        ArrayList<String> personCSVData = new ArrayList<>();
        String id = "";
        String first = "";
        String last = "";
        String title = "";
        String CSVPersonRecord = "";
        int yearOfBirth = 0;

        Boolean done = false;
        Boolean enteringDone = false;
        Scanner input = new Scanner(System.in);
        do{
        do {
            id = SafeInput.getNonZeroLenString(input, "Please enter your ID");
            System.out.println("ID is:" + id);
            first = SafeInput.getNonZeroLenString(input, "What is your first name?");
            System.out.println("Your first name is " + first);
            last = SafeInput.getNonZeroLenString(input, "What is your last name?");
            System.out.println("Your last name is " + last);
            title = SafeInput.getNonZeroLenString(input, "What is your preferred title (ms., mr, dr, etc)?");
            System.out.println("Your title is " + title);
            yearOfBirth = SafeInput.getRangedInt(input, "What is your year of birth", 1000, 9999);
            System.out.println("Your birthday is " + yearOfBirth);
            CSVPersonRecord = id + "," + first + "," + last + "," + title + "," + yearOfBirth;
            personCSVData.add(CSVPersonRecord);
            enteringDone = SafeInput.getYNConfirm(input, "have you finished entering the person's data?");
        } while (!enteringDone);
            done = SafeInput.getYNConfirm(input, "Are you done entering persons?");
        }
        while (!done);
        for (String p : personCSVData)
            System.out.println(p);
//* Writing the CVS file *//
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonData.txt");

        try
        {

            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(String rec : personCSVData)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();

            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

