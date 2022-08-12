import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Data {
    private boolean isIntType;
    private boolean isReverseOrder;
    private File outFile;
    private ArrayList<File> inFiles = new ArrayList<>();

    public Data(String[] args) {
        parseArgs(args);
    }

    public boolean isIntType() {
        return isIntType;
    }

    public boolean isReverseOrder() {
        return isReverseOrder;
    }

    public File getOutFile() {
        return outFile;
    }

    public ArrayList<File> getInFiles() {
        return inFiles;
    }

    private void parseArgs(String[] args) {
        boolean outFileSet = false;
        for(String arg: args){
            if(arg.toCharArray()[0] == '-') {
                if (arg.equals("-i")) isIntType = true;
                if (arg.equals("-d")) isReverseOrder = true;
                continue;
            }

            if(!outFileSet) {
                outFile = new File(arg);
                outFileSet = true;
            }
            else inFiles.add(new File(arg));
        }
    }
}