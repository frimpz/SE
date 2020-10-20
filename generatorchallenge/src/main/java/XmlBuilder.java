import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.text.WordUtils;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class XmlBuilder {

   //xml string
   private String xml ="";
   private String entity ="";
   private Class<?> cls = null;


    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public XmlBuilder(Class<?> cls) {
        this.cls = cls;
        this.setEntity(cls.getSimpleName());
    }

    public String buildXml(){

        this.setXml(xml,"<h:form id=\"form"+getEntity()+"\">\n");


        Method[] methods = cls.getDeclaredMethods();


        for (Method i : methods){
            String closetag = "";
        if(getFieldName(i)!=null) {
            setXml(xml,"\t<util:");
            String inputfield = getInputField(i.getReturnType().getSimpleName());
            setXml(xml,inputfield+"\n");
            closetag = "\t />\n";
        }

        //create column
            try {
                for (Map.Entry<String,String> entry : getAnnotationValues(i.getDeclaredAnnotations()).entrySet()) {
                    setXml(xml,"\t\t"+entry.getKey()+" = "+entry.getValue()+"\n");
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


            setXml(xml,closetag);
        }

        setXml(xml,"</h:form>");

        return xml;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    //This method is used to build the xml string
    public  void setXml(String xml,String additionalString){
        this.xml =  xml + additionalString;
    }

    /**   This method is used to get only getter methods
     *   Using getWriteMethod instead of getReadMethod will return setter methods
     *   Follows java beans convention, so may not work for classes which do not follow java beans conventions.
     */

    public  String getFieldName(Method method)
    {
        try
        {
            Class<?> clazz=method.getDeclaringClass();
            BeanInfo info = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] props = info.getPropertyDescriptors();
            for (PropertyDescriptor pd : props)
            {
                if(method.equals(pd.getReadMethod()))
                {
                    return pd.getName();
                }
            }
        }
        catch (IntrospectionException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return null;
    }


    /**
     * Method to get only supported annotation type
     * primitive, date and string,  data types for annotation values
     * eg: nullable : boolean, lenght: Integer; name:String
     */
    public  boolean isSupportedType(Class<?> clazz){
        return ClassUtils.isPrimitiveOrWrapper(clazz) || clazz.getSimpleName().equals("String")
                || clazz.getSimpleName().equals("Date");
    }

    /**
     * Method to get only supported annotation type
     * primitive, date and string,  data types for annotation values
     * eg: nullable : boolean, lenght: Integer; name:String
     */
    public  String getSupportedType(Class<?> clazz){
        if(ClassUtils.isPrimitiveOrWrapper(clazz)){
            System.out.println(clazz.getSimpleName());
            return  ClassUtils.wrapperToPrimitive(clazz).getSimpleName();
        }
        else if(clazz.getSimpleName().equals("String")){
            System.out.println(clazz.getSimpleName());
            return "String";
        }
        else if(clazz.getSimpleName().equals("Date")) {
            System.out.println(clazz.getSimpleName());
            return "Date";
        }
        System.out.println(clazz.getSimpleName());
        return "";
    }

    /**
     * This method returns a map of annotations and their values.
     * The annotations and their values are used to build the xml
     */
    public  HashMap<String,String> getAnnotationValues(Annotation[] mthdannotations) throws InvocationTargetException, IllegalAccessException {
        HashMap<String,String> data = new HashMap<>();
        for (Annotation j : mthdannotations) {
            Class<? extends Annotation> type = j.annotationType();
            Method[] mtds = type.getDeclaredMethods();
            for (Method method : mtds) {
                Object value = method.invoke(j, (Object[])null);
                if(!value.equals("")) {
                    if(method.getDefaultValue()!=null) {
                        Map<String,String> mdata;
                        if (!method.getDefaultValue().equals(value) && isSupportedType(value.getClass()) == true) {
                            mdata = toXmlValue(method.getName() ,value.toString(),method.getDefaultValue().toString());
                            if(mdata!=null) {
                                data.putAll(mdata);
                            }
                        }
                        else if(isSupportedType(value.getClass()) == true){
                            mdata = toXmlValue(j.annotationType().getSimpleName(),"","");
                            //all unsupported types will return null
                            if(mdata!=null) {
                                data.putAll(mdata);
                            }
                        }
                    }
                }
            }
        }
        return data;
    }

    public  Map<String,String> toXmlValue(String field,String mvalue,String defValue){
        String value = mvalue.replaceAll("\\s","");
        if(value.equals("")){
             value = defValue;
        }
        Map<String,String> pair = new HashMap<>();
        if(field.equals("NotEmpty")){
            pair.put("required","\"true\"");
        }
        else if(field.equals("name")){
            pair.put("label","\""+WordUtils.capitalizeFully(mvalue)+"\"");
            pair.put("value","\"#{bean."+value+"}\"");
            pair.put("edit","\"#{edit"+"}\"");
            pair.put("title","\"#{text[t."+getEntity()+"."+value+"]}\"");
            pair.put("rendered","\"#{empty "+value+"Render ? 'true' : "+value+"Render}\"");
            pair.put("id","\"#{prefix}"+value+"\"");
        }
        else if(field.equals("max")){
            pair.put("maxlength","\""+value+"\"");
        }
        else if(field.equals("min")){
            pair.put("minlength","\""+value+"\"");
        }
        else if(field.equals("Size")){
            pair.put("Size","\""+value+"\"");
        }
        else {
            if(!field.equals("")&& !value.equals("")) {
                pair.put(field, "\"" + mvalue + "\"");
            }
        }
        return pair;
    }

    /**
     *
     *
     */
    public String getInputField(String datatype){
        String inputfield = "inputText";
        if(datatype.equals("Integer")||datatype.equals("Double")||datatype.equals("Float")){
            inputfield = "inputNumber";
        }
        else if(datatype.equals("Date")){
            inputfield = "inputDate";
        }
        return inputfield;
    }
}
