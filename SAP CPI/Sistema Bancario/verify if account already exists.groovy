import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

def Message processData(Message message) {
    def body = message.getBody();
    
    usuario = message.getHeaders().get("usuario")
    senha   = message.getHeaders().get("senha")
    
        def mapaComoString = message.getProperty("contas")
        mapaComoString = mapaComoString.replace("[", "").replace("]", "")
        def entradas = mapaComoString.split(',') 
    
        def mapa = [:] /* new HashMap() */
        entradas.each { entrada ->

            def partes = entrada.split(':') 
            mapa[partes[0].trim()] = partes[1].trim()

        }
    
    if(!mapa.containsKey(usuario)){

        mapa.put(usuario, senha)
        message.setProperty("usuario", usuario)
        message.setProperty("contas", mapa.toString())
        message.setProperty("result", "success")

    }else{

        message.setProperty("result", "deny")

    }
    
    return message;
}