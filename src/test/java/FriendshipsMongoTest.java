import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FriendshipsMongoTest {
    
    @Mock
    private FriendsCollection friendsCollection;
    
    @InjectMocks
    private FriendshipsMongo friendshipsMongo;
    
    @Test
    public void mockingWorksAsExpected() {
        Person joe = new Person("Joe");
        when(friendsCollection.findByName("Joe")).thenReturn(joe);
        
        assertThat(friendsCollection.findByName("Joe")).isEqualTo(joe);
    }
    
    @Test
    public void alexDoesNotHaveFriends() {
        assertThat(friendshipsMongo.getFriendsList("Alex")).isEmpty();
    }
    
    @Test
    public void joeHas5Friends() {
        List<String> expected = Arrays.asList("Karol", "Dawid", "Maciej", "Tomek", "Adam");
        Person joe = mock(Person.class);
        when(friendsCollection.findByName("Joe")).thenReturn(joe);
        when(joe.getFriends()).thenReturn(expected);
        
        assertThat(friendshipsMongo.getFriendsList("Joe")).hasSize(5)
                .containsOnly("Karol", "Dawid", "Maciej", "Tomek", "Adam");
        verify(friendsCollection).findByName("Joe");
        verify(joe).getFriends();
    }
    
    @Test
    public void joeWithAlexAreNotFriends() {
        Person joe = mock(Person.class);
        when(friendsCollection.findByName("Joe")).thenReturn(joe);
        when(joe.getFriends()).thenReturn(new ArrayList<>());
        
        assertThat(friendshipsMongo.areFriends("Joe", "Alex")).isFalse();
        verify(friendsCollection).findByName("Joe");
        verify(joe).getFriends();
    }
    
    @Test
    public void joeWithAlexAreFriends() {
        Person joe = mock(Person.class);
        when(friendsCollection.findByName("Joe")).thenReturn(joe);
        when(joe.getFriends()).thenReturn(Collections.singletonList("Alex"));
        
        assertThat(friendshipsMongo.areFriends("Joe", "Alex")).isTrue();
        verify(friendsCollection).findByName("Joe");
        verify(joe).getFriends();
    }
    
    @Test
    public void nameOfJoeIsJoe() {
        Person joe = mock(Person.class);
        when(joe.getName()).thenReturn("Joe");
        
        assertThat(joe.getName()).isEqualTo("Joe");
        verify(joe).getName();
    }
    
    @Test
    public void settingNameWithEmptyStringThrowsException() {
        Person joe = mock(Person.class);
        doThrow(new RuntimeException()).when(joe)
                .setName(anyString());
        
        assertThatThrownBy(() -> joe.setName("")).isInstanceOf(RuntimeException.class);
    }
    
}