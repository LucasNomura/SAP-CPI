import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

def Message processData(Message message) {
    def body = message.getBody();
    
    usuario = message.getHeaders().get("usuario")
    senha   = message.getHeaders().get("senha")
    
        def mapaComoString = message.getProperty("contas")
        mapaComoString = mapaComoString.replace("[", "").replace("]", "")
        def entradas = mapaComoString.split(',') 
    
        def mapa = [:]
        entradas.each { entrada ->

            def partes = entrada.split(':') 
            mapa[partes[0].trim()] = partes[1].trim()

        }
    
    if(mapa.containsKey(usuario) && mapa[usuario] == senha){

        message.setProperty("validacao", "aprovado")

    }else{

        message.setProperty("validacao", "negado")

    }
    
    return message;
}