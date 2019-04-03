package Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Function {



    public boolean checkIfCSVisCorrect ( List<List< String >> data ){
        List<Integer> sizes =  data.stream()
                .map( n-> n.size() )
                .collect(Collectors.toList());
        boolean flag = true;
        for( int i =0; i<sizes.size()-1; i++ ){
            if ( sizes.get(i) == sizes.get(i + 1)){
                flag = true;
            }else{
                flag = false;
                break;
            }
        }
        return flag;
    }

    public List<List< String >>  remoweWhiteSpaces ( List<List< String >> data ){
        List<List< String >>  dataNoWhiteSpaces =  data.stream()
                .map( n-> { if( n.get(0).equals(" ") ){
                                    return n.subList(1,n.size());
                                }else{
                                    return n;
                                }
                } )
                .collect(Collectors.toList());
        return  dataNoWhiteSpaces;
    }

    public List<List< String >> rowsAll( String[] titles, List<List< String >> data ){
        return data.stream()
                .collect(Collectors.toList());
    }
    
    public List<List< String >> showColumn(String[] titles, List<List< String >> data, String... args){

        List<String> argsh = Arrays.asList(args);
        List<Integer> argumentes = argsh.stream()
                .map( n ->  coulmnTranslator( n ,titles) )
                .collect(Collectors.toList());

        List<List< String >> column = data.stream()
                .map( n-> {
                    List<String> temp = new ArrayList<>();
                    for(Integer index : argumentes){
                        temp.add( n.get(index) );
                    }
                    return temp;
                })
                .collect(Collectors.toList());
        return column;
    }


    public List<List< String >> filterRow( String[] titles, List<List< String >> data, String columnTitle, Integer value ){
        int index = coulmnTranslator( columnTitle  ,titles);
        return data.stream()
                .filter( n-> Integer.valueOf( n.get(index) ) < value )
                .collect(Collectors.toList());
    }


    public int coulmnTranslator( String columnTiltle, String[] firstLine) {
        int index = -1;
        for (int i = 0; i < firstLine.length; i++) {
            if (firstLine[i].equals(columnTiltle)) {
                index = i;
            }
        }
        return index;
    }


    public int sumByColumn( String[] titles, List<List< String >> data, String columnTitle ){
        int index = coulmnTranslator( columnTitle ,titles);
        Integer sumOf = data.stream()
                .mapToInt( n-> Integer.valueOf( n.get(index) ) )
                .sum();
        return sumOf;
    }


}
