public class Messenger {
    
    private final TemplateEngine templateEngine;
    
    private final MailServer mailServer;
    
    public Messenger(MailServer mailServer, TemplateEngine templateEngine) {
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
    }
    
    public void sendMessage(Client client, Template template) {
        String msgContent = templateEngine.prepareMessage(template, client);
        mailServer.send(client.getEmail(), msgContent);
    }
    
}
