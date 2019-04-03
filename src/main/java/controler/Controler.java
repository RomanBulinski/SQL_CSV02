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
    List< List< String > > data;
    String[] firstLine;
    Function function;


    public Controler() {

        csvLoader = new CSVLoader();
        view= new View();
        function = new Function();

    }

    public void printMenu(){
        view.printString("MENU");
        view.printEmptyLine();
        List<String> menuList = Arrays.asList( "Type question : ","Exit : ");
        view.printMenu(menuList);
    }

    public void doJob(){
        switch( view.getInputInt( "Type your choise : " )) {
            case (1):

                String[] query = view.getInputString("Type your SQL QUERY : ").split(" ");
                boolean check =Arrays.stream(query)
                        .anyMatch(n -> n.contains("."));
                String CSVname = null;
                if(check){
                    CSVname = Arrays.stream(query)
                            .filter(n -> n.contains("."))
                            .collect(Collectors.toList()).get(0);
                }
                path = csvLoader.getCSVLinkbyName(CSVname);
                data = csvLoader.getInputFile ( path ,"," );
                String[] titles = csvLoader.getFirstLine( path,"," );

                if( !function.checkIfCSVisCorrect( data )  ) {
                    view.printString("Wrong format of data  !!!");
                    clear();
                    exit();
                }else if ( Arrays.stream(query).anyMatch(n -> n.equals("*")) && Arrays.stream(query).count()<5 ){
                    view.printListOfLists(  function.rowsAll(titles, data) ) ;
                }

                if ( Arrays.stream(query).anyMatch(n -> n.equals("where"))){
                        view.printListOfLists( function.filterRow( titles, data,"participation", 5000 ) );
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
