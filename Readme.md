
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

## Vídeo do Jogo
[![preview](mediasDoProjeto/unknown.png)](https://r1---sn-bg07dn6r.c.drive.google.com/videoplayback?expire=1624866345&ei=6UXZYPrvOfCC0_wP08uX2AI&ip=152.249.172.247&cp=QVRHVklfVVNSRlhPOjVwREE1MnBUck1SektuUnRPdGZrdzRtMUpkN2JVZ1FvS0xwTW5Vcl9EaFM&id=11cdc12342dfcc07&itag=18&source=webdrive&requiressl=yes&sc=yes&ttl=transient&susc=dr&driveid=1GXtweUdVgBALA0UBgLl2Ft-Lv1kjOEnw&app=explorer&mime=video/mp4&vprv=1&prv=1&dur=300.094&lmt=1624851478733396&sparams=expire,ei,ip,cp,id,itag,source,requiressl,ttl,susc,driveid,app,mime,vprv,prv,dur,lmt&sig=AOq0QJ8wRgIhAMJ6TAhvqnEgQkVWAVrg4gZR8siT1E6QZiciGEFo2qtAAiEA_3DMa5zsTtufxaLDNe5ye51OKFimOKVS0t0zrP_CMHU=&cpn=KaoDLOoeHdOkhI8I&c=WEB_EMBEDDED_PLAYER&cver=1.20210623.1.0&redirect_counter=1&rm=sn-bg0r77e&req_id=436bf50e4aeb36e2&cms_redirect=yes&ipbypass=yes&mh=ON&mip=2804:431:c7fa:5801:c0cc:a19:9906:5da1&mm=32&mn=sn-bg07dn6r&ms=su&mt=1624851618&mv=m&mvi=1&pl=52&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl,sc&lsig=AG3C_xAwRQIhANFnfs2ytdN4byfMHf7zAeKkSRpO_SITHXaqI7qcGoeJAiBV2zZZvAeToo4xaGNQiKgH1eD_il2ixlQky4toHr9ZhQ%3D%3D)

# Slides do Projeto

## Slides da Prévia
[`Slides`](mediasDoProjeto/ColonizadoresEspaciaisSlides.pdf)

## Slides da Apresentação Final
[Slide Final](https://docs.google.com/presentation/d/11qTxp_TWNRNH8hJwHmlFnG_UQ2e56wFU4XpdZa-8dr0/edit?usp=sharing)

## Relatório de Evolução

> A maior dificuldade encontrada foi pensar em uma arquitetura para o projeto que envolvesse a interface gráfica, pois não tínhamos conhecimento de como ela funcionava e não sabíamos como montar seu planejamento.
> Inicialmente havia uma única classe View, que se comunicava com o Controlador, o que gerava problemas. Pois o controlador atualizava diretamente a View, ao mesmo tempo e que recebia requisoções dela.
> Com a evolução do projeto, fizemos um componente View para cada componente que tivesse uma interface visual. Desse modo, conseguimos aplicar o pattern Observer para que o componente modelo notificasse a view sempre que houvesse uma mudança.
> Com isso, o controlador, passou a se preocupar em notificar somente o tabuleiro sobre requisões do usuário e o próprio tabuleiro e componentes atualizavam a view.

> Esse é o diagrama geral e de componentes antigos, a versão atualizada será mostrada adiante

![Diagrama Geral_Antigo](mediasDoProjeto/diagramaGeralAntigo.png)
![Diagrama Componentes_Antigo](mediasDoProjeto/diagramaComponentesAntigo.png)

> Algo que ficou evidente com o final do projeto, foi a importância da arquitetura e de fazer um bom planejamento. Por falta de conhecimento, não conseguimos fazer isso logo de início e atrapalhou muito o desenvolvimento, pois tivemos que mudar várias vezes durante a contrução do projeto.

# Destaques de Código

> COLCAR PARTE RELEVANTE DO CÓDIGO
~~~java
// Recorte do seu código
public void algoInteressante(…) {
   …
   trechoInteressante = 100;
}
~~~

# Destaques de Pattern
`Adotamos o Pattern observer`

## Diagrama do Pattern

![Diagrama Pattern_Jogador](mediasDoProjeto/patternJogador.png)
![Diagrama Pattern_Planeta](mediasDoProjeto/patternPlaneta.png)


## Código do Pattern

> A classe planeta contém os itens (naves e satélites) e a classe Tile engloba a representação visual do planeta e os itens que ele possui.
> O Tile é um observer do Planeta. Toda vez que um item é adicionado ou removido do planeta, o Planeta notifica automaticamente o Tile, que resedenha seu espaço com os novos itens.
> Quando um planeta é sorteado e gera recurso, a classe Planeta também notifica o Tile, que realiza uma mudança visual para que o usuário veja que aquele planeta foi sorteado.

~~~java
//Planeta notifica Tile
@Override
public boolean Inserir(Item item) {
        if(AvaliarSituacaoIntruso(item)==0){
        List<Item> itensAnteriores = new ArrayList<>();
        itensAnteriores.addAll(itens);
        this.itens.add(item);
        support.firePropertyChange("itens",itensAnteriores,itens);
        return true;
        }
        return false;
        }
        
//Tile Recebe notificações
@Override
public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if(propertyChangeEvent.getPropertyName().equals("sorteado")){
        this.showHighLight("images/highlight.png", 12, 10);
        } else if(propertyChangeEvent.getPropertyName().equals("itens")){
        update((List<Item>) propertyChangeEvent.getNewValue());
        }
        }
~~~

> O componente Jogador apresenta o estado do jogador, com sua pontuação, itens e recursos disponíveis. Esse componente sabe, por exemplo, se o jogador já moveu todos os seus itens na rodada, se é a sua vez de jogar. 
> Conforme essas informações, ele atualiza o componente JogadorGrafico, que as mostra na tela conforme notificado.

~~~java
//Jogador notifica a parte gráfica quando perde um item
public void ExcluirItem(Object[] object){
int statusAnterior[] = status();
int pontuacaoAnterior = pontuacao;
for(int i = 1; i<object.length;i++){
itens.remove(object[i]);
if(object[i] instanceof NaveColonizadora){
pontuacao-=1;
} else if(object[i] instanceof NaveGuerra){
pontuacao-=2;
} else {
pontuacao-=3;
}
}
support.firePropertyChange("status",statusAnterior,status());
support.firePropertyChange("pontuacao",pontuacaoAnterior, pontuacao);
}

//Parte gráfica recebe notificação
@Override
public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if(propertyChangeEvent.getPropertyName().equals("pontuacao")){
        update((int) propertyChangeEvent.getNewValue());
        } else if (propertyChangeEvent.getPropertyName().equals("status")){
        int status[] = (int[]) propertyChangeEvent.getNewValue();
        barraLateral.update(status);
        }
        ....
}
~~~ 

> Aplicamos esse Pattern usando o "PropertyChangeSupport" do Java beans. Com isso, o controle não atualizava mais a View e só se preocupava com a lógica do jogo. Além disso, nesse modelo podemos modificar completamente a view, sem afetar o funcionamento do jogo, administrado pelo controle e pelas classes "modelo".

# Conclusões e Trabalhos Futuros

> Patterns fututos??????????????????????/ Melhorias?????????????????? Lições Aprendidas?????????/
> Uma melhoria que poderia ser implementada é um componente Montador. No início do jogo, parecia que não havia necessidade de usá-lo, mas conforme o jogo cresceu, esse componente poderia ser usado pelo controlador para construir o jogo e conectar observers com seus obsevebles, retornando um jogo pronto para o controle.

# Documentação dos Componentes

# Diagramas

## Diagrama Geral do Projeto
![Diagrama Geral](mediasDoProjeto/diagramaGeralAtualizado.png)

> A classe principal App instancia o Controle. Esse componente instancia o Tabuleiro (que cria seus itens e recursos conforme regras do jogo) e as respectivas partes gráficas dos componentes.
> Ele conecta os componentes com suas partes gráficas e inicia o jogo, coordenando as vezes do jogador e recebendo ações das views e passando para o Tabuleiro, que as administra os itens entre os planetas e valida ações ou requisita ações dos planetas.

## Diagrama Geral de Componentes

![Diagrama geral de componentes](mediasDoProjeto/diagramaComponentesAtualizado.png)


## Componente `Jogador`

>   O Componente Jogador tem como função armazenar a potuação, recursos e itens do jogador e também saber se todos os seus itens foram removidos ou se é a sua vez de jogar.
> para que o controle saiba quando o jogo acabou e quais ações são possíveis no turno atual.

![Diagrama do Componente jogador](mediasDoProjeto/componenteJogador.png)

**Ficha Técnica**
item | detalhamento
---- | ------------
Classe | `Tabuleiro.Jogador`
Autores | `Jéssica & Gabriel`
Interfaces | `Ijogador`


### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](mediasDoProjeto/IJogador.png)

Interface agregadora do componente em Java: 

~~~java
public interface IJogador {
    void setItem(Item item);
    void setPontuacao(int pontuacao);
    int getPontuacao();
    void updateRecursos();
    int getQtdItens();
    void IniciarJogador();
    void setNome(String nome);
}
~~~

## Detalhamento da Interface

### Interface `IJogador`

`A interface contém métodos necessários para que o controle obtenha as informações que precisa para saber, por exemplo, se o jogo ainda continua, se o jogador já moveu todos os itens, se o jogador tem recursos suficientes para construir itens.`

Método | Objetivo
----- | -----
`setItem`| `atualiza os itens iniciais do jogador quando o jogo começa`
`setPontuacao` | `atualiza a pontuação inicial do jogador quando o jogo começa`
`getPontuacao` | `retorna a pontuação atual do jogador para o Controle`
`updateRecursos` | `atualiza todos os recursos do jogador com base nos recursos que seus itens armazenam`
`getQtdItens` | `retorna a quantidade de itens que o jogador possui`
`setNome` | `atualiza o nome do jogador quando digitado pelo usuário`
`IniciarJogador` | `jogador inicia o jogador gráfico para que apareça na tela suas informações iniciais atualizadas`

### Interface `ITrocarVez`

`A interface é compartlhada por mais de um componente. É necessária para os componentes que precisam agir conforme turno do jogo`

Método | Objetivo
----- | -----
`TrocarVez`| `atualiza o jogador para trocar a vez do seu estado atual`

## Componente `Controle`

>   Esse componente administra o estado do Jogador, atualizando sempre que ocorre alguma mudança de pontos e administrando a vez dos jogadores.
> Ele recebe os comandos do usuário pela interface gráfica e requisita as funções específicas para o tabuleiro caso elas sejam validadas.

![Diagrama do Controle](mediasDoProjeto/componenteControle.png)

**Ficha Técnica** 
item | detalhamento
----- | -----
Classe | `View.Controle`
Autores | `Jéssica`
Interfaces | `IControle`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](mediasDoProjeto/Icontrole.png)

Interface agregadora do componente em Java:

~~~java
public interface IControle extends ITrocarVez {
    boolean Mover(int planetaClicado, int planetaRecebeAcao, String objeto);
    int getVez();
    boolean Construir(int id, String objeto);
}
~~~
## Detalhamento da Interface

### Interface `IControle`

`A interface contém os métodos que a view precisa requisitar ao controle conforme ação do jogador`

Método | Objetivo
----- | -----
`Mover`| `recebe o id dos planetas clicados e o nome do objeto a ser movido, requisita o movimento ao tabuleiro e retorna a resposta dele`
`getVez` | `retorna a vez do jogador para que a interface gráfica saiba quais elementos mostrar`
`Construir` | `retorna a pontuação atual do jogador para o Controle`

### Interface `ITrocarVez`

`A interface é compartlhada por mais de um componente. É necessária para os componentes que precisam agir conforme turno do jogo`

~~~java
public interface ITrocarVez {
    void TrocarVez();
}
~~~

Método | Objetivo
----- | -----
`TrocarVez`| `requisitado pela interface gráfica quando usário quer passar sua vez e é usado pelo controle para coordenar turnos`
        
## Componente `Tabuleiro`

> O componente contém uma matriz com todos os planetas. Ele instancia os planetas e os itens iniciais de cada planeta. Éle quem administra o movimento dos itens pelos planetas, requisitando para o planeta inserção, remoção de itens, recursos novos.
> Realiza esses movimento quando o controle requisita sua ação.
> 
![Diagrama do Tabuleiro](mediasDoProjeto/componenteTabuleiro.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `Tabuleiro.Tabuleiro`
Autores | `Jéssica & Gabriel`
Interfaces | `ITabuleiro`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](mediasDoProjeto/ITabuleiro.png)

Interface agregadora em java

~~~java
public interface ITabuleiro extends IGerarRecurso, IConstruir {
    public Object[] Mover(int idDestino, int idOrigem, String itemMovidoTexto) throws RuntimeException;
}
~~~

Método | Objetivo
----- | -----
`Mover`| `recebe os planetas de origem e destino e o nome do item a ser movido, faz as verificações nos planetas e insere e remove o item deles`

## Detalhamento da Interface

### Interface `IConstruir`

`Contem o método construir que é compartilhado por mais de uma interface do jogo.`

~~~java
public interface IConstruir {
    Item Construir(int id, String objeto);
}
~~~

Método | Objetivo
----- | -----
`Construir`| `recebe id do planeta em que será construído um item e o nome do item, retorna se foi possível construir ou não`

### Interface `IGerarRecurso`

`Contem o método para Gerar Recurso que é compartilhado por mais de uma interface do jogo.public interface IGerarRecurso`

~~~java
public interface IGerarRecurso {
    void GerarRecursos();
}
~~~

Método | Objetivo
----- | -----
`GerarRecursos`| `requisita que a classe que implementa esse método gere recurso, no tabuleiro, esse meodo sorteia dois planetas e requisita que eles gerem recursos`


## Componente `Tile`

>   Engloba o Planeta, os Itens instanciados em cada Planeta e os recursos disponíveis nos planetas. 
> É ele que insere ou remove os itens do planeta. Esse componente também conhece seus vizinhos (outros componentes Tile) e fornece essa informação quando necessário para que o tabuleiro possa validar o movimento.

![Diagrama do_Planeta](mediasDoProjeto/componentePlaneta.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe |  `Tabuleiro.Tile`
Autores | `Jéssica & Gabriel`
Interfaces | `IPlaneta`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](mediasDoProjeto/IPlaneta.png)

Interface agregadora do componente em Java:

~~~java
public interface IPlaneta extends IGerarRecurso, IConstruir {
    void Remover(Item item);
    boolean Inserir(Item item);
    List<Item> getItens();
    boolean  isVizinho(int idVizinho);
}
~~~

Método | Objetivo
----- | -----
`Remover`| `planeta remove o item que está nele`
`Inserir`| `planeta insere o item que recebe`
`getItens`| `planeta retorna os itens que possui`
`isVizinho`| `planeta recebe um id e retorna se o id é de um planeta vizinho a ele ou não`

## Detalhamento da Interface

### Interface `IConstruir`

`Contem o método construir que é compartilhado por mais de uma interface do jogo.`

~~~java
public interface IConstruir {
    Item Construir(int id, String objeto);
}
~~~

Método | Objetivo
----- | -----
`Construir`| `recebe id do planeta em que será construído um item e o nome do item, instancia um novo item e insere no planeta`

### Interface `IGerarRecurso`

`Contem o método para Gerar Recurso que é compartilhado por mais de uma interface do jogo.public interface IGerarRecurso`

~~~java
public interface IGerarRecurso {
    void GerarRecursos();
}
~~~

Método | Objetivo
----- | -----
`GerarRecursos`| `requisita que a classe que implementa esse método gere recurso, no Tile, essa classe instancia um novo recurso e o insere no item que está nele`


## Componente `TileView`

>  Esse componente é a interface gŕafica do planeta e seus itens. 
> É atualizado pelo componente Tile toda vez que ele muda seu estado, (desenhando ou excluindo os itens da tela conforme eles se mexem) e também é inicializado pelo Tile.
> O TileView requisita ações para o controle sempre que é clicado
> 
![Diagrama do Tile](mediasDoProjeto/componenteTile.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe |  `View.TileView`
Autores | `Jéssica & Gabriel`
Interfaces | `ITile`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](mediasDoProjeto/ITile.png)

Interface agregadora do componente em Java:

~~~java
public interface ITile {
    void IniciarTile();
    void update();
    void setPlanetaId(int i);
    void setImgpath(String imgpath);
    void setItens(List<Item> items);
    void setPositionsPixels(int[] positions);
}
~~~

## Detalhamento da Interface

### Interface `ITile`

`Contem os métodos que o Planeta precisa para atualizar e inicializar sua versão gráfica`

Método | Objetivo
----- | -----
`setPlanetaId`| `atualiza o id do planeta que o Tile vai representar`
`setImgPath` | `atualiza a imagem do planeta que o Tile vai representar`
`setItens` | `atualiza os itens que o planeta possui, para que o Tile possa representá-los graficamente`
`setPositionPixels` | `atualiza a posição na tela em que o Tile deve desenhar planetas e itens`
`update` | `redesenha o planeta com seus itens, atualizando a imagem gráfica` 
`IniciarTile` | `desenha as informações iniciais na tela para iniciar o jogo`

## Componente `JogadorGrafico`

>  Representa as informações gráficas do jogador na tela (quantidade de itens, recursos e pontuação). É somente visual, portanto não interage recebendo eventos do usuário.

![Diagrama do Jogador_Grafico](mediasDoProjeto/componenteJogadorGrafico.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe |  `View.JogadorGrafico`
Autores | `Jéssica & Gabriel`
Interfaces | `IJogadorGrafico`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](mediasDoProjeto/IJogadorGrafico.png)

Interface agregadora do componente em Java:

~~~java
public interface IJogadorGrafico {
    void setNome(String nome);
    void update(int pontos);
    vois TrocarVez();
}
~~~

## Detalhamento da Interface

### Interface `IJogadorGrafico`

`Contém métodos que o jogador precisa para atualizar sua interface gráfica`

Método | Objetivo
----- | -----
`setNome`| `atualiza o nome do jogador no início do jogo`
`update` | `atualiza a quantidadede pontos que o jogador tem`

### Interface `ITrocarVez`

`A interface é compartlhada por mais de um componente. É necessária para os componentes que precisam agir conforme turno do jogo`

~~~java
public interface ITrocarVez {
    void TrocarVez();
}
~~~

Método | Objetivo
----- | -----
`TrocarVez`| `requisitado pela interface gráfica quando usário quer passar sua vez e é usado pelo controle para coordenar turnos`

# Plano de Exceções

## Diagrama da hierarquia de exceções

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

