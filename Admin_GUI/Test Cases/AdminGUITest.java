package adminGui;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class AdminGUITest
{

    @Test
    public void resourceMain()
    {
        File file = new File("bin/Sherlock.txt");
        new resources(file);
    }

}
