package main.properties;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class properties {

    public static void main(String[] args) {

        try {

            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream("D:\\Bureau\\HOUVERSING_CADET_CPOA_TD1_2020\\src\\main\\properties\\log.xml");
            properties.loadFromXML(fis);
            fis.close();

            properties.list(System.out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
