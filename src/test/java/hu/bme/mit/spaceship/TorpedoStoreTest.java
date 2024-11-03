package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TorpedoStoreTest {
    TorpedoStore test;
    TorpedoStore inaccurate;
    @BeforeEach
    void initizlaize(){
        test = new TorpedoStore(5);
        inaccurate = new TorpedoStore(5,2);
    }
    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }
    @Test 
    void fire_Exception(){
        assertThrows(IllegalArgumentException.class,()-> test.fire(10)); // it has only 5 in store
    }
    @Test 
    void fire_Exception2(){
        test.fire(5);
        assertThrows(IllegalArgumentException.class,()-> test.fire(1)); // store is empty
    }
    @Test
    void fire_inaccurate(){
        boolean success = inaccurate.fire(1);
        assertEquals(false, success);
    }
    @Test
    void fire_count(){
        test.fire(3);
        assertEquals(2,test.getTorpedoCount());
    }
    @Test
    void fire_empty(){
        test.fire(5);
        assertEquals(true, test.isEmpty());
        inaccurate.fire(5);
        assertEquals(false,inaccurate.isEmpty());
    }
    @Test
    void fire_assumeFired(){
        TorpedoStore ts  = new TorpedoStore(5,0.2);
        assumeTrue(ts.fire(2));
        assertEquals(3,ts.getTorpedoCount());
    }
    @Test
    void fire_assumeNotFired(){
        TorpedoStore ts  = new TorpedoStore(5,0.9);
        assumeFalse(ts.fire(2));
        assertEquals(5,ts.getTorpedoCount());
    }

}
