package server.models.datatypes;

import java.util.ArrayList;
import java.util.List;

public class Array extends DataType {

    private List<DataType> list;
     public Array() {
        super('*');
        list = new ArrayList<>();
    }

    public void add(DataType item) {
         list.add(item);
    }



    @Override
    public String encode() {
         StringBuilder builder = new StringBuilder("*" + list.size() + terminator);
         for (int i = 0; i<list.size(); i++) {
             builder.append(list.get(i).encode());
         }
         return builder.toString();
    }
}
