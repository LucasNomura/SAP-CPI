import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.*

def Message processData(Message message) {
    def body = message.getBody(String); 
    def jsonParser = new JsonSlurper()
    def jsonObject = jsonParser.parseText(body)

    message.setProperty("email", jsonObject.comprovante_email)
    allowed = ["consulta", "saque", "deposito", "transferencia", "?"]
    
    acao = jsonObject.acao
    
    switch(acao){
        case "c":
            acao = "consulta"
            break

        case "d":
            acao = "deposito"
            break

        case "s":
            acao = "saque"
            break

        case "t":
            acao = "transferencia"
            break

    }

    message.setProperty("acao", acao)
    if(!allowed.contains(acao)){

        message.setProperty("condicao", "invalido")
        message.setProperty("error", "Erro, ação não reconhecida!")

    }else{

        message.setProperty("condicao", jsonObject.acao)
        message.setProperty("error", "Erro! Em (comprovante_email) digite apenas \"s\" para sim ou \"n\"  para não.")

    }

    return message;
}