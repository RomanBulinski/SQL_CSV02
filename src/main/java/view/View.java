package view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class View {



    public void formatPrintLineList( List<String> oneLine ){
        for( String element : oneLine){
            System.out.printf("%25s", element);
        }
        System.out.println();
    }

    public void printListOfLists(List<List<String>> data) {
        data.stream()
                .forEach(n -> formatPrintLineList(n));
    }

    public void printInt(Integer num) {
        System.out.println(num);
    }

    public void printString( String word ) {
        System.out.println( word );
    }

    public void printEmptyLine( ) {
        System.out.println( );
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

    public Integer getInputInt( String labelText ){
        Scanner reader = new Scanner(System.in);
        System.out.println( labelText );
        int n = reader.nextInt(); // Scans the next token of the input as an int.
//        reader.close();
        return  n;
    }

    public String getInputString( String labelText ){
        Scanner reader = new Scanner(System.in);
        System.out.println( labelText );
        String n = reader.nextLine();
//        reader.nextLine();

        return  n;
    }









}