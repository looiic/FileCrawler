import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileCrawler1 {
    public static void main(String[] args) {
        new FileCrawler1();
    }

    public FileCrawler1() {
        File input = new File(".");
        List<File> result = search(input);
        result.forEach(System.out::println);
    }

    private List<File> search(File input) {

        List<File> result = new ArrayList<>();
        File[] files = input.listFiles();
        if (files == null) return result;
        for (File file : files) {
            result.add(file);
            if (file.isDirectory()) {
                List<File> subfiles = search(file);
                result.addAll(subfiles);
            }
        }

        return result;
    }

}
