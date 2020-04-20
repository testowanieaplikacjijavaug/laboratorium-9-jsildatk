import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarTest {
    
    private Car car;
    
    @BeforeEach
    public void setUp() {
        car = mock(Car.class);
    }
    
    @Test
    public void test_instance_car() {
        assertTrue(car instanceof Car);
    }
    
    @Test
    public void test_default_behavior_needsFuel() {
        assertFalse(car.needsFuel(), "New test double should return False as boolean");
    }
    
    @Test
    public void test_default_behavior_temperature() {
        assertEquals(0.0, car.getEngineTemperature(), "New test double should return 0.0");
    }
    
    @Test
    public void test_stubbing_mock() {
        when(car.needsFuel()).thenReturn(true);
        assertTrue(car.needsFuel());
    }
    
    @Test
    public void test_exception() {
        when(car.needsFuel()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> car.needsFuel());
    }
    
    @Test
    public void testVerification() {
        car.driveTo("Kartuzy");
        car.needsFuel();
        
        verify(car).driveTo("Kartuzy");
        verify(car).needsFuel();
        assertFalse(car.needsFuel());
    }
    
    @Test
    public void testEngineTemperature() {
        when(car.getEngineTemperature()).thenReturn(11.11);
        final double result = car.getEngineTemperature();
        
        verify(car).getEngineTemperature();
        assertEquals(11.11, result);
    }
    
    @Test
    public void testDriveToThrowsException() {
        doThrow(new RuntimeException()).when(car)
                .driveTo(anyString());
        assertThrows(RuntimeException.class, () -> car.driveTo("Sasd"));
    }
    
    @Test
    public void testDriveToDoAnswer() {
        doAnswer((Answer<Void>) invocation -> {
            assertEquals(1, invocation.getArguments().length);
            return null;
        }).when(car)
                .driveTo(anyString());
        
        car.driveTo("Test");
    }
    
    @AfterEach
    public void tearDown() {
        car = null;
    }
    
}