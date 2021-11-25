import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Parser{
    private String[] args;
    private int posAdd = -1;
    private int posRm = -1;
    private int posShow = -1;

    Parser(String[] _args) {
        args = _args;
        show();
        rm();
        add();
    }

    private void add(){
        String pattern = "-add";

        for(int i = posAdd + 1; i < args.length - 2; i++)
            if((pattern.equals(args[i])) && (i > posAdd) && (args[i+1].charAt(0) != '-') && (args[i+2].charAt(0) != '-')){
                posAdd = i;
                return;
            }
        posAdd = -1;
    }

    private void rm(){
        String pattern = "-rm";

        for(int i = posRm + 1; i < args.length - 1; i++)
            if((pattern.equals(args[i])) && (i > posRm) && (args[i+1].charAt(0) != '-')) {
                posRm = i;
                return;
            }
        posRm = -1;
    }

    private void show(){
        String pattern = "-show";

        for(int i = posShow + 1; i < args.length; i++)
            if(pattern.equals(args[i])) {
                posShow = i;
                return;
            }
        posShow = -1;
    }

    public int ind(){
        int res = -1;
        if(((posShow < posRm) || (posRm == -1)) && ((posShow < posAdd) || (posAdd == -1)) && (posShow != -1)){
            res = posShow;
            show();
            return res;
        }
        if(((posRm < posShow) || (posShow == -1)) && ((posRm < posAdd) || (posAdd == -1)) && (posRm != -1)){
            res = posRm;
            rm();
            return res;
        }
        if(((posAdd < posRm) || (posRm == -1)) && ((posAdd < posShow) || (posShow == -1)) && (posAdd != -1)){
            res = posAdd;
            add();
            return res;
        }
        return res;
    }

    public Param isParamShow(int r) {
        Date date0 = new Date();
        Date date1 = date0;
        Param param;

//        if((r+1 < args.length) && (args[r + 1].charAt(0) != '-'))
//          param = new Param();

        if((r+2 < args.length) && (args[r + 1].charAt(0) != '-') && (args[r + 2].charAt(0) != '-')){
            try {
                date0 = new SimpleDateFormat("dd:MM:yyyy HH:mm", Locale.ENGLISH).parse(args[r + 1]);
                date1 = new SimpleDateFormat("dd:MM:yyyy HH:mm", Locale.ENGLISH).parse(args[r + 2]);
                param = new Param(date0,date1);
                r += 2;
                while ((r + 1 < args.length) && (args[r+1].charAt(0) != '-')) {
                    param.addWord(args[++r]);
                }
                return param;
            }
            catch (Exception e) {};
        } return null;
    }
}