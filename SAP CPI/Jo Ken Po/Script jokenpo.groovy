import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import java.util.Random
import java.util.Collections

def jokenpo(String entrada){
def random = new Random()
def strbuff = new StringBuffer()
def numeroAleatorio = random.nextInt(3)
lista = ["pedra", "papel", "tesoura"]
Collections.shuffle(lista)
def pc = lista[numeroAleatorio]

def result
switch(entrada){
    case "papel":
        if(pc == "papel"){
            result= "Empatou"
        }else if(pc == "pedra"){
            result ="Voce ganhou"
        }else{
            result= "Voce perdeu"
        }
        break
		
    case "pedra":
        if(pc == "papel"){
            result ="Voce perdeu"
        }else if(pc == "pedra"){
            result ="Empatou"
        }else{
            result= "Voce ganhou"
        }
        break
		
    case "tesoura":
        if(pc == "papel"){
            result ="Voce ganhou"
        }else if(pc == "pedra"){
            result ="Voce perdeu"
        }else{
            result ="Empatou"
        }
        break
}
return result
}


def Message processData(Message message) {
    def body = message.getBody(String);
    
    def resultado = jokenpo(body)
    message.setBody(resultado)

    return message;
}