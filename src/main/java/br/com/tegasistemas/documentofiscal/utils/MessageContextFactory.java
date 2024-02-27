package br.com.tegasistemas.documentofiscal.utils;

import br.com.tegasistemas.documentofiscal.DFConfig;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.transport.http.HTTPConstants;

public enum MessageContextFactory {
    INSTANCE;

    public MessageContext create(DFConfig config) {
        final MessageContext messageContext = new MessageContext();
        messageContext.setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeoutRequisicaoEmMillis());
        messageContext.setProperty(HTTPConstants.CONNECTION_TIMEOUT, config.getSoTimeoutEmMillis());
        return messageContext;
    }
}
