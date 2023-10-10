import java.util.Random

def parOuImpar(String player, int num){
  choices = ["par", "impar"]
  choices.remove(player)
  pc = choices.pop()
  rd = new Random()
  pcNum = rd.nextInt(9)
  sum = num + pcNum

  switch(sum % 2 == 0){
    case true:
    result = (player == "par")?"Voce ganhou!":"Voce perdeu!"
    break

    case false:
    result = (player == "impar")?"Voce ganhou!":"Voce perdeu!"
    break

    }
  
  return "O bot escolheu: \"$pc\" e o numero: \"$pcNum\".\n${result.toUpperCase()}"
  }
  
  
  print parOuImpar("par", 4)