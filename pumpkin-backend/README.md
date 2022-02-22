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

Os métodos implementados no repositório possuem o objetivo de buscar e-mails e CPFs já existentes no banco, utilizando uma SQL query setada como nativa. Utilizei Optional<T> para um melhor tratamento do retorno.

![alt text](https://i.ibb.co/gVM0vWt/repositorio.png)

Meu endpoint será chamado de “vacinados”, sendo assim, como padrão do Spring utilizei as @annotations essenciais para o papel do controller seja feito corretamente. Eu poderia simplesmente declarar o PessoaRepository, mas dada a minha forma de organização e preferência, criei um service pois já imaginava a reutilização de um método especifico. A rota do tipo POST, utiliza este método chamando findCpfOrEmailCadastrado, onde o mesmo utiliza as querys do repositório e retorna uma String vazia ou com uma mensagem personalizada dizendo o que foi encontrado. Assim, eu apenas faço um tratamento utilizando essa String como base, e enviando ela para o método personalizado de warning caso ela não esteja vazia ou passando uma nova mensagem e passado ela para o método personalizado de success. Além de claro, passar como parâmetro os requisitos de Bad Request e Created.
