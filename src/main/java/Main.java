import DAO.CSVLoader;
import controler.Controler;
import view.View;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {



    public static void main(String[] args) {


        Controler controler = new Controler( );

        controler.printMenu();
        controler.doJob();

    }





}
