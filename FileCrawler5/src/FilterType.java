import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilterType implements Filter {

    private List<String> filterType;

    FilterType() {
        this.filterType = new ArrayList<>();
    }

    void addFilter(String filter){
        filterType.add(filter);
    }

    @Override
    public boolean filter(File file) {
        for(String filter: filterType){
            if (file.getName().toLowerCase().endsWith(filter)) return true;
        }
        return false;
    }
}
