import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Search_class {
    static int[] make_hash(String str) {
        int len = str.length();
        str = new StringBuilder(str).reverse().toString(); //разворот строки (123 -> 321)

        int[] res = new int[len+1];
        for(int i = 0; i<len+1;i++)
            res[i] = i;

        int i = 1;
        int j = 0;

        while(i<len) {
            while(j<len) {
                if (str.charAt(i) == str.charAt(j))
                    res[j] = res[i];
                j++;
            }
            j=0;
            i++;
        }
        if (res[0]==0) res[0] = res[len];
        return res;
    }

    static int[] Search(String str, String path) throws IOException {
        FileReader reader = new FileReader(path);
        if(str.length() == 0){
            int[] tmp = new int[0];
            return tmp;
        }
        int[] hash = make_hash(str);
        str = new StringBuilder(str).reverse().toString();
        int len = str.length();
        int[] buff = new int[len];
        int tmp;
        int cnt = 0;
        int p = 0;
        int ind = 0;
        int i = 0;
        int sdvig = 0;
        ArrayList<Integer> res = new ArrayList();

        while((tmp = reader.read()) != -1){
            ind = cnt % len;
            buff[ind] = tmp;

            while ((i < len) && (str.charAt(i) == buff[ind])){
                ind = (ind - 1 + len)% len;
                i++;
            }
            if(i == len){
                res.add(cnt - len +1);
            }
            else{
                if(i == 0){
                    while((p<len) && (tmp != str.charAt(p)))
                    {
                        p++;
                    }
                    if(p==len) sdvig = hash[len];
                    else{
                        sdvig = hash[p];
                    }
                }
                else{
                    sdvig = hash[0];
                }
            }

            i = 0;

            while((sdvig-1 > 0) && ((tmp = reader.read()) != -1)){
                cnt++;
                ind = cnt % len;
                buff[ind] = tmp;
                sdvig--;
            }

            p = 0;
            i = 0;
            sdvig = 0;
            cnt++;
        }
        if(cnt==0){
            int[] tmp1 = new int[0];
            return tmp1;
        }
        int[] res1 = new int[res.size()];
        for(int j=0; j<res.size(); j++) {
            res1[j] = res.get(j);
        }
        return(res1);
    }
}