package tricks;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @author shane
 */

public class Elvis implements Serializable {
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {}

    private String[] favoriteSongs = {"Hound Dog", "Heartbreak Hotel"};
    public void printFavorites() {
        System.out.println(Arrays.toString(favoriteSongs));
    }

    public Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }
}
