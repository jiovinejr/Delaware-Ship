package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Audit {

    public static void log(String name) {
        File audit = new File("audit.txt");
        if (audit.exists()) {
            try(PrintWriter writer = new PrintWriter(new FileOutputStream(audit, true))) {
                writer.println(name);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        } else {
            try(PrintWriter writer = new PrintWriter(audit)) {
                writer.println(name);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
    }
}
