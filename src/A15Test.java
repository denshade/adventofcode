import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class A15Test
{
    @Test
    void loadSensors()
    {
        A15.loadSensors(new File("a15.test.txt"));
    }

}