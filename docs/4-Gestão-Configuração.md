# Gestão de Configuração

> Nesta parte do documento, é apresentado como foi realizada a
> gestão de configuração do projeto. Isto é, como a ferramenta de
> controle de versão foi configurada, bem como a hospedagem da
> plataforma.

## Controle de Versão

A ferramenta de controle de versão adotada no projeto foi o
[Git](https://git-scm.com/), sendo que o [Github](https://github.com)
foi utilizado para hospedagem do repositório `upstream`.

Para gestão do código fonte do software desenvolvido pela equipe, o grupo utiliza um processo baseado no Gitflow, que é um design de fluxo de trabalho Git que foi criado por Vincent Driessen (2010).
Como há 6 contribuidores neste projeto Diary, é importante o uso do Gitflow para se ter o controle do que está sendo produzido pela equipe, como são corrigidas as falhas e o implemento de funcionalidades. Desta forma, tudo se torna mais organizado. Abaixo há a descrição da forma padrão do projeto:

O projeto segue a seguinte convenção para o nome de branchs:

- `master`: Versão estável já testada do software. É onde teremos o produto final para entregar para o cliente.
- `develop`: Versão de desenvolvimento do software. É para integração das outras branches e funcionalidades, de forma que haja manutenção e verificação se o código está funcionando de acordo com o desejo do cliente. Está em paralelo com o branch master.
- `release`: Prepara o projeto para uma nova versão e quando o branch develop estiver com as funcionalidades prontas para entrega de uma sprint, fazemos o merged com a develop e a master.

Quanto à gerência de issues, o projeto adota a seguinte convenção:

- `hotfix`: É criado quando necessária correção de algum erro e conflito durante a criação do produto. Terá o merged feito no branch ou branches onde apresentarão os erros.
- `feature`: Quando uma nova funcionalidade precisa ser introduzida.

Quanto à gerência de commits, o projeto segue a seguinte convenção:

- `fix`: Quando é necessário a correção de um problema (bug).
- `feat`: Quando inclui um novo código no projeto para uma nova funcionalidade.
- `docs`: Quando se inclui ou altera algo na documentação do projeto.
- `style`: Quando se altera ou inclui algo somente visual (CSS) no sistema.
- `refactor`: Quando se altera o código com a finalidade da melhoria de qualidade do código, sem alterar as funcionalidades ou aparência do projeto.

Quanto à gerência de tags, utilizaremos as tags Anotadas, pois elas são um armazenamento completo de objetos no banco de dados do Git. Elas contem marcações de nome, email, data, entre outras informações importantes para verificar quando preciso, aquela versão.

Quanto à gerência de mergeds:

- `develop`: Quando o código desse branch chegar ao nível estável, todas as alterações devem ser mergeadas de volta ao master juntamente com a branch release.
- `feature`: Assim que concluída, é mesclada (merged) com a branch develop. Nunca irá interagir diretamente com o branch master.
- `release`: Ao final da sprint, quando é feito a tag para a descrição da versão do projeto, este branch é mesclado com a branch master e depois com a develop.
- `hotfix`: É também mesclada com o branch master e develop, após a correção do erro da funcionalidade.

## Hospedagem

> Explique como a hospedagem e o lançamento da plataforma foi feita.
>
> **Links Úteis**:
>
> - [Getting Started with Heroku](https://devcenter.heroku.com/start)
> - [Crie seu Site com o
>   HostGator](https://www.hostgator.com.br/como-publicar-seu-site)
> - [GoDady](https://br.godaddy.com/how-to)
> - [GitHub Pages](https://pages.github.com/)
