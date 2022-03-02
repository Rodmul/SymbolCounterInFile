import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter source directory path:");
        String sourceDirectory = scanner.next();
        System.out.println("Enter source file name:");
        String sourceFileName = scanner.next();
        ArrayList<Pair> res = new ArrayList<>();
        try{
           File sourceFile = openFileRead(sourceDirectory, sourceFileName);
           res = countSymbols(sourceFile);
        } catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("Enter destination directory path: ");
        String destDirectory = scanner.next();
        System.out.println("Enter destination file name");
        String destFileName = scanner.next();

        File destFile = new File(destDirectory, destFileName);
        printInFileResult(destFile, res);


    }

    private static File openFileRead(String directory, String fileName) throws FileNotFoundException {
        File sourceFile = new File(directory, fileName);
        if (!sourceFile.exists()) throw new FileNotFoundException("File or directory not found! Try again");

        return sourceFile;
    }

    private static ArrayList<Pair> countSymbols(File file){
        ArrayList<Pair> res = new ArrayList<>();
        int temp;

        try(FileReader reader = new FileReader(file)){

            boolean check = true;
            while((temp = reader.read()) != -1){
                for (Pair elem : res){
                    if (temp == (int) elem.getSymbol()){
                        elem.setCount(elem.getCount() + 1);
                        check = false;
                    }
                }
                if (check){
                    res.add(new Pair((char)temp, 1));
                }
                check = true;
            }

        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        return res;
    }

    private static void printInFileResult(File dest, ArrayList<Pair> res){

        try (FileWriter writer = new FileWriter(dest, false)){
            for (Pair elem : res){
                writer.write(String.format("%c - %d", elem.getSymbol(), elem.getCount()));
                writer.append('\n');
            }

            writer.flush();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
