package controler;

import DAO.CSVLoader;
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

    public Controler(String path) {
        this.path = path;
        csvLoader = new CSVLoader();
        data = csvLoader.getInputFile ( path ,"," );
        firstLine = csvLoader.getFirstLine(path ,",");
        view= new View();

//        view.printListOfLists(filterRow( data,"participation", 5000 ));
        view.printListOfLists(showColumn( filterRow( data,"participation", 5000 ), "full_name", "address","type" ));

    }



    public void showCol(List<List< String >> data, String... args){
//        Stream<String> arguments = Arrays.stream(args);
        Object[] argumentes =  {1,2};
        List<List< String >> column = new ArrayList<>();
        data.stream().forEach( n-> {
            for( Object arg : argumentes ){
                System.out.print( n.get((Integer) arg) + " ");
            }
            System.out.println( );
        });
    }



    public List<List< String >> showColumn(List<List< String >> data, String... args){
//        Stream<String> arguments = Arrays.stream(args);
        List<String> argsh = Arrays.asList(args);
        String[] titles = csvLoader.getFirstLine( path,"," );

        List<Integer> argumentes = new ArrayList<>();

        for (String temp :argsh){
            argumentes.add( csvLoader.coulmnTranslator( temp ,titles));
        }

        List<List< String >> column = new ArrayList<>();

        data.stream().forEach( n-> {
            for( Integer index : argumentes ){
                column.add(Collections.singletonList(n.get(index)));
            }
        });

        return column;
    }




    public List<List< String >> filterRow( List<List< String >> data, String columnTitle, Integer value ){

        String[] titles = csvLoader.getFirstLine( path,"," );
        int index = csvLoader.coulmnTranslator( columnTitle  ,titles);

        return data.stream()
                .filter( n-> Integer.valueOf( n.get(index) ) > value )
                .collect(Collectors.toList());

    }




}
