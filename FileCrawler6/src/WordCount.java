import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

class WordCount {


    void wordCount(List<File> files, HashMap<String, Integer> output) {
        for (File file : files) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(file.getPath()), StandardCharsets.UTF_8);
                for (String line : lines) {
                    for (String word : output.keySet()) {
                        final String[] split = line.split(word);
                        output.put(word, output.get(word) + split.length - 1);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
