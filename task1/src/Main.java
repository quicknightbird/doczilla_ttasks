/*

Имеется корневая папка. В этой папке могут находиться текстовые файлы, а также другие папки.
В других папках также могут находится текстовые файлы и папки (уровень вложенности может оказаться любым).
Найти все текстовые файлы, отсортировать их по имени и склеить содержимое в один текстовый файл.

*/

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<File> fileTxt = new ArrayList<>();
        SearchTextFilesAndFolders(new File("C:\\Users\\bmth\\IdeaProjects\\doczilla_ttasks\\task1\\folder1"),fileTxt);
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        fileTxt.sort((o1, o2) -> {
            String s1 = o1.getName();
            String s2 = o2.getName();
            return s1.compareTo(s2);
        });
        for(File file:fileTxt){
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                //out.println(line);
                try(FileWriter writer = new FileWriter("note4.txt", true))
                {
                    writer.write(line+ "\n" );
                    writer.flush();
                }
                catch (IOException e){
                    e.printStackTrace();
                }


            }
        }
//        for(File file:fileTxt) {
//            System.out.println(file.getName());
//        }
//        fileTxt.forEach(System.out::println);


    }
    private static void SearchTextFilesAndFolders(File path, List<File> fileTxt){
        if(path.isDirectory()){
            File[] directory = path.listFiles();
            assert directory != null;
            Arrays.sort(directory, Comparator.comparingLong(File::lastModified));
            for (File file:directory){
                if(file.isDirectory()){
                    SearchTextFilesAndFolders(file, fileTxt);
                }else{
                    if(file.getName().endsWith(".txt")){
                        fileTxt.add(file);
                    }
                }
            }

        }
    }

}