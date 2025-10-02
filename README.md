1)
Uma activity aloca memória e gerencia processos do sistema operacional toda vez que é aberta ou rotacionada, fazendo com que o mal gerenciamento do ciclo de vida de uma activity possa causar lentidão e travamentos. Visto que muitas activities podem ser abertas.
Para o acesso à dados caros de serem resgatados (acesso à banco, requisições WEB) é possível salvá-los e resgatá-los com os métodos onSaveInstanceState() e onRestoreInstanceState() no carregamento da activity, trazendo um ganho de performance. Sistemas com essa técnica implementada adotam o conceito State Restoration, que consiste no regate do estado da anterior da activity em seu carregamento.

2)
Manifesto é um arquivo xml que define as propriedades do aplicativo android, como as activities existentes, ícone do aplicativo, etc.
Res é a pasta de recursos da aplicação, onde podem ser armazenadas imagens, strings internacionalizadas, os próprios layouts para as activities, etc.
R é uma classe autogerada que contém os identificadores de todos os recursos da aplicação (pasta res)
Activity é uma classe que representa uma tela da aplicação. Ela contém a lógica do ciclo de vida da tela, suas regras e comportamentos
