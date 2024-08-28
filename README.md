# Tutorial de Execução do Projeto

Este tutorial orienta você a baixar, configurar e executar o projeto. Siga as etapas abaixo para garantir que o projeto seja executado corretamente.

## Etapa 1: Clonar o Repositório Git

1. **Abrir o Terminal:**
   - Abra o terminal no seu sistema operacional.

2. **Clonar o Repositório:**
   - Use o comando abaixo para clonar o repositório do projeto para o seu diretório local:

     ```sh
     git clone https://github.com/JoaoSouzaNascimento/Bet.git
     ```

## Etapa 2: Configurar o Classpath

1. **Adicionar os Arquivos ao Classpath:**
   - Certifique-se de que todos os arquivos do projeto estejam incluídos no classpath. A maioria das IDEs gerencia isso automaticamente ao importar o projeto, mas verifique as configurações do classpath para garantir que todos os arquivos necessários estejam acessíveis.

## Etapa 3: Configurar e Executar o Docker

1. **Navegar até o Diretório do Projeto:**
   - No terminal, navegue até o diretório `Tigrinho/src` onde o arquivo `docker-compose.yml` está localizado. Use o comando abaixo, substituindo o caminho pelo local onde você clonou o projeto:

     ```sh
     cd /caminho/para/o/projeto/Tigrinho/src
     ```

2. **Executar o Docker Compose:**
   - Execute o seguinte comando para iniciar os containers Docker definidos no arquivo `docker-compose.yml`:

     ```sh
     docker compose up -d
     ```


3. **Verificar os Containers:**
   - Para verificar se os containers estão em execução, use o comando:

     ```sh
     docker ps
     ```

## Etapa 4: Executar a Aplicação

1. **Executar a Aplicação Java:**
   - Com os containers Docker em execução, você pode iniciar a aplicação Java. Na sua IDE, localize a classe `AppLauncher` e execute-a como uma aplicação Java. Este será o ponto de entrada da sua aplicação.

2. **Interagir com a Aplicação:**
   - Após iniciar a aplicação, uma janela de login será exibida. Siga as instruções na tela para fazer login ou criar um usuário de teste, conforme necessário.

## Observações

- **Docker:** Certifique-se de ter o Docker instalado e em execução no seu sistema. Caso não tenha o Docker instalado, você pode baixar e instalar [aqui](https://www.docker.com/get-started).

- **Ambiente:** Verifique se o ambiente de desenvolvimento está configurado corretamente, incluindo o Java e as bibliotecas necessárias.

Se você seguir essas etapas, deverá conseguir executar o projeto sem problemas. Se encontrar algum erro ou problema, verifique as mensagens de erro no terminal ou na IDE para diagnóstico adicional.
