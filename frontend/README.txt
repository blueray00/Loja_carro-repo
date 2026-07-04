=========================================
LOJA DE CARROS - FRONTEND
=========================================

Autor: Raynara Gustavo da Costa

Descrição:
Este frontend foi desenvolvido em HTML5, CSS3 e JavaScript puro para consumir a API REST do projeto LojaCarro, utilizando autenticação e autorização por JWT.

=========================================
REQUISITOS
=========================================

- Java 17 ou superior
- Maven
- Navegador atualizado (Google Chrome, Microsoft Edge ou Mozilla Firefox)
- Servidor HTTP para disponibilizar os arquivos do frontend (ex.: Live Server ou Python HTTP Server)

=========================================
COMO EXECUTAR O BACKEND
=========================================

1. Abra o projeto LojaCarro em sua IDE.
2. Execute a aplicação Spring Boot.
3. Aguarde a inicialização da aplicação.

A API ficará disponível em:

http://localhost:8080

=========================================
COMO EXECUTAR O FRONTEND
=========================================

IMPORTANTE:

Não abra os arquivos HTML diretamente pelo Explorador do Windows (file://), pois os navegadores bloqueiam requisições HTTP por questões de segurança (CORS e Same-Origin Policy).

O frontend deve ser servido por um servidor HTTP.

Opção 1 - Visual Studio Code (recomendado)

1. Instale a extensão "Live Server".
2. Abra a pasta "frontend".
3. Clique com o botão direito em "login.html".
4. Selecione "Open with Live Server".

O sistema ficará disponível em:

http://127.0.0.1:5500/login.html

ou

http://localhost:5500/login.html

-----------------------------------------

Opção 2 - Python

Abra um terminal dentro da pasta "frontend" e execute:

python -m http.server 5500

Depois acesse:

http://localhost:5500/login.html

=========================================
LOGIN
=========================================

Usuário:

gerente

Senha:

123

=========================================
FUNCIONALIDADES
=========================================

- Autenticação utilizando JWT.
- Armazenamento do token no Local Storage.
- Controle de acesso por autenticação.
- Listagem de veículos.
- Cadastro de veículos.
- Atualização de veículos.
- Exclusão de veículos.
- Logout do sistema.

=========================================
OBSERVAÇÕES
=========================================

- O backend deve estar em execução antes da utilização do frontend.
- Caso o token seja removido ou expire, o usuário será redirecionado para a tela de login.
- O endereço padrão da API é:

http://localhost:8080

- Caso a API seja executada em outro endereço ou porta, altere a constante "API_URL" nos arquivos:

login.js
script.js

=========================================
ESTRUTURA DO PROJETO
=========================================

frontend/
│
├── login.html
├── index.html
├── login.js
├── script.js
├── style.css
└── README.txt

=========================================
FIM
=========================================