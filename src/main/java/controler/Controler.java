package controler;

import DAO.CSVLoader;
import Function.Function;
import view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.sun.deploy.config.JREInfo.clear;
import static javafx.application.Platform.exit;
import static javafx.application.Platform.setImplicitExit;

public class Controler {

    String path;
    View view;
    CSVLoader csvLoader;
    List<List<String>> data;
    String[] firstLine;
    Function function;


    public Controler() {
        csvLoader = new CSVLoader();
        view = new View();
        function = new Function();
    }

    public void printMenu() {
        view.printString("MENU");
        view.printEmptyLine();
        List<String> menuList = Arrays.asList("Type question : ", "Exit : ");
        view.printMenu(menuList);
    }

    public void doJob() {

        boolean flag = true;

        while(flag){

            switch (view.getInputInt("Type your choise : ")) {

                case (1):

                    String query = view.getInputString("Type your SQL QUERY : ");
                    query = query.toLowerCase();
                    String[] words = query.split(" ");
                    boolean check = Arrays.stream(words)
                            .anyMatch(n -> n.contains(".csv"));
                    String CSVname = null;

                    if (check) {
                        CSVname = Arrays.stream(words)
                                .filter(n -> n.contains(".csv"))
                                .collect(Collectors.toList()).get(0);
                    }

                    path = csvLoader.getCSVLinkbyName(CSVname);
                    data = csvLoader.getInputFile( path, ",");
                    String[] titles = csvLoader.getFirstLine(path, ",");

                    if (!function.checkIfCSVisCorrect(data)) {
                        view.printString("Wrong format of data  !!!");
                        clear();
                    }

                    if (Arrays.stream(words).anyMatch(n -> n.equals("*")) ) {
                        data = function.rowsAll(titles, data);
                    }

                    if (Arrays.stream(words).anyMatch(n -> n.equals("where"))) {

                         List<String> splitedQuery = Stream.of(query.split("where"))
                                 .map(elem -> new String(elem))
                                 .collect(Collectors.toList());

                        String arg = ( splitedQuery.get(1).split(" ") )[1];
                        String sign = ( splitedQuery.get(1).split(" ") )[2];
                        int argValue = Integer.valueOf((splitedQuery.get(1).split(" "))[3]);
                        data = function.filterRow(titles, data, arg, sign, argValue);

                    }

                    if (Arrays.stream(words).anyMatch(n -> n.equals("*")) ) {
                        data = function.rowsAll(titles, data);
                    }

                    if ( !Arrays.stream(words).anyMatch(n -> n.equals("*")) ) {
                        List<String> splitedQuery = Stream.of(query.split("from"))
                                .map(elem -> new String(elem))
                                .collect(Collectors.toList());

                        data = function.showColumn( titles,data,"full_name" ,"participation" );
                    }

                    view.printListOfLists(data);



//                view.printListOfLists(  function.rowsAll(titles, data) ) ;

                    //        int totalsum =  function.sumByColumn(  titles, data, "participation" );
                    //        view.printInt( totalsum );


                    break;

                case (2):
                    flag = false;
                    exit();
                    clear();
                    break;
            }
        }
    }
}


//                        select * from txt.csv where participation > 6000
//                        select participation from txt.csv where participation > 6000