# REDES I - Anotações - 09/08

___

#### "Mensagem"

Na camada **física** não temos o conceito de mensagem, apenas o fluxo de bits. 

A camada de **enlace** interpreta o fluxo de bits e o transforma em mensagens, e essas mensagens são chamadas de `quadros`.

Na camada de **rede** a mensagem é chamada de `pacote`.

Na camada de **transporte**, é chamada de `segmento`. 

Na **aplicação**, é chamada de `mensagem` ou `dados`.

A camada de rede efetua o roteamento entre origem e destino. Ela é responsável pela rota e, em especial, **ENTRE** origem e destino.
___

A tecnologia envolvendo roteadores e a topologia da rede são problemas da camada de rede (da enlace e da física também)

A camada de transporte transporta dados entre um **PROCESSO** na origem e outro no destino.

O processo de origem acredita que ele é conectado diretamente com o processo de destino.

Na verdade, a camada de Enlace é dividida em duas: 
- Camada *MAC*
- A Própria enlace

A camada *MAC* é aquela de: "Quando o burro fala, os outros abaixam a orelha."