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

        String[] titles = csvLoader.getFirstLine( path,"," );

//        view.printListOfLists(filterRow( data,"participation", 5000 ));
//        view.printListOfLists(function.showColumn( titles, function.filterRow( titles, data,"participation", 5000 ), "full_name","participation" ));

        view.printListOfLists(  function.showColumn(titles, function.rowsAll(titles, data),"participation" ) );

        int totalsum =  function.sumByColumn(  titles, data, "participation" );
        view.printInt( totalsum );


    }







}
