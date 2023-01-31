package io.ferretdb;

import org.junit.Assert;
import org.junit.Test;
import com.mongodb.client.MongoClients;

/**
 * Unit test for simple App.
 */
public class ReproducerTest {
    @Test
    public void checkConnection() {
        try {
            var MONGO_URI = "mongodb://username:password@localhost:27017/?authMechanism=PLAIN";
            var mclient = MongoClients.create(MONGO_URI);

            var foo = mclient.getDatabase("foo").getCollection("bar");
            foo.find().forEach(System.out::println);
        } catch(Throwable t) {
            t.printStackTrace();
            Assert.fail();
        }
    }
}
