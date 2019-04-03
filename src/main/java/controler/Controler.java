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
        switch (view.getInputInt("Type your choise : ")) {
            case (1):

                String query = view.getInputString("Type your SQL QUERY : ");
                query = query.toLowerCase();
                String[] words = query.split(" ");
                boolean check = Arrays.stream(words)
                        .anyMatch(n -> n.contains("."));
                String CSVname = null;
                if (check) {
                    CSVname = Arrays.stream(words)
                            .filter(n -> n.contains("."))
                            .collect(Collectors.toList()).get(0);
                }
                path = csvLoader.getCSVLinkbyName(CSVname);
                data = csvLoader.getInputFile(path, ",");
                String[] titles = csvLoader.getFirstLine(path, ",");

                if (!function.checkIfCSVisCorrect(data)) {
                    view.printString("Wrong format of data  !!!");
                    clear();
                    exit();
                }

                if (Arrays.stream(words).anyMatch(n -> n.equals("*")) && Arrays.stream(words).count() < 5) {
                    view.printListOfLists(function.rowsAll(titles, data));
                }

                    if (Arrays.stream(words).anyMatch(n -> n.equals("where"))) {


                        List<String> splitedQuery = Stream.of(query.split("where"))
                                .map(elem -> new String(elem))
                                .collect(Collectors.toList());

//                    Arrays.stream(splitedQuery.get(1).split(" ")).anyMatch( n-> words.   )

                        if (Arrays.stream(splitedQuery.get(0).split(" ")).anyMatch(n -> n.equals("*"))) {

                            String arg = (splitedQuery.get(1).split(" "))[0];
                            int argValue = Integer.valueOf((splitedQuery.get(1).split(" "))[2]);

                            view.printListOfLists(function.filterRow(titles, data, arg, argValue));

                        }

//                    boolean check =Arrays.stream(words)
//                            .anyMatch(n -> n.equals(  ));
                    }


                    //        view.printListOfLists(filterRow( data,"participation", 5000 ));
                    //        view.printListOfLists(function.showColumn( titles, function.filterRow( titles, data,"participation", 5000 ), "full_name","participation" ));

//                view.printListOfLists(  function.showColumn(titles, function.rowsAll(titles, data),"full_name" ,"participation" ) );
//                view.printListOfLists(  function.rowsAll(titles, data) ) ;

                    //        int totalsum =  function.sumByColumn(  titles, data, "participation" );
                    //        view.printInt( totalsum );

                    // code block
                    break;

            case (2):
                clear();
                exit();
                break;

            default:
                // code block

            }
        }
    }
