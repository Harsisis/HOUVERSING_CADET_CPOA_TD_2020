package main.ui.util;

public class util_isFloat {
    public boolean isFloat(String string) {
        try
        {
            // checking valid integer using parseInt() method
            Float.parseFloat(string);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
}
