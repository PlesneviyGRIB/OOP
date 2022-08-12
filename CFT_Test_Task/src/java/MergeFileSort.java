import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MergeFileSort {
    private Data data;
    private List<Scanner> scanners = new ArrayList<>();
    private String hash = "XC8pKEkEZ=0r@6Ak6$E9q!qcuR)##tS++GYSQQVhmaj7ptD&A;+irt$v=d#+&7Ukx2M)%#F;#1WV3Uw9yYg9KIPzTd4a%$L@86;JNJcjYPa5urDdgic@G+oc";

    public MergeFileSort(Data data) {
        this.data = data;

        data.getInFiles().forEach(f ->{
            try {
                scanners.add(new Scanner(f));
            } catch (FileNotFoundException e) {
                System.out.println("Can not open input file: " + f.getName());
                e.printStackTrace();
            }
        });

        if(data.isIntType()) mergeIntSort(data.getOutFile());
        else mergeStringSort(data.getOutFile());

        if(data.isReverseOrder()) reverseFile(data.getOutFile());
    }

    private void mergeIntSort(File out) {
        long[] currentValue = initArr();

        long min = Arrays.stream(currentValue).min().orElse(Long.MAX_VALUE);

        try(FileWriter fileWriter = new FileWriter(out)) {
            while (min < Long.MAX_VALUE) {
                int i = Arrays.stream(currentValue).boxed().toList().indexOf(min);

                fileWriter.append(String.valueOf(min) + "\n");

                if (scanners.get(i).hasNextLine()) {
                    long tmp = checkIntValue(scanners.get(i).nextLine(), i);
                    if (tmp >= currentValue[i]) currentValue[i] = tmp;
                    else {
                        currentValue[i] = Long.MAX_VALUE;
                        scanners.get(i).close();
                        System.out.println("Wrong data order: " + data.getInFiles().get(i).getName());
                    }
                } else {
                    currentValue[i] = Long.MAX_VALUE;
                    scanners.get(i).close();
                }
                min = Arrays.stream(currentValue).min().orElse(Long.MAX_VALUE);
            }
        } catch (IOException e){
            System.out.println("Can not open output file: " + out.getName());
            e.printStackTrace();
            System.exit(0);
        }
    }

    private long[] initArr(){
        long[] currentValue = new long[scanners.size()];
        Arrays.fill(currentValue, Long.MAX_VALUE);

        for(int i = 0; i < scanners.size(); i++){
            if(scanners.get(i).hasNextLine()){
                currentValue[i] = checkIntValue(scanners.get(i).nextLine(), i);
            }
        }
        return currentValue;
    }

    private long checkIntValue(String value, int index){
        long tmp = Long.MAX_VALUE;
        try {
            tmp = Integer.parseInt(value);
        } catch (NumberFormatException e){
            System.out.println("Wrong data format: " + data.getInFiles().get(index).getName());
            scanners.get(index).close();
        }
        return tmp;
    }

    private String checkStringValue(String value, int index){
        String tmp = hash;
        if(!value.contains(" "))
            tmp = value;
        else {
            System.out.println("Wrong data format: " + data.getInFiles().get(index).getName());
            scanners.get(index).close();
        }
        return tmp;
    }

    private void mergeStringSort(File out){
        String[] currentValue = new String[scanners.size()];
        Arrays.fill(currentValue, hash);

        for(int i = 0; i < scanners.size(); i++){
            if(scanners.get(i).hasNextLine()){
                currentValue[i] = checkStringValue(scanners.get(i).nextLine(), i);
            }
        }


        String min = Arrays.stream(currentValue).filter(s -> !s.equals(hash)).min(String::compareTo).orElse(hash);

        try(FileWriter fileWriter = new FileWriter(out)) {
            while (!min.equals(hash)) {
                int i = Arrays.stream(currentValue).toList().indexOf(min);

                fileWriter.append(min + "\n");

                if (scanners.get(i).hasNextLine()) {
                    String tmp = checkStringValue(scanners.get(i).nextLine(), i);
                    if (tmp.compareTo(currentValue[i]) >= 0 ) currentValue[i] = tmp;
                    else {

                        currentValue[i] = hash;
                        scanners.get(i).close();
                        System.out.println("Wrong data order: " + data.getInFiles().get(i).getName());
                    }
                } else {
                    currentValue[i] = hash;
                    scanners.get(i).close();
                }
                min = Arrays.stream(currentValue).filter(s -> !s.equals(hash)).min(String::compareTo).orElse(hash);
            }
        } catch (IOException e){
            System.out.println("Can not open output file: " + out.getName());
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void reverseFile(File file){
        new ReverseFile(file);
    }
}
