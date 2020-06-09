import java.util.Scanner;

public class jogoDamas {
    //declaracao de variaveis
    public static Scanner leitor=new Scanner(System.in);
    public static int linha; //linhas do tabuleiro
    public static int coluna; //colunas do tabuleiro
    public static char mat[][]; //matriz casas e pedras
    public static char casaValida='@'; //casas pretas
    public static char casaInvalida=' '; //casas brancas
    public static float maxLinhas; //maximo de linhas iniciais preenchidas com pedras de um jogador
    public static char pedra1='x'; //pedra simples 1
    public static char pedra1X='X'; //pedra composta 1
    public static char pedra2='a'; //pedra simples 2
    public static char pedra2A='A'; //pedra composta 2
    public static int contPedra1=0; //contador de pedras 1
    public static int contPedra2=0; //contador de pedras 2
    public static int turno=0; //alternador de turno dos jogadores
    public static int vencedor=0; //verificador de vencedor
    public static int linDest; //linha destino pedra
    public static int colDest; //coluna destino pedra

    //funcao inicia matriz
    public static void iniciaMatriz(){
        linha=2;
        do{
            System.out.print("Digite o numero de linhas e colunas do tabuleiro (minimo 3): ");
            linha=leitor.nextInt();
            if(linha<3){
                System.out.println("Tamanho de tabuleiro invalido!!");
            }
        }while(linha<3);
        coluna=linha;
        mat=new char[linha][coluna];
        int alterna=0;
        if(linha%2!=0){
            for(int i=0;i<linha;i++){
                for(int j=0;j<coluna;j++){
                    if(alterna==0){
                        mat[i][j]=casaValida;
                        alterna++;
                    }else{
                        mat[i][j]=casaInvalida;
                        alterna--;
                    }
                }
            }
        }else{
            for(int i=0;i<linha;i++){
                for(int j=0;j<coluna;j++){
                    if(alterna==0){
                        mat[i][j]=casaValida;
                        alterna++;
                    }else{
                        mat[i][j]=casaInvalida;
                        alterna--;
                    }
                }
                if(alterna==0){
                    alterna++;
                }else{
                    alterna--;
                }
            }            
        }
    }//fim funcao inicia matriz

    //funcao inicia pedras dos jogadores no tabuleiro
    public static void iniciaPedras(){
        //define numero maximo de pedras iniciais de cada jogador
        if(linha%2==0){
            maxLinhas=(linha/2)-1;
        }else{
            maxLinhas=(linha/2);
        }
        for(int i=0;i<maxLinhas;i++){
            for(int j=0;j<linha;j++){
                if(mat[i][j]==casaValida){
                    mat[i][j]=pedra1;
                }
            }
        }
        if(linha%2==0){
            for(int i=linha-1;i>maxLinhas+1;i--){
                for(int j=0;j<linha;j++){
                    if(mat[i][j]==casaValida){
                        mat[i][j]=pedra2;
                    }
                }
            }
        }else{
            for(int i=linha-1;i>maxLinhas;i--){
                for(int j=0;j<linha;j++){
                    if(mat[i][j]==casaValida){
                        mat[i][j]=pedra2;
                    }
                }
            }            
        }
        for(int i=0;i<linha;i++){
            for(int j=0;j<linha;j++){
                if(mat[i][j]==pedra1 || mat[i][j]==pedra1X){
                    contPedra1++;
                }
                if(mat[i][j]==pedra2 || mat[i][j]==pedra2A){
                    contPedra2++;
                }
            }
        }                
    }//fim funcao inicia pedras
    
    //funcao imprime tabuleiro
    public static void imprimeTabuleiro(){
        System.out.print(" ");        
        for(int i=0;i<linha;i++){
            System.out.print(" "+(i+1));
        }
        System.out.println();
        for(int i=0;i<linha;i++){
            System.out.print((i+1)+"|");
            for(int j=0;j<coluna;j++){
                System.out.print(mat[i][j]+"|");
            }
            System.out.println();            
        }
    }//fim da funcao imprime tabuleiro
    
    //funcao turno
    public static void turno(){
        do{
        if(turno==0){
            jogar();
            turno++;
        }else{
            jogar();
            turno--;
        }
        imprimeTabuleiro();
        System.out.println("quantidade pedras (x,X) jogador 1 = "+contPedra1);        
        System.out.println("quantidade pedras (a,A) jogador 2 = "+contPedra2);
        //funcao checar se tem vencedor aqui
        }while(vencedor!=1);
    }//fim da funcao turno

    //funcao checa se há movimento possivel para pedra
    public static int movimentoPossivel(int lin_,int col_){
        int possivel=0;
        int opcao=0;
        if(mat[lin_][col_]==pedra1){//checa opcoes pedra simples jogador 1
            if(lin_+1<linha && col_-1>=0 && mat[lin_+1][col_-1]==casaValida){
                opcao=1; //checa inferior esquerdo
            }
            if(lin_+1<linha && col_+1<coluna && mat[lin_+1][col_+1]==casaValida){
                opcao=1; //checa inferior direito
            }
            if(checaSimplesComer(lin_,col_)==1){
                opcao=1;
            }
        }else{//checa opcoes pedra simples jogador 2
            if(lin_-1>=0 && col_-1>=0 && mat[lin_-1][col_-1]==casaValida){
                opcao=1; //checa superior esquerdo
            }
            if(lin_-1>=0 && col_+1<coluna && mat[lin_-1][col_+1]==casaValida){
                opcao=1; //checa superior direito
            }
            if(checaSimplesComer(lin_,col_)==1){
                opcao=1;
            }                        
        }
        if(opcao==1){
            possivel=1;
        }else{
            System.out.println("Não há movimento possivel para pedra ("+(lin_+1)+","+(col_+1)+")!!");
            imprimeTabuleiro();
        }
        return possivel;
    }

    //funcao checa se pedra simples pode comer pedra
    public static int checaSimplesComer(int lin_,int col_){
        int podeComer=0;
        if(mat[lin_][col_]==pedra1){//checa se jogador 1 pode comer com pedra simples
            if(lin_-1>0){//comer superior esquerda
                if(col_-1>0){
                    if(mat[lin_-1][col_-1]==pedra2 || mat[lin_-1][col_-1]==pedra2A){
                        if(lin_-2>=0){
                            if(col_-2>=0){
                                if(mat[lin_-2][col_-2]==casaValida){
                                    podeComer=1;
                                }
                            }
                        }
                    }
                }
            }
            if(lin_-1>0){//comer superior direita
                if(col_+1>0 && col_+1<coluna){
                    if(mat[lin_-1][col_+1]==pedra2 || mat[lin_-1][col_+1]==pedra2A){
                        if(lin_-2>=0){
                            if(col_+2<coluna){
                                if(mat[lin_-2][col_+2]==casaValida){
                                    podeComer=1;
                                }
                            }
                        }
                    }
                }
            }
            if(lin_+1<linha){//comer inferior esquerda
                if(col_-1>0){
                    if(mat[lin_+1][col_-1]==pedra2 || mat[lin_+1][col_-1]==pedra2A){
                        if(lin_+2<linha){
                            if(col_-2>=0){
                                if(mat[lin_+2][col_-2]==casaValida){
                                    podeComer=1;
                                }
                            }
                        }
                    }
                }
            }
            if(lin_+1<linha){//comer inferior direita
                if(col_+1<coluna){
                    if(mat[lin_+1][col_+1]==pedra2 || mat[lin_+1][col_+1]==pedra2A){
                        if(lin_+2<linha){
                            if(col_+2<coluna){
                                if(mat[lin_+2][col_+2]==casaValida){
                                    podeComer=1;
                                }
                            }
                        }
                    }
                }
            }                        
        }else{//checa se jogador 2 pode comer com pedra simples
            if(lin_-1>0){//comer superior esquerda
                if(col_-1>0){
                    if(mat[lin_-1][col_-1]==pedra1 || mat[lin_-1][col_-1]==pedra1X){
                        if(lin_-2>=0){
                            if(col_-2>=0){
                                if(mat[lin_-2][col_-2]==casaValida){
                                    podeComer=1;
                                }
                            }
                        }
                    }
                }
            }
            if(lin_-1>0){//comer superior direita
                if(col_+1>0 && col_+1<coluna){
                    if(mat[lin_-1][col_+1]==pedra1 || mat[lin_-1][col_+1]==pedra1X){
                        if(lin_-2>=0){
                            if(col_+2<coluna){
                                if(mat[lin_-2][col_+2]==casaValida){
                                    podeComer=1;
                                }
                            }
                        }
                    }
                }
            }
            if(lin_+1<linha){//comer inferior esquerda
                if(col_-1>0){
                    if(mat[lin_+1][col_-1]==pedra1 || mat[lin_+1][col_-1]==pedra1X){
                        if(lin_+2<linha){
                            if(col_-2>=0){
                                if(mat[lin_+2][col_-2]==casaValida){
                                    podeComer=1;
                                }
                            }
                        }
                    }
                }
            }
            if(lin_+1<linha){//comer inferior direita
                if(col_+1<coluna){
                    if(mat[lin_+1][col_+1]==pedra1 || mat[lin_+1][col_+1]==pedra1X){
                        if(lin_+2<linha){
                            if(col_+2<coluna){
                                if(mat[lin_+2][col_+2]==casaValida){
                                    podeComer=1;
                                }
                            }
                        }
                    }
                }
            }             
        }
        if(podeComer==1){
            System.out.println("Pedra ("+(lin_+1)+","+(col_+1)+") pode comer!!");
        }else{
            System.out.println("Pedra ("+(lin_+1)+","+(col_+1)+") nao pode comer!!");
        }
        return podeComer;
    }//fim da funcao checaSimplesComer
    
    //funcao jogar
    public static void jogar(){
        if(turno==0){ //turno do jogador 1
            int lin;
            int col;
            do{
                do{ //jogador 1 seleciona pedra FUNCIONANDO OK
                    do{
                        System.out.println("TURNO DO JOGADOR 1");
                        System.out.print("Jogador 1 selecione a pedra (linha) ");
                        lin=leitor.nextInt();
                        lin--;
                        System.out.print("Jogador 1 selecione a pedra (coluna) ");
                        col=leitor.nextInt();
                        col--;
                        if(lin>linha-1 || lin<0 || col>coluna-1 || col<0){
                            System.out.println("Posicao invalida!!");
                            imprimeTabuleiro();
                        }                    
                    }while(lin>linha-1 || lin<0 || col>coluna-1 || col<0);
                    if(mat[lin][col]!=pedra1 && mat[lin][col]!=pedra1X){
                        System.out.println("Pedra invalida ou posicao invalida!!");
                        imprimeTabuleiro();
                    }
                }while(mat[lin][col]!=pedra1 && mat[lin][col]!=pedra1X);
            }while(movimentoPossivel(lin,col)!=1);
            //checaSimplesComer(lin,col);
            //DESENVOLVER JOGADA COMER PEDRA
            if(mat[lin][col]==pedra1){//verifica se a pedra é simples o movimento que pode fazer
                do{
                    do{
                        System.out.println("TURNO DO JOGADOR 1");  
                        System.out.print("Jogador 1 selecione a destino da pedra ("+(lin+1)+","+(col+1)+") (linha) ");
                        linDest=leitor.nextInt(); 
                        linDest--;
                        System.out.print("Jogador 1 selecione a destino da pedra ("+(lin+1)+","+(col+1)+") (coluna) ");
                        colDest=leitor.nextInt();
                        colDest--;
                        if(linDest>linha-1 || linDest<0 || colDest>coluna-1 || colDest<0){
                            System.out.println("Destino invalido!!");
                            imprimeTabuleiro();
                        }                         
                    }while(linDest>linha-1 || linDest<0 || colDest>coluna-1 || colDest<0);
                    if(linDest!=lin+1 || (colDest!=col-1 && colDest!=col+1) || mat[linDest][colDest]!=casaValida){
                        System.out.println("lin-"+lin+" col-"+col+" linDest-"+linDest+" colDest-"+colDest+" valor na posicao-"+mat[linDest][colDest]);
                        System.out.println("Destino invalido!!");
                        imprimeTabuleiro();
                    }
                }while(linDest!=lin+1 || (colDest!=col-1 && colDest!=col+1) || mat[linDest][colDest]!=casaValida);
                mat[lin][col]=casaValida;
                mat[linDest][colDest]=pedra1;
            }
            }else{ //turno do jogador 2
                int lin;
                int col;
                do{
                    do{ //jogador 2 seleciona pedra FUNCIONANDO OK
                        do{
                        System.out.println("TURNO DO JOGADOR 2");                        
                        System.out.print("Jogador 2 selecione a pedra (linha) ");
                        lin=leitor.nextInt();
                        lin--;
                        System.out.print("Jogador 2 selecione a pedra (coluna) ");
                        col=leitor.nextInt();
                        col--;
                            if(lin>linha-1 || lin<0 || col>coluna-1 || col<0){
                                System.out.println("Posicao invalida!!");
                                imprimeTabuleiro();
                            }
                        }while(lin>linha-1 || lin<0 || col>coluna-1 || col<0);
                        if(mat[lin][col]!=pedra2 && mat[lin][col]!=pedra2A){
                            System.out.println("Pedra invalida ou posicao invalida!!");
                            imprimeTabuleiro();
                        }
                    }while(mat[lin][col]!=pedra2 && mat[lin][col]!=pedra2A);
                }while(movimentoPossivel(lin,col)!=1);
                //checaSimplesComer(lin,col);
                //DESENVOLVER FUNCAO COMER PEDRA
                if(mat[lin][col]==pedra2){//verifica se a pedra é simples e os movimentos que pode fazer
                    do{
                        do{
                            System.out.println("TURNO DO JOGADOR 2");  
                            System.out.print("Jogador 2 selecione a destino da pedra ("+(lin+1)+","+(col+1)+") (linha) ");
                            linDest=leitor.nextInt(); 
                            linDest--;
                            System.out.print("Jogador 2 selecione a destino da pedra ("+(lin+1)+","+(col+1)+") (coluna) ");
                            colDest=leitor.nextInt();
                            colDest--;
                            if(linDest>linha-1 || linDest<0 || colDest>coluna-1 || colDest<0){
                                System.out.println("Destino invalido!!");
                                imprimeTabuleiro();
                            }                            
                        }while(linDest>linha-1 || linDest<0 || colDest>coluna-1 || colDest<0);
                        if(linDest!=lin-1 || (colDest!=col+1 && colDest!=col-1) || mat[linDest][colDest]!=casaValida){
                            System.out.println("lin-"+lin+" col-"+col+" linDest-"+linDest+" colDest-"+colDest+" valor na posicao-"+mat[linDest][colDest]);
                            System.out.println("Destino invalido!!");
                            imprimeTabuleiro();
                        }
                    }while(linDest!=lin-1 || (colDest!=col+1 && colDest!=col-1) || mat[linDest][colDest]!=casaValida);
                    mat[lin][col]=casaValida;
                    mat[linDest][colDest]=pedra2;            
                }
            }
    }//fim da funcao jogar
    
    //funcao main
    public static void main(String[] args){
        iniciaMatriz();
        iniciaPedras();
        imprimeTabuleiro();
        turno();
    }//fim funcao main
    
}// fim arquivo JogoDamas



