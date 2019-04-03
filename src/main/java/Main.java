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

        String path = "/home/roman/codecool/40_Advanced/04_TW_FP/02SQL_CSV/SQL_CSV02/src/main/sources/txt.csv";
        Controler controler = new Controler( path );

        controler.printMenu();
        controler.doJob();

    }





}
