import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class MessengerTest {
    
    private Messenger messenger;
    
    private Template template;
    
    private Client client;
    
    private MailServer mailServer;
    
    private TemplateEngine templateEngine;
    
    @Test
    public void testMocks() {
        template = mock(Template.class);
        client = mock(Client.class);
        mailServer = mock(MailServer.class);
        templateEngine = mock(TemplateEngine.class);
        messenger = new Messenger(mailServer, templateEngine);
        
        final String message = "Test";
        final String email = "test@asdf.pl";
        when(templateEngine.prepareMessage(template, client)).thenReturn(message);
        when(client.getEmail()).thenReturn(email);
        
        messenger.sendMessage(client, template);
        
        verify(templateEngine).prepareMessage(template, client);
        verify(mailServer).send(email, message);
    }
    
    @Test
    public void testSpy() {
        template = spy(Template.class);
        client = spy(Client.class);
        mailServer = spy(MailServer.class);
        templateEngine = spy(TemplateEngine.class);
        messenger = new Messenger(mailServer, templateEngine);
        
        final String message = "Test";
        final String email = "test@asdf.pl";
        doReturn(message).when(templateEngine.prepareMessage(template, client));
        doReturn(email).when(client.getEmail());
        
        messenger.sendMessage(client, template);
        
        verify(templateEngine).prepareMessage(template, client);
        verify(mailServer).send(email, message);
    }
    
}