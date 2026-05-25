import java.io.*;


public class Main {

     public static void main(String[] args) {
        String filePath = "student.csv"; 
        String outputPath = "student.json";
        String line;
        String delimiter = ",";


        // READ FILE
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {

        br.readLine(); // skip header
        bw.write("[");

        String nextLine = br.readLine(); // read first data line

        while (nextLine != null) {
            line = nextLine;
            nextLine = br.readLine(); // next line

            String[] values = line.replace("\"", "").split(delimiter);

            bw.write("\n {");
            bw.write("\n  \"id\": \"" + values[0] + "\",");
            bw.write("\n  \"name\": \"" + values[1] + "\",");
            bw.write("\n  \"course\": \"" + values[2] + "\"");

            if (nextLine != null) {
                bw.write("\n },"); 
            } else {
                bw.write("\n }");  
            }
        }

        bw.write("\n]");

    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
    }

    
}
