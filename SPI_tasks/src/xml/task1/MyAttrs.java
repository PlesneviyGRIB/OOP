package xml.task1;
import org.xml.sax.Attributes;

public class MyAttrs {
    public void setVal(String val) {
        this.val = val;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public String getValue() {
        return value;
    }

    public String getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

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
