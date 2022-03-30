package tool;

import java.lang.reflect.*;
import java.util.*;

@Descroption(ds = "class for parse other given classes with annotations")
public class ClassDissect {
    private Class<?> objClass;
    private String pattern = "([a-zA-Z]+\\.)+";

    public ClassDissect (Class<?> objClass){
        this.objClass = objClass;
    }

    private Map.Entry<String,String> getClassName() {
        return new Map.Entry<>() {
            @Override
            public String getKey() { return objClass.toGenericString().replaceAll(pattern, ""); }
            @Override
            public String getValue() { return objClass.getAnnotation(Descroption.class) == null? "": objClass.getAnnotation(Descroption.class).ds(); }
            @Override
            public String setValue(String value) { return getValue();}
        };
    }

    private <T> Map<String,String> getEntity(Class<T> type) {
        Map<String,String>  methods = new LinkedHashMap<>();

        T[] types = null;
        if(type == Field.class) types = (T[])objClass.getDeclaredFields();
        if(type == Constructor.class) types = (T[])objClass.getDeclaredConstructors();
        if(type == Method.class) types = (T[])objClass.getDeclaredMethods();

        for (T entity: types) {
            Descroption descroption = ((AnnotatedElement)entity).getAnnotation(Descroption.class);
            methods.put(entity.toString().replaceAll(pattern,"") + ";", descroption == null ? "": descroption.ds());
        }
        return methods;
    }

    @Descroption(ds = "method for visualize parsed classes")
    public String getParsedClass(){
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();

        stringBuilder.append(getAnnotation(getClassName().getValue())).append(getClassName().getKey()).append("{\n");

        for(Map.Entry entry: getEntity(Field.class).entrySet()){
            stringBuilder1.append(getAnnotation(entry.getValue().toString())).append(entry.getKey()).append("\n");
        }
        stringBuilder1.append("\n");

        for(Map.Entry entry: getEntity(Constructor.class).entrySet()){
            stringBuilder1.append(getAnnotation(entry.getValue().toString())).append(entry.getKey()).append("\n");
        }
        stringBuilder1.append("\n");

        for(Map.Entry entry: getEntity(Method.class).entrySet()){
            stringBuilder1.append(getAnnotation(entry.getValue().toString())).append(entry.getKey()).append("\n");
        }

        for (String str: stringBuilder1.toString().split("\n"))
            stringBuilder.append("    ").append(str).append("\n");

        return stringBuilder.append("}").toString();
    }

    private String getAnnotation(String str){
        return str.length() > 0 ? "\n>>" + str + "\n": "";
    }

    public static void main(String[] args) throws ClassNotFoundException {
        if(args.length < 1) {
            System.out.println("type classes for parse as params");
            System.exit(0);
        }

        for(String str: args){
            try {
                System.out.println(new ClassDissect(Class.forName(str)).getParsedClass());
            }
            catch (ClassNotFoundException e) {
                System.out.println("class not found: " + str);
            }
        }
        System.out.println();
    }
}