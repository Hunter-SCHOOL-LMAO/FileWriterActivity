import java.io.*;
import java.nio.file.*;
public class MyFileWriterTester {
    public static void main(String[] args) {
        try{
        //Base Case
        System.out.println(Files.readString(Paths.get("example.txt")));
        System.out.println(MyFileWriter.hashFile("example.txt"));
        //Empty File
        System.out.println(MyFileWriter.hashFile("empty.txt"));
        //Large File (the entire Bee Movie script)
        //link to script: https://gist.github.com/MattIPv4/045239bc27b16b2bcf7a3a9a4648c08a
        System.out.println(MyFileWriter.hashFile("large.txt"));
        //non-ASCII characters
        System.out.println(MyFileWriter.hashFile("emoji.txt"));
        //non-exsistant file
        System.out.println(MyFileWriter.hashFile("MY-FREE-TIME.txt"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
