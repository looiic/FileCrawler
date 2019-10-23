import java.io.File;

public class FilterSize implements Filter {

    private long filterSize;

    public FilterSize(long filterSize) {
        this.filterSize = filterSize;
    }

    @Override
    public boolean filter(File file) {
        if(!file.isDirectory() && file.length() <= filterSize) return true;
        return false;
    }
}
