
# Projeto `Colonizadores Espaciais`

# Descrição Resumida do Jogo
O jogo é formado por um tabuleiro com 16 planetas e é jogado por duas pessoas.
Existem os itens: nave de guerra, nave colonizadora e satélite e os recursos: metal,
munição e combustível.
O jogo começa com cada jogador em um planeta aleatório, com uma nave colonizadora e
um satélite nele.
A cada rodada, os jogadores têm sua vez e podem optar por se mover para um planeta ou
construir um item (se tiver recursos disponíveis). Se se moverem para um planeta com nave
inimiga, haverá uma luta, sendo a ordem de força satélite>nave de guerra> nave
colonizadora respeitada. Após as jogadas, é sorteado dois planetas, que oferecem os
recursos que possuem para o jogador que está nele. Quanto mais planeta o jogador
colonizar, mais chances tem de ganhar mais recursos.
Ganha o jogador que fizer 12 pontos primeiro (cada item construído
# Equipe
* `Jéssica  Da Silva De Oliveira` - `RA173931`
* `Gabriel Lima Luz` - `RA177836`

# Vídeos do Projeto

## Vídeo da Prévia
[![preview](mediasDoProjeto/video.png)](https://drive.google.com/file/d/1sm055svD8BkM5IXoianjSeHf3vuLuPKr/view?usp=sharing)


# Slides do Projeto

## Slides da Prévia
[`Slides`](mediasDoProjeto/ColonizadoresEspaciaisSlides.pdf)

# Documentação dos Componentes

# Diagramas

## Diagrama Geral do Projeto
![Diagrama Geral](mediasDoProjeto/diagramageral.png)

> A classe principal App instancia as classes "Controle", "View" e "Tabuleiro". Cada uma dessas classes é responsável por instanciar os itens que a contem.
> A View cria o contexto com a interface Gráfica e o Tabuleiro cria os planetas com seus respectivos itens.
> O App faz uma conexão entre o a parte visual do planeta com a a classe "Planeta", para que essa classe atualize diretamente a View quando muda seu estado.
> O controle recebe os comandos da View e os requisita para o tabuleiro e também controla a vez do jogador.

## Diagrama Geral de Componentes

![Diagrama geral de componentes](mediasDoProjeto/diagramageralcomponentes.png)


## Componente `Jogador`

>   O Componente Jogador tem como função armazenar a potuação, recursos e itens do jogador
> para que o controle saiba quando o jogo acabou e quais ações são possíveis no turno atual\
![Diagrama do Componente jogador](mediasDoProjeto/CJogador.png)

**Ficha Técnica**
item | detalhamento
---- | ------------
Classe | `Tabuleiro.Jogador`
Autores | `Jéssica & Gabriel`
Interfaces | `Ijogador`


### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](mediasDoProjeto/Ijogador.png)

Interface agregadora do componente em Java: 

~~~java
public interface IJogador {
    void AdicionarItem(Item item);
    boolean RemoverItem(Item item);
}
~~~
## Componente `Controle`

>   Esse componente administra o estado do Jogador, atualizando sempre que ocorre alguma mudança de pontos e administrando a vez dos jogadores.
> Ele recebe os comandos do usuário pela interface gráfica e requisita as funções específicas para o tabuleiro caso elas sejam validadas.

![Diagrama do Componente jogador](mediasDoProjeto/CControle.png)

**Ficha Técnica** 
item | detalhamento
----- | -----
Classe | `Tabuleiro.Controle`
Autores | `Jéssica`
Interfaces | `IControle`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](mediasDoProjeto/Icontrole.png)

Interface agregadora do componente em Java:

~~~java
public interface IControle {
    boolean Construir(Item item);
    boolean Mover(Item item);
}
~~~
## Componente `Tabuleiro`

>   O Componente Jogador tem como função armazenar a potuação, recursos e itens do jogador
> para que o controle saiba quando o jogo acabou e quais ações são possíveis no turno atual\
![Diagrama do Componente jogador](mediasDoProjeto/CTabuleiro.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `Tabuleiro.Tabuleiro`
Autores | `Jéssica & Gabriel`
Interfaces | `ITabuleiro`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](mediasDoProjeto/ITabuleiro.png)


~~~java
public interface ITabuleiro {
    void Mover(int idDestino, int idOrigem, String itemMovido);
    void Construir(int id, String objeto);
}
~~~
## Componente `Tile`

>   Envolve o Planeta, os Itens instanciados em cada Planeta e os recursos disponíveis nos planetas. 
> É ele que insere ou remove itens do planeta. Esse componente também conhece seus vizinhos (outros componentes Tile) e fornece essa informação quando necessário para que o tabuleiro possa validar o movimento.
![Diagrama do Componente jogador](mediasDoProjeto/CTile.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe |  `Tabuleiro.Tile`
Autores | `Jéssica & Gabriel`
Interfaces | `ITile`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](mediasDoProjeto/ITile.png)

Interface agregadora do componente em Java:

~~~java
public interface ITile {
    boolean isVizinho(int id);
    Item Construir(String objeto);
    Item Remover(String itemRemovido);
    Item Inserir(Item itemInserido);
}
~~~
## Componente `TileView`

>  Esse componente é a interface gŕafica do planeta e seus itens. 
> É atualizado pelo componente Tile toda vez que ele muda seu estado, (desenhando ou excluindo os itens da tela conforme eles se mexem) e também é inicializado pelo Tile.
> O TileView é um botão que requisita ações para View sempre que é clicado.
> 
![Diagrama do Componente jogador](mediasDoProjeto/CTileView.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe |  `View.TileView`
Autores | `Jéssica & Gabriel`
Interfaces | `ItileView`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](mediasDoProjeto/ITileView.png)

Interface agregadora do componente em Java:

~~~java
public interface ITileView {
    void Update(List<Item> itens);
    void IniciarTile();
}
~~~
## Componente `View`

> Organiza toda a estrutura gráfica do Jogo, mostrando na tela o menu com os materiais e itens dos jogadores, e os botões específicos conforme as regras do jogo.
> 
![Diagrama do Componente jogador](mediasDoProjeto/CView.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe |  `View`
Autores | `Jéssica & Gabriel`
Interfaces | `IView`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](mediasDoProjeto/IView.png)

Interface agregadora do componente em Java:

~~~java
public interface IView {
    void ClicarBarra(ActionEvent actionEvent);
    void ClicarPlaneta(ActionEvent actionEvent);
    void ClicarConstruir(ActionEvent actionEvent);
}
~~~
## Detalhamento das Interfaces

### Interface `IItem`

Padronizar e modularizar a comunicação entre as classes Item e Planeta

~~~java
public interface IItem {
    public static List<Recursos> coletados = new ArrayList<Recursos>();
    // acoes de jogo
    int lutar();
    void  recolherRecurso(Recursos coletado);
    // getters e setters
    List<Integer> getPosition();
    List<Recursos> getColetados();
    String getType();
    String getImgPath();
}

~~~

Método | Objetivo
-------| --------
`lutar` | Retorna um int que quando comparado com o valor fornecido por outro item determina quem ganhou a luta.
`recolherRecurso` | !!um pequeno problema!!Recebe um objeto da classe Recursos e adiciona ao atributo estático coletados que armazena todos os recursos coletados pelo jogador .
`getPosition`| Retorna uma lista no formato [i,j] que representa a qual planeta da matriz o item pertence 
`getColetados`|retorna uma lista de recursos coletados pelos itens
`getType` | Retorna uma string informando o nome da classe.
`getImgpath` | Retorna uma string com o caminho para o arquivo da imagem para o item
`.

# Plano de Exceções

## Diagrama da hierarquia de exceções
`<Elabore um diagrama com a hierarquia de exceções como detalhado abaixo>`

![Hierarquia Exceções](mediasDoProjeto/exceções.png)

## Descrição das classes de exceção

`<Monte uma tabela descritiva seguindo o exemplo>:`

Classe | Descrição
----- | -----
InvalidImgPath | Engloba todos os erros de caminho para imagens
InvalidItemIcon | indica erro no caminho de uma imagem de um item
InvalidPLanetIcon |indica erro no caminho de uma imagem de um planeta
InvalidMovement | Engloba movimentos invalidos
MovementOutOfReach | indica que o planeta de destino é invalido
MovementBLockedByNaveColonizadora | indica que o planeta de destino está bloquado por outra nave colonizadora
ItemAlreadyMoved | Indica que a peça já foi movimentada
UnableToBuild | Engloba erros de Construção
NotEnoughRecursos | Indica que o jogador nao possui recursos suficientes
NotEnoughSpace | Indica que não há espaço suficiente no planeta para construir mais itens 

