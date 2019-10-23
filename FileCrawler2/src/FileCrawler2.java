import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileCrawler2 {
    public static void main(String[] args) {
        new FileCrawler2();
    }

    public FileCrawler2() {
        File input = new File(".");
        List<File> result = search(input);
        result.forEach(System.out::println);
    }

    public List<File> search(File input) {

        List<File> result = new ArrayList<>();
        File[] files = input.listFiles();
        if (files == null) return result;
        for (File file : files) {
            if(filter(file)) {
                result.add(file);
            }
            if (file.isDirectory()) {
                List<File> subfiles = search(file);
                result.addAll(subfiles);
            }
        }

        return result;
    }

    private boolean filter(File file) {
        return file.getName().endsWith(".java");
    }

}
