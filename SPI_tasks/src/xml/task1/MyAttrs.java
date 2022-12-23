package xml.task1;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.xml.sax.Attributes;

@Data
@NoArgsConstructor
public class MyAttrs {
    private String val;
    private String value;
    private String count;
    private String name;
    private String id;

    public static MyAttrs parseAttrs(Attributes attributes){
        MyAttrs myAttrs = new MyAttrs();

        for(int i=0; i<attributes.getLength(); i++) {

            if(attributes.getQName(i).equals("val") ) myAttrs.setVal(attributes.getValue(i));
            if(attributes.getQName(i).equals("value") ) myAttrs.setValue(attributes.getValue(i));

            if(attributes.getQName(i).equals("count")) myAttrs.setCount(attributes.getValue(i));
            if(attributes.getQName(i).equals("name")) myAttrs.setName(attributes.getValue(i));
            if(attributes.getQName(i).equals("id")) myAttrs.setId(attributes.getValue(i));
        }

        return myAttrs;
    }
}
