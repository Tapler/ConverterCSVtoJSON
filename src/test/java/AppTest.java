import com.seroevvlad.CSVToJSON.Entity;
import org.junit.Assert;
import org.junit.Test;


public class AppTest {

    @Test
    public void createNewEntity(){
        Entity entity = new Entity("mark01","150,800,400");
        Assert.assertEquals("mark01", entity.getName());
        Assert.assertEquals("150,800,400", entity.getValue());
        Assert.assertEquals(1350, entity.getSum());
    }
    @Test
    public void createEntityWithNotInt(){
        Entity entity = new Entity("mark01","someString");
        Assert.assertEquals(0, entity.getSum());
    }

    @Test
    public void setName(){
        Entity entity = new Entity("mark01","someString");
        entity.setName("mark10");
        Assert.assertEquals("mark10", entity.getName());
    }
}
