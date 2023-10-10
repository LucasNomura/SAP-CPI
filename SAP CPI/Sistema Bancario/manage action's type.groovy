import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.*

def Message processData(Message message) {
    def body = message.getBody(String); 
    def jsonParser = new JsonSlurper()
    def jsonObject = jsonParser.parseText(body)

    message.setProperty("valor", jv = jsonObject.valor != ''?jsonObject.valor.replace(",", ".").toFloat():0)
    message.setProperty("alvo", jsonObject.para_quem)

    
    saldo = message.getProperty("saldoAtual").toFloat()
    switch(message.getProperty("acao")){
        case "deposito":
            message.setProperty("saldoNovo", saldo + message.getProperty("valor"))
            break

        case "saque":
            if(saldo - message.getProperty("valor") >= 0){
            message.setProperty("saldoNovo", saldo - message.getProperty("valor"))
            }else{
                message.setProperty("acao", "erro")
            }
            break

        case "transferencia":
            message.setProperty("saldoNovo", saldo - message.getProperty("valor"))
            break

        case "consulta":
            message.setProperty("saldoAtual", String.format("%.2f", saldo))            
            break

    }


    return message;
}