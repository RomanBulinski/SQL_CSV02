package view;

import java.util.List;
import java.util.stream.Collectors;

public class View {

    public void printListOfLists( List<List< String >> data){
        data.stream()
                .forEach(n-> System.out.println( n ));
    }

    public void printInt( Integer num){
         System.out.println( num );
    }


}
