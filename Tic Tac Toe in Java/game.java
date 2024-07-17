import java.util.*;
class TicTacToe 
{
    static char box[][];
    public TicTacToe()
    {
        box=new char[3][3];
        initbox();
    }
    void initbox()
    {
        for(int i=0;i<box.length;i++)
        {
            for(int j=0;j<box[i].length;j++)
            {
                box[i][j]=' ';
            }
            System.out.println();
        }
    }
    static void displaybox()
    {
        System.out.println("-----------------");
        for(int i=0;i<box.length;i++)
        {
            System.out.print("| ");
            for(int j=0;j<box[i].length;j++)
            {
                System.out.print(box[i][j]+" | ");
            }
            System.out.println();
            System.out.println("-----------------");
        }
    }
    static void placemark(int row,int col, char mark)
    {
        box[row][col]=mark;
    }
    static boolean checkifcolumnwin()
    {
        for(int j=0;j<=2;j++)
        {
            if(box[0][j]!=' ' && box[0][j]==box[1][j] && box[1][j]==box[2][j])
            {
                return true;
            }
        }
        return false;
    }
    static boolean checkifrowwin()
    {
        for(int i=0;i<=2;i++)
        {
            if(box[i][0]!=' ' && box[i][0]==box[i][1] && box[i][1]==box[i][2])
            {
                return true;
            }
        }
        return false;
    }
    static boolean checkifdiagonalwin()
    {
        if(box[0][0]!=' ' && box[0][0]==box[1][1] && box[1][1]==box[2][2]
        || box[0][2]!=' ' && box[0][2]==box[1][1] && box[1][1]==box[2][0])
        {
            return true;
        }
        return false;
    }
    static boolean isdrawbox()
    {
        for(int i=0;i<box.length;i++)
        {
            for(int j=0;j<box[i].length;j++)
            {
                if(box[i][j]==' ')
                {
                    return false;
                }
            }
        }
        return true;
    }
}
abstract class player
{
    String name;
    char mark;
    abstract void makeMove();
    boolean isValidmove(int row,int col)
    {
        if(row>=0 && row<=2 && col>=0 && col<=2)
        {
            if(TicTacToe.box[row][col]==' ')
            {
                return true;
            }
        }
        return false;
    }
}
class HumanPlayer extends player
{
    Scanner scan = new Scanner(System.in);
    public HumanPlayer(String name,char mark)
    {
        this.name=name;
        this.mark=mark;
    }
    void makeMove()
    {
        int row,col;
        do
        {
        System.out.println("Enter the row and col:");
        row=scan.nextInt();
        col=scan.nextInt();
        }while(!isValidmove(row, col));
        TicTacToe.placemark(row, col, mark);

    }
}
class ComputerPlayer extends player
{
    public ComputerPlayer(String name,char mark)
    {
        this.name=name;
        this.mark=mark;
    }

    void makeMove()
    {
        int row;
        int col;
        do
        {
            Random r = new Random();
            row=r.nextInt(3);
            col=r.nextInt(3);
        }while(!isValidmove(row, col));
        TicTacToe.placemark(row, col, mark);

    }
}
public class game
{
    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        player player1=new HumanPlayer("Prakash Raj S ", 'O');
        player player2=new ComputerPlayer("Dell ", 'X');
        player cp;
        cp=player1;
        while(true)
        {
            System.out.println(cp.name+"make a move");
            cp.makeMove();
            TicTacToe.displaybox();
            if(TicTacToe.checkifcolumnwin() || TicTacToe.checkifrowwin() || TicTacToe.checkifdiagonalwin())
            {
                System.out.println(cp.name+"has won the match");
                break;
            }
            else if(TicTacToe.isdrawbox())
            {
                System.out.println("Draw Match! Game Over");
                break;
            }
            else
            {
                if(cp==player1)
                {
                    cp=player2;
                }
                else
                {
                    cp=player1;
                }
            }
            
        }
    }
}

