package org.example;

public class EmailNotificacaoService implements NotificacaoService {

    @Override
    public void enviarNotificacao() {
        System.out.println("Notificação de e-mail enviada!");
    }
}
