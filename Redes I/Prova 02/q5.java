/** 
 * @author Bryan S.
 * Questão 05 - Prova 02 - Redes I
*/

public isEnderecoPrivadoIPv4(String ip) {
    boolean result = false;
    
    String[] bits = ip.split('.');
    
    if (bits.at(0).equals("10")) {
      result = true;
    }
    
    else if (bits.at(0).equals("172")) {
      int x = Integer.parseInt(bits.at(1));
      
      if (x >= 16 && x <= 31)
        result = true;
    }
    
    else if (bits.at(0).equals("192") && bits.at(1).equals("168")) {
      result = true;
    }
    
    return result;
}