import java.util.Scanner;

public class jogoDamas {
    //declaracao de variaveis
    public static Scanner leitor=new Scanner(System.in);
    public static int linha;
    public static int coluna;
    public static char mat[][];
    public static char casaValida='@';
    public static char casaInvalida=' ';
    public static float maxLinhas;
    public static char pedra1='x';
    public static char pedra1X='X';
    public static char pedra2='a';
    public static char pedra2A='A';
    public static int contPedra1=0;
    public static int contPedra2=0;
    public static int turno=0;
    public static int vencedor=0;
    public static int linDest;
    public static int colDest;

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
        System.out.println("quantidade pedras jogador 1 = "+contPedra1);        
        System.out.println("quantidade pedras jogador 2 = "+contPedra2);
        //funcao checar se tem vencedor aqui
        }while(vencedor!=1);
    }//fim da funcao turno

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
            System.out.println("pedra pode comer!!");
        }else{
            System.out.println("pedra nao pode comer!!");
        }
        return podeComer;
    }//fim da funcao checaSimplesComer
    
    //funcao jogar
    public static void jogar(){
        if(turno==0){ //turno do jogador 1
            int lin;
            int col;
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
                        System.out.println("posicao invalida!!");
                        imprimeTabuleiro();
                    }                    
                }while(lin>linha-1 || lin<0 || col>coluna-1 || col<0);
                if(mat[lin][col]!=pedra1 && mat[lin][col]!=pedra1X){
                    System.out.println("pedra invalida ou posicao invalida!!");
                    imprimeTabuleiro();
                }
            }while(mat[lin][col]!=pedra1 && mat[lin][col]!=pedra1X);
            checaSimplesComer(lin,col);
            if(mat[lin][col]=='x'){//verifica se a pedra é simples o movimento que pode fazer
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
                            System.out.println("posicao invalida!!");
                            imprimeTabuleiro();
                        }                         
                    }while(linDest>linha-1 || linDest<0 || colDest>coluna-1 || colDest<0);
                    if(linDest!=lin+1 || (colDest!=col-1 && colDest!=col+1) || mat[linDest][colDest]!=casaValida){
                        System.out.println("lin-"+lin+" col-"+col+" linDest-"+linDest+" colDest-"+colDest+" valor na posicao-"+mat[linDest][colDest]);
                        System.out.println("destino invalido!!");
                        imprimeTabuleiro();
                    }
                }while(linDest!=lin+1 || (colDest!=col-1 && colDest!=col+1) || mat[linDest][colDest]!=casaValida);
                mat[lin][col]=casaValida;
                mat[linDest][colDest]=pedra1;
            }
            }else{ //turno do jogador 2
                int lin;
                int col;
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
                            System.out.println("posicao invalida!!");
                            imprimeTabuleiro();
                        }
                    }while(lin>linha-1 || lin<0 || col>coluna-1 || col<0);
                    if(mat[lin][col]!=pedra2 && mat[lin][col]!=pedra2A){
                        System.out.println("pedra invalida ou posicao invalida!!");
                        imprimeTabuleiro();
                    }
                }while(mat[lin][col]!=pedra2 && mat[lin][col]!=pedra2A);
                checaSimplesComer(lin,col);
                if(mat[lin][col]=='a'){//verifica se a pedra é simples e os movimentos que pode fazer
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
                                System.out.println("posicao invalida!!");
                                imprimeTabuleiro();
                            }                            
                        }while(linDest>linha-1 || linDest<0 || colDest>coluna-1 || colDest<0);
                        if(linDest!=lin-1 || (colDest!=col+1 && colDest!=col-1) || mat[linDest][colDest]!=casaValida){
                            System.out.println("lin-"+lin+" col-"+col+" linDest-"+linDest+" colDest-"+colDest+" valor na posicao-"+mat[linDest][colDest]);
                            System.out.println("destino invalido!!");
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



