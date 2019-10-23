import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileCrawler6 {
    public static void main(String[] args) {
        new FileCrawler6(args);
    }

    HashMap<String, Integer> wordsToSearch;

    public FileCrawler6(String[] args) {
        wordsToSearch = new HashMap<>();
        List<Filter> filters = getFiltersForArgs(args);
        File input = new File(".");
        List<File> result = search(input, filters);
        result.forEach(System.out::println);

        if (!wordsToSearch.isEmpty()) {
            new WordCount().wordCount(result, wordsToSearch);
            for (String word : wordsToSearch.keySet()) {
                System.out.println("key: " + word + " value: " + wordsToSearch.get(word));
            }
        }
    }

    public List<File> search(File input, List<Filter> filters) {

        List<File> result = new ArrayList<>();
        boolean isFileFiltered = false;
        for (Filter filter : filters) {
            if (!filter.filter(input)) {
                isFileFiltered = true;
                break;
            }
        }
        if (!isFileFiltered) {
            result.add(input);
        }
        if (input.isDirectory()) {
            File[] files = input.listFiles();
            if (files == null) return result;
            for (File file : files) {
                List<File> subfiles = search(file, filters);
                result.addAll(subfiles);
            }
        }

        return result;
    }

    private List<Filter> getFiltersForArgs(String[] args) {
        ArrayList<Filter> result = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-f":
                    FilterType filterType = new FilterType();
                    while (i < args.length) {
                        try{
                            if(args[i + 1].charAt(0) != '-') {
                                i++;
                                filterType.addFilter(args[i]);
                            }else{
                                break;
                            }
                        } catch(IndexOutOfBoundsException e){
                            //sonst gibts eine Endlosschleife
                            i++;
                        }
                    }
                    result.add(filterType);

                    break;
                case "-s":
                    try {
                        long fileSize = Long.parseLong(args[i + 1]);
                        result.add(new FilterSize(fileSize));
                        i++;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                case "-w":
                    while (i < args.length) {
                        try{
                            if(args[i + 1].charAt(0) != '-') {
                                i++;
                                wordsToSearch.put(args[i], 0);
                            }else{
                                break;
                            }
                        } catch(IndexOutOfBoundsException e){
                            //sonst gibts eine Endlosschleife
                            i++;
                        }
                    }

                    break;
                default:
                    System.out.println("Parameter -f um nach einer Dateiendung zu filtern");
                    System.out.println("Parameter -s um nach einer Dateigrösse zu filtern");
                    break;
            }
        }
        return result;
    }


}
