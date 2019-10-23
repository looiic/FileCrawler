import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileCrawler3 {
    public static void main(String[] args) {
        new FileCrawler3(args);
    }

    public FileCrawler3(String[] filter) {
        File input = new File(".");
        List<File> result = search(input, filter);
        result.forEach(System.out::println);
    }

    public List<File> search(File input, String[] filter) {

        List<File> result = new ArrayList<>();
        File[] files = input.listFiles();
        if (files == null) return result;
        for (File file : files) {
            if(filter(file, filter)) {
                result.add(file);
            }
            if (file.isDirectory()) {
                List<File> subfiles = search(file, filter);
                result.addAll(subfiles);
            }
        }

        return result;
    }

    private boolean filter(File file, String[] filters) {
        if (filters.length == 0 && !file.isDirectory()) return true;
        for (String filter: filters) {
            if (file.getName().toLowerCase().endsWith(filter.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
