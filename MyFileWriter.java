import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
public class MyFileWriter {
    public static void main(String[] args) {
        String data = "Hello, World!";
        String fileName1 = "example.txt";
        String fileName2 = "example2.txt";
        String fileName3 = "example3.txt";
        String fileName4 = "example4.txt";
        String fileName5 = "example5.txt";

        // 1. Using FileWriter
        try (FileWriter writer = new FileWriter(fileName1)) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. Using BufferedWriter
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName2))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. Using FileOutputStream
        try (FileOutputStream outputStream = new FileOutputStream(fileName3)) {
            outputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4. Using BufferedOutputStream
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName4))) {
            bufferedOutputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 5. Using Files (java.nio.file)
        try {
            Files.write(Paths.get(fileName5), data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 0.4 HW
        printFileSize(fileName1);
    }
    public void makeSecretFile(){
        try{
            Files.write(Paths.get(".secretFiles.txt"), "IT'S WANI WEDNESDAY".getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void makePublicFileInSecretFolder(){
         try{
            Files.write(Paths.get("/.ultraUnacessable/lessSecretFiles.txt"), "IT'S WANI WEDNESDAY".getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    // Calculate and print the file size using the File class
    private static void printFileSize(String... fileNames) {
        long totalSize = 0;
        for (String fileName : fileNames) {
            File file = new File(fileName);
            if (file.exists()) {
                totalSize += file.length();
            }
        }
        System.out.println("Total size of all files: " + totalSize + " bytes");
    }
    /**
    * Reads a text file and returns its contents as a string.
    * 
    * @param filePath the path to the file
    * @return the contents of the file as a string
    * @throws IOException if an I/O error occurs
    */
    public static String stringify(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }
    /*
     * Kudos to GeeksForGeeks.org for teaching me how to use MessgeDigest and best practices for 
     * converting stuff into Hexidecimal
     */
    public static String hashFile(String filePath){
        try{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] intermediaryArray = md.digest(Files.readString(Paths.get(filePath), StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8));
        BigInteger hashResult = new BigInteger(1, intermediaryArray);
        StringBuilder output = new StringBuilder(hashResult.toString(16));
        while (output.length() < 64)
        {
            output.insert(0, '0');
        }

        return output.toString();
    } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }
}