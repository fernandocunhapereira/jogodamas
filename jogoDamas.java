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
    /*public static void checaSimplesComer(){
        if(mat[lin][col]==pedra1){

        }else{

        }
    }*/
    
    //funcao jogar
    public static void jogar(){
        if(turno==0){ //turno do jogador 1
            int lin;
            int col;
            do{ //jogador 1 seleciona pedra FUNCIONANDO OK
                do{
                    System.out.print("Jogador 1 selecione a pedra (linha) ");
                    lin=leitor.nextInt();
                    lin--;
                    System.out.print("Jogador 1 selecione a pedra (coluna) ");
                    col=leitor.nextInt();
                    col--;
                    if(lin>linha-1 || lin<0 || col>coluna-1 || col<0){
                        System.out.println("posicao invalida!!");
                    }                    
                }while(lin>linha-1 || lin<0 || col>coluna-1 || col<0);
                if(mat[lin][col]!=pedra1 && mat[lin][col]!=pedra1X){
                    System.out.println("pedra invalida ou posicao invalida!!");
                }
            }while(mat[lin][col]!=pedra1 && mat[lin][col]!=pedra1X);
            if(mat[lin][col]=='x'){//verifica se a pedra é simples o movimento que pode fazer
                do{
                    System.out.print("Jogador 1 selecione a destino da pedra ("+(lin+1)+","+(col+1)+") (linha) ");
                    linDest=leitor.nextInt(); 
                    linDest--;
                    System.out.print("Jogador 1 selecione a destino da pedra ("+(lin+1)+","+(col+1)+") (coluna) ");
                    colDest=leitor.nextInt();
                    colDest--;
                    if(linDest!=lin+1 || (colDest!=col-1 && colDest!=col+1) || mat[linDest][colDest]!=casaValida){
                        System.out.println("lin-"+lin+" col-"+col+" linDest-"+linDest+" colDest-"+colDest+" valor na posicao-"+mat[linDest][colDest]);
                        System.out.println("destino invalido!!");
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
                System.out.print("Jogador 2 selecione a pedra (linha) ");
                lin=leitor.nextInt();
                lin--;
                System.out.print("Jogador 2 selecione a pedra (coluna) ");
                col=leitor.nextInt();
                col--;
                    if(lin>linha-1 || lin<0 || col>coluna-1 || col<0){
                        System.out.println("posicao invalida!!");
                    }
                }while(lin>linha-1 || lin<0 || col>coluna-1 || col<0);
                if(mat[lin][col]!=pedra2 && mat[lin][col]!=pedra2A){
                    System.out.println("pedra invalida ou posicao invalida!!");
                }
            }while(mat[lin][col]!=pedra2 && mat[lin][col]!=pedra2A);
            if(mat[lin][col]=='a'){//verifica se a pedra é simples e os movimentos que pode fazer
                do{ 
                    System.out.print("Jogador 2 selecione a destino da pedra ("+(lin+1)+","+(col+1)+") (linha) ");
                    linDest=leitor.nextInt(); 
                    linDest--;
                    System.out.print("Jogador 2 selecione a destino da pedra ("+(lin+1)+","+(col+1)+") (coluna) ");
                    colDest=leitor.nextInt();
                    colDest--;
                    if(linDest!=lin-1 || (colDest!=col+1 && colDest!=col-1) || mat[linDest][colDest]!=casaValida){
                        System.out.println("lin-"+lin+" col-"+col+" linDest-"+linDest+" colDest-"+colDest+" valor na posicao-"+mat[linDest][colDest]);
                        System.out.println("destino invalido!!");
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



