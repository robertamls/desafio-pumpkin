# Vacinados | back-end
Construção de uma API REST com apenas um endpoint.

## Stack
- Java 17;
- Maven;
- SpringBoot;
- JPA;
- Hibernate;
- Postgres.

## Configuração
- Criar um database com o nome "vacinados".
- Configurar o arquivo src/main/resources/application.properties para as credenciais do banco local.
- IDE utilizada: IntelliJ.

## Desenvolvimento

Para o início do desenvolvimento da API REST utilizei o Spring Initializr, montando um projeto Maven com a linguagem Java e a versão 2.6.3 do Spring.
Minha escolha por Maven, foi por puro favoritismo, e zona de conforto. Assim como a linguagem JAVA. A versão do Spring já escolhi manter um padrão pelo qual utilizei antes.

Em relação as dependências foram adicionadas o Spring Boot DevTools, uma ferramenta que dá um ótimo suporte para o desenvolvimento, o Spring Web que é voltado para aplicações web e MVC, o Spring Data JPA e PostgreSQL Driver para fazer toda a comunicação com o banco e utilizando Hibernate.

Como padrão MVC criei os pacotes model e controller, dentro de model separei pacotes referenciando ao que se trata, como admin e pessoa, e dentro deles as suas respectivas entidades, e os seus respectivos repositórios e services. Admin, foi criado apenas para uma implementação rápida de Login, e desenvolvimento de um front-end.

Se tratando da comunicação com o banco, além de realizar toda a configuração das credenciais, eu também configurei o modo de operação do Hibernate para update e o seu dialeto. E para garantir todo esse gerenciamento automatizado, as @annotations me auxiliaram nas criações de entidades e validações de atributos, services. Uma ênfase boa a ser dada, é a utilização do GenerationType.IDENTITY, eu poderia muito bem utilizar AUTO e deixar a implementação da especificação escolher uma estratégia para geração dos ids, mas por optar por algo padronizado com AUTO_INCREMENT e menos complexo no momento, eu preferi o Identify ao invés de Table por exemplo.

![alt text](https://i.ibb.co/1m8Ckn5/Pessoa.png)

Os métodos implementados no repositório possuem o objetivo de buscar e-mails e CPFs já existentes no banco, utilizando uma SQL query setada como nativa. Utilizei Optional<T> para um melhor tratamento do retorno, e aproveitar o recurso isPresent(), que ajuda muito nas validações.

![alt text](https://i.ibb.co/gVM0vWt/repositorio.png)

Meu endpoint será chamado de “vacinados”, sendo assim, como padrão do Spring utilizei as @annotations essenciais para que o papel do controller seja feito corretamente. Eu poderia simplesmente declarar o PessoaRepository, mas dada a minha forma de organização e preferência, criei um service pois já imaginava a reutilização de um método especifico. O método pessoas busca todos os cadastros no banco retornando uma coleção.
  
![alt text](https://i.ibb.co/W0WkYR8/all.png)

A rota do tipo POST, utiliza justamente o método citado acima chamando de findCpfOrEmailCadastrado, onde o mesmo utiliza as querys do repositório e retorna uma String vazia ou com uma mensagem personalizada dizendo o que foi encontrado. Assim, eu apenas faço um tratamento utilizando essa String como base, e enviando ela para o método personalizado de warning caso ela não esteja vazia ou passando uma nova mensagem e passado ela para o método personalizado de success. Além de claro, passar como parâmetro os requisitos de Bad Request e Created.

![alt text](https://i.ibb.co/xq5KYxW/post.png)
  
![alt text](https://i.ibb.co/2NbNqCm/service.png)

Esses métodos se encontram em um pacote chamado util que possui uma classe chamada ResponseUtil, com o objetivo de melhorar e especificar as respostas da API.

![alt text](https://i.ibb.co/tsx3LYd/Respose.png)
  
Além da rota GetAll, eu também criei uma outra rota do tipo GET que busca especificamente por ID. Esta utiliza o método padrão findById para tratamento. Aproveitei para usar o status NO_CONTENT já que o mesmo se refere a não haver conteudo para a solicitação.
  
![alt text](https://i.ibb.co/sbxp3Xb/getid.png)
  
A rota de PUT utiliza o método existsById que retorna um booleano, e caso o mesmo seja encontrado faço uma nova validação de CPF e E-mail para conferir que atualização não consiste em dados repetidos que devem ser únicos para só então liberar a alteração. E caso não entre um id existente, retorno novamente um status de NO_CONTENT.
  
![alt text](https://i.ibb.co/GCWcwRd/put.png)

Por fim para a rota DELETE, eu também utilizo o método findById para encontrar o dado, e caso ele exista realizo uma verificação se pessoa está "completo" para então utilizar o deleteById. E novamente caso não encontre, eu retorno um status de NO_CONTENT.

![alt text](https://i.ibb.co/VY2V68S/delete.png)

Como pode ser visto todos os métodos seguem um padrão de tratamento e reutilização de métodos. Eu ressalto que tenho noção de que há métodos de segurança para trabalhar com rotas autenticadas entra outras coisas para garantir a segurança dos dados, mas no caso e pelo tempo limitado e corrido, eu quis implementar um CRUD simples e explicações lógicas dos fluxos para vocês possuirem uma ideia de como penso e concluo.
