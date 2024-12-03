package org.example;

public class SMSNotificacaoService implements NotificacaoService{
    @Override
    public void enviarNotificacao() {
        System.out.println("Notificação de SMS enviada!");
    }
}
