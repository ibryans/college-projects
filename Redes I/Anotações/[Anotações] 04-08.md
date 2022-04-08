# REDES I - Anotações - 04/08

> $~ traceroute www.pucminas.com.br

Em rede temos 3 modelos: **OSI**, **TCP/IP** e **Híbrido**. Os modelos são teoria, classificação. Na verdade, o TCP/IP é uma arquitetura, uma implementação de verdade.

**OSI**

- Aplicação
- Apresentação
- Sessão
- Transporte
- Rede
- Enlace
- Física

**TCP/IP**

- Aplicação
- Transporte
- Inter-Redes
- Hospedeiro/Rede

**Híbrido**

- Aplicação
- Transporte
- Rede
- Enlace
- Física

As camadas de transporte e aplicação rodam exclusivamente na origem e no destino. As camadas de Rede, Enlace e Física rodam em todos os *HOPs* da comunicação.

O processo de roteamento cosiste em um sobe e desce na pilha de protocolos de todos o *HOPs* envolvidos na rota de comunicação

> HOP: Uma parte do caminho entre a origem e o destino. Os pacotes de dados passam por pontes, roteadores e gateways enquanto viajam entre a origem e o destino. Cada vez que os pacotes são passados para o próximo dispositivo de rede, ocorre um hop. A contagem de hops refere-se ao número de dispositivos intermediários pelos quais os dados devem passar entre a origem e o destino.

Um dos principais pontos da Rede de Computadores é a organização em camadas, que nos permite separar cada tarefa e trocar de protocolos da mesma forma que no lego

- Camada física: Cuspir bits
- Camada de enlace: Controle de fluxo (velocidade), controle de erros e enquadramento.

A camada de enlace é local (Sua rede). A camada de rede é a grande rede (por exemplo, internet).
