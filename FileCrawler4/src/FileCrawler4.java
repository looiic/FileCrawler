import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileCrawler4 {
    public static void main(String[] args) {
        new FileCrawler4(args);
    }

    public FileCrawler4(String[] filter) {
        File input = new File(".");
        long filterSizeByte = 0;
        if(filter.length >= 1){
            try{
                filterSizeByte = Long.parseLong(filter[0]);
            } catch(NumberFormatException e){
                System.err.println("Bitte eine Nummer als Argument mitgeben");
            }
        }
        List<File> result = search(input, filterSizeByte);
        result.forEach(System.out::println);
    }

    public List<File> search(File input, long filterSizeBytes) {

        List<File> result = new ArrayList<>();
        if(filterFilesize(input, filterSizeBytes)) {
            result.add(input);
        }
        if (input.isDirectory()) {
            File[] files = input.listFiles();
            if (files == null) return result;
            for (File file : files) {
                List<File> subfiles = search(file, filterSizeBytes);
                result.addAll(subfiles);
            }
        }

        return result;
    }

    private boolean filterFilesize(File file, long filterSizeBytes) {
        if(!file.isDirectory() && filterSizeBytes == 0) return true;
        if(!file.isDirectory() && file.length() <= filterSizeBytes) return true;
        return false;
    }


}
