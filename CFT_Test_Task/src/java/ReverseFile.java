import java.io.*;

public class ReverseFile {
    private RandomAccessFile randomAccessFile;

    public ReverseFile(File file) {
        try {
            randomAccessFile = new RandomAccessFile(file,"r");
            reverse(file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void reverse(File file) throws IOException {

        int cntOfLines = 0;

        randomAccessFile.seek(0);

        while((randomAccessFile.readLine()) != null) cntOfLines++;

        long[] linesOffSet = new long[cntOfLines];

        int pntr = 0;
        long offSet = 0;
        String tmp;

        randomAccessFile.seek(0);

        while((tmp = randomAccessFile.readLine()) != null) {
            linesOffSet[pntr++] = offSet;
            offSet += tmp.length() + 1;
        }

        File newFile = new File(file.getAbsolutePath() + "1");

        FileWriter fileWriter = new FileWriter(newFile);

        for (int i = linesOffSet.length -1; i>= 0; i--){
            randomAccessFile.seek(linesOffSet[i]);
            String s = randomAccessFile.readLine();
            fileWriter.append(s);
            fileWriter.append("\n");
        }

        fileWriter.close();

        newFile.renameTo(file);
    }
}
