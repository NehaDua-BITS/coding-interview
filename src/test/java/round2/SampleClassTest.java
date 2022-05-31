package round2;

import org.interview.round2.SampleClass;
import org.junit.Assert;
import org.junit.Test;

public class SampleClassTest {

    @Test
    public void testEcho() {
        SampleClass sampleClass = new SampleClass();
        String result = sampleClass.echo("testing");
        Assert.assertEquals("testing", result);
    }
}
