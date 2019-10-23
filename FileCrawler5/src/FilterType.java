import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilterType implements Filter {

    private List<String> filterType;

    public FilterType() {
        this.filterType = new ArrayList<>();
    }

    public FilterType(List<String> filterType) {
        this.filterType = filterType;
    }

    public void addFilter(String filter){
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
