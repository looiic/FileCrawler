import java.io.File;

public class FilterSize implements Filter {

    private long filterSize;

    FilterSize(long filterSize) {
        this.filterSize = filterSize;
    }

    @Override
    public boolean filter(File file) {
        return (!file.isDirectory() && file.length() <= filterSize);
    }
}
