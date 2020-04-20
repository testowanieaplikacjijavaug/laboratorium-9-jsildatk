import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EasyMockSampleTest {
    
    @Test
    public void test1() {
        List<String> mock = EasyMock.mock(List.class);
        EasyMock.expect(mock.get(0))
                .andReturn("one");
        EasyMock.expect(mock.get(1))
                .andReturn("two");
        
        EasyMock.replay(mock);
        final String result1 = mock.get(0);
        final String result2 = mock.get(1);
        
        assertEquals("one", result1);
        assertEquals("two", result2);
        
        EasyMock.verify(mock);
    }
    
}
