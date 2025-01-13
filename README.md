# shopping-cart-manager

## Como Configurar e Executar Esta Aplicação

### Requisitos

Certifique-se de ter as seguintes ferramentas instaladas no seu computador:

- **Java JDK** (versão 8 ou superior)
- **Maven** para gerenciar as dependências (não é necessário baixar o arquivo bin manualmente, o Maven pode ser instalado facilmente)
- **XAMPP** com Apache e MySQL
- **Git** para clonar o repositório

### Passos para Configuração

1. **Clone o Repositório**

    ```bash
    git clone https://github.com/Pichula07/shopping-cart-manager.git
    cd shopping-cart-manager
    ```

2. **Configure o Banco de Dados no XAMPP**
    - Inicie o XAMPP e certifique-se de que os módulos Apache e MySQL estão em execução.
    - Acesse o phpMyAdmin no navegador em [http://localhost/phpmyadmin](http://localhost/phpmyadmin).
    - No phpMyAdmin, crie uma base de dados com o nome shopping_cart.
    - Importe o arquivo `shopping_cart.sql` fornecido no repositório:
      - No phpMyAdmin, clique em **Importar**.
      - Clique no botão **Escolher arquivo** e selecione o arquivo `shopping_cart.sql` localizado no diretório `src/main/resources/sql`.
      - Clique em **Executar** para importar o banco de dados com as tabelas vazias.
    - Certifique-se de que o banco de dados foi importado com sucesso e que as tabelas estão presentes.

3. **Compile o Projeto**
    - No terminal, vá para a pasta raiz do projeto e execute:

    ```bash
    mvn clean install
    ```

4. **Execute a Aplicação**
    - Após a compilação, execute o JAR gerado:

    ```bash
    java -jar target/Shopping-Cart-Manager-1.0.0.jar
    ```

    A aplicação será iniciada no console, e você poderá interagir com ela diretamente por meio de entradas e saídas no terminal.

### Como Resolver Caso o JAR Não Funcione

Caso você encontre dificuldades ao tentar executar o arquivo JAR, como um erro que indique que o arquivo não pode ser acessado ou que falte alguma dependência, siga as seguintes etapas:

1. **Verifique se o arquivo JAR foi gerado corretamente**:
   - Acesse a pasta `target` do seu projeto e verifique se o arquivo `Shopping-Cart-Manager-1.0.0.jar` está presente.
   - Caso o arquivo JAR não esteja presente, execute novamente o comando Maven `mvn clean install` para compilar e gerar o JAR.

2. **Erros de Classe ou Dependências Faltando**:
   - Caso a execução do JAR falhe devido a classes ausentes, como `javax.persistence.Persistence`, adicione as dependências corretas no seu arquivo `pom.xml` (se já não estiverem presentes). Para isso, adicione a dependência do JPA (Java Persistence API), como mostrado abaixo:

    ```xml
    <dependency>
        <groupId>javax.persistence</groupId>
        <artifactId>javax.persistence-api</artifactId>
        <version>2.2</version>
    </dependency>
    ```

3. **Problemas com Permissões no Sistema Operacional**:
   - Se você receber erros de permissão ao tentar rodar o arquivo JAR, certifique-se de que tem permissões adequadas de leitura e execução no arquivo.
   - Caso esteja em um sistema Unix (Linux/Mac), execute o seguinte comando para garantir permissões de execução:

    ```bash
    chmod +x target/Shopping-Cart-Manager-1.0.0.jar
    ```

4. **Verifique a versão do Java**:
   - Certifique-se de estar usando uma versão compatível do Java (8 ou superior). Execute o comando `java -version` para verificar a versão instalada.

### Configuração Adicional

#### Configuração do Maven (Se necessário)

Caso você não tenha o Maven instalado ou se o Maven não estiver funcionando corretamente, siga os passos abaixo para configurá-lo:

1. **Baixar o Apache Maven**
   - Acesse a página oficial do Apache Maven: [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
   - Baixe a versão mais recente do Maven (geralmente um arquivo `.zip` ou `.tar.gz`).

2. **Instalar o Apache Maven**
   - Extraia o conteúdo do arquivo baixado para um diretório de sua escolha, por exemplo, `C:\maven` (Windows) ou `/opt/maven` (Linux/Mac).
   - Certifique-se de que o diretório `bin` do Maven esteja no PATH do sistema.
   
     **No Windows:**
     - Vá em "Propriedades do Sistema" -> "Configurações avançadas do sistema" -> "Variáveis de Ambiente".
     - Em "Variáveis do sistema", procure pela variável `Path`, e adicione o caminho para o diretório `bin` do Maven (por exemplo, `C:\maven\bin`).

     **No Linux/Mac:**
     - Abra o terminal e edite o arquivo `.bashrc` ou `.zshrc` (dependendo do shell que você usa).
     - Adicione a linha:

       ```bash
       export PATH=/opt/maven/bin:$PATH
       ```

     - Salve e execute `source ~/.bashrc` ou `source ~/.zshrc` para recarregar as configurações do shell.

3. **Verifique a instalação do Maven**
   - Após a instalação, abra um terminal e digite:

     ```bash
     mvn -v
     ```

   - Isso deve retornar a versão do Maven instalada, indicando que o Maven foi configurado corretamente.

#### Configuração de Porta

- Caso necessário, altere a porta padrão no arquivo `application.properties`:

    ```properties
    server.port=8081
    ```

Se você encontrar problemas ou tiver dúvidas, sinta-se à vontade para abrir uma **issue** neste repositório!
