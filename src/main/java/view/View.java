package view;

import java.util.List;
import java.util.stream.Collectors;

public class View {


    public void formatPrintLine(  ){
        

    }

    public void printListOfLists(List<List<String>> data) {
        data.stream()
                .forEach(n -> System.out.println(n));
    }

    public void printInt(Integer num) {
        System.out.println(num);
    }

    public void isCorrectInformation(Boolean checkFlag) {
        if (checkFlag) {
            System.out.println("CSV file is OK.");
        } else {
            System.out.println("SV file is not correct.");
        }
    }

    public void printMenu(List<String> menuList ) {
            for ( int i =0; i< menuList.size(); i++){
                System.out.println( i+1 +" "+ menuList.get(i) );
            }
        System.out.println();
//        menuList.stream()
//                .forEach(n -> System.out.println(   menuList.get( String.valueOf( n ) ) + " " + n ));
    }

}