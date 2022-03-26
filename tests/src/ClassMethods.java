import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class ClassMethods implements Iterable<Map.Entry<String,String>> {
    private final Class<?> classObj;
    private Map<String,String> methodsInfo = new LinkedHashMap<>();

    ClassMethods(Class<?> classObj){
        this.classObj = classObj;
        getMethods();
    }

    private void getMethods(){
        StringBuilder stringBuilder = new StringBuilder();

        for(Constructor<?> constructor: classObj.getDeclaredConstructors()){
            stringBuilder.delete(0,stringBuilder.length());
            if(constructor.getModifiers() > 0) stringBuilder.append(Modifier.toString(constructor.getModifiers())).append(" ");
            stringBuilder.append(constructor.getName()).append(" ");
            stringBuilder.append(getParams(constructor.getParameterTypes()));
            stringBuilder.append(getExceptionSignature(constructor.getExceptionTypes())).append(";");
            methodsInfo.put(stringBuilder.toString(), "none");
        }

        for(Method method: classObj.getDeclaredMethods()){
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.append(Modifier.toString(method.getModifiers())).append(" ");
            stringBuilder.append(method.getReturnType().getSimpleName()).append(" ");
            stringBuilder.append(method.getName()).append(getParams(method.getParameterTypes()));
            stringBuilder.append(getExceptionSignature(method.getExceptionTypes())).append(";");
            methodsInfo.put(stringBuilder.toString(), "none");
        }
    }

    private String getParams(Class<?>[] params){
        StringBuilder stringBuilder = new StringBuilder("(");
        for (Class<?> param: params) stringBuilder.append(param.getSimpleName()).append(" .., ");
        if(stringBuilder.length() > 2) stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());
        return stringBuilder.append(")").toString();
    }

    private String getExceptionSignature(Class<?>[] exceptions){
        StringBuilder stringBuilder = new StringBuilder(" throws ");

        for(Class<?> exception: exceptions) stringBuilder.append(exception.getSimpleName()).append(", ");
        return stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length()).toString().split(" ").length > 2 ? stringBuilder.toString(): "";
    }

    @Override
    public Iterator<Map.Entry<String,String>> iterator() {
        return methodsInfo.entrySet().iterator();
    }

    public static void main(String[] args) throws Exception {
        if(args.length < 1){
            System.out.println("Type names of classes as params");
            System.exit(0);
        }

        for (String str: args) {
            try {
                for (Map.Entry<String, String> entry : new ClassMethods(Class.forName(str)))
                    System.out.println(entry.getKey());
            } catch (Exception e){
                System.out.println("No such class: " + str);
            }
        }
    }
}