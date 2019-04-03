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


    public Controler(String path) {

        this.path = path;
        csvLoader = new CSVLoader();
        data = csvLoader.getInputFile ( path ,"," );
        firstLine = csvLoader.getFirstLine(path ,",");
        view= new View();
        function = new Function();

    }

    public void printMenu(){
        view.printString("MENU");
        view.printEmptyLine();
        List<String> menuList = Arrays.asList("Type question : ","Exit : ");
        view.printMenu(menuList);
    }

    public void doJob(){

        switch( view.getInputInt( "Type your choise : " )) {
            case (1):
                String[] titles = csvLoader.getFirstLine( path,"," );
                switch(  view.getInputString("Type your SQL QUERY : ")){
                    case ("select * from txt.csv"):
                        view.printListOfLists(  function.rowsAll(titles, data) ) ;
                        break;
                    case ("yyy"):

                        //        view.printListOfLists(filterRow( data,"participation", 5000 ));
                        //        view.printListOfLists(function.showColumn( titles, function.filterRow( titles, data,"participation", 5000 ), "full_name","participation" ));

                        view.printListOfLists(  function.showColumn(titles, function.rowsAll(titles, data),"full_name" ,"participation" ) );
                        view.printListOfLists(  function.rowsAll(titles, data) ) ;

                        view.isCorrectInformation( function.checkIfCSVisCorrect( data )   );

                        //        int totalsum =  function.sumByColumn(  titles, data, "participation" );
                        //        view.printInt( totalsum );

                        break;
                    default:
                        // code block
                }
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
