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
    - Importe o arquivo `shopping_cart.sql` fornecido no repositório:
      - No phpMyAdmin, clique em **Importar**.
      - Clique no botão **Escolher arquivo** e selecione o arquivo `shopping_cart.sql` localizado no diretório `src/main/resources/sql`.
      - Clique em **Executar** para importar o banco de dados com as tabelas vazias.
    - Certifique-se de que o banco de dados foi importado com sucesso e que as tabelas estão presentes.

3. **Configurar o application.properties**
    - No diretório `src/main/resources`, edite o arquivo `application.properties` com as informações do seu banco de dados:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/shopping_cart
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    ```

4. **Compile o Projeto**
    - No terminal, vá para a pasta raiz do projeto e execute:

    ```bash
    mvn clean install
    ```

5. **Execute a Aplicação**
    - Após a compilação, execute o JAR gerado:

    ```bash
    java -jar target/Shopping-Cart-Manager-1.0.0.jar
    ```

    A aplicação será iniciada no console, e você poderá interagir com ela diretamente por meio de entradas e saídas no terminal.

### Configuração Adicional

- **Banco de Dados**: Caso tenha incluído scripts SQL, eles estarão no diretório `src/main/resources/sql`. Você pode importá-los manualmente no phpMyAdmin.
- **Configuração de Porta**: Caso necessário, altere a porta padrão no arquivo `application.properties`:

    ```properties
    server.port=8081
    ```

Se você encontrar problemas ou tiver dúvidas, sinta-se à vontade para abrir uma **issue** neste repositório!
