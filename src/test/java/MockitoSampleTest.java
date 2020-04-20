import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MockitoSampleTest {
    
    @Test
    public void test1() {
        List<String> mock = mock(List.class);
        
        when(mock.get(0)).thenReturn("one");
        when(mock.get(1)).thenReturn("two");
        
        final String result1 = mock.get(0);
        final String result2 = mock.get(1);
        
        assertEquals("one", result1);
        assertEquals("two", result2);
        
        // nie musimy verify wszystkiego
        verify(mock).get(0);
    }
    
}
