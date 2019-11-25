package com.noamls_amirbs.ex1;

import java.util.Random;
public class GameBoard
{

    public int [][] mixBoard(int [][] board)
    {
        int [] array = new int[16];
        int index = 0;
        Random rand = new Random();
        for(int i = 0; i<4; i++)
        {
            for(int j = 0; j<4; j++)
            {
                if(i == 3 && j == 3)
                    continue;
                int n = rand.nextInt(15)+1;

                if(index == 0 || checkRandomNumber(array,index,n))
                {

                    array[index] = n;
                    board[i][j] = n;
                    index++;

                }
                else
                    j--;
            }
        }
        return board;
    }

    public Boolean checkRandomNumber(int []array, int index, int n)
    {
        for(int i = 0; i<index; i++)
        {
            if(n == array[i])
                return false;
        }
        return true;
    }

    public int [] moveOption(int i, int j )
    {
        int [] resArray = new int [4];

        for(int index = 0; index<4 ; index++)
            resArray[index] = -1;

        if(i == 0 && j == 0)
        {
            resArray[0] = 2;
            resArray[1] = 5;
        }
        else if(i == 0  && j == 1)
        {
            resArray[0] = 1;
            resArray[1] = 3;
            resArray[2] = 6;
        }
        else if(i == 0 && j == 2)
        {
            resArray[0] = 2;
            resArray[1] = 4;
            resArray[2] = 7;
        }
        else if(i == 0 && j == 3)
        {
            resArray[0] = 3;
            resArray[1] = 8;
        }
        else if(i == 1 && j == 0)
        {
            resArray[0] = 1;
            resArray[1] = 6;
            resArray[2] = 9;
        }
        else if(i == 1 && j == 1)
        {
            resArray[0] = 2;
            resArray[1] = 5;
            resArray[2] = 7;
            resArray[3] = 10;
        }
        else if(i == 1 && j == 2 )
        {
            resArray[0] = 3;
            resArray[1] = 6;
            resArray[2] = 8;
            resArray[3] = 11;
        }
        else if(i == 1 && j == 3 )
        {
            resArray[0] = 4;
            resArray[1] = 7;
            resArray[2] = 12;
        }
        else if(i == 2 && j == 0 )
        {
            resArray[0] = 5;
            resArray[1] = 10;
            resArray[2] = 13;
        }
        else if(i == 2 && j == 1 )
        {
            resArray[0] = 6;
            resArray[1] = 9;
            resArray[2] = 11;
            resArray[3] = 14;
        }
        else if(i == 2 && j == 2 )
        {
            resArray[0] = 7;
            resArray[1] = 10;
            resArray[2] = 12;
            resArray[3] = 15;
        }
        else if(i == 2 && j == 3 )
        {
            resArray[0] = 8;
            resArray[1] = 11;
            resArray[2] = 16;
        }
        else if(i == 3 && j == 0 )
        {
            resArray[0] = 9;
            resArray[1] = 14;
        }
        else if(i == 3 && j == 1 )
        {
            resArray[0] = 13;
            resArray[1] = 10;
            resArray[2] = 15;
        }
        else if(i == 3 && j == 2 )
        {
            resArray[0] = 11;
            resArray[1] = 14;
            resArray[2] = 16;
        }
        else if(i == 3 && j == 3 )
        {
            resArray[0] = 12;
            resArray[1] = 15;
        }
        return resArray;
    }

    public int [] boradCoordinate(int num)
    {
        int [] resArray = new int [2];
        switch (num)
        {
            case 1:
                resArray[0] = 0;
                resArray[1] = 0;

                break;
            case 2:
                resArray[0] = 0;
                resArray[1] = 1;

                break;

            case 3:
                resArray[0] = 0;
                resArray[1] = 2;

                break;

            case 4:
                resArray[0] = 0;
                resArray[1] = 3;

                break;

            case 5:
                resArray[0] = 1;
                resArray[1] = 0;

                break;

            case 6:
                resArray[0] = 1;
                resArray[1] = 1;

                break;

            case 7:
                resArray[0] = 1;
                resArray[1] = 2;

                break;

            case 8:
                resArray[0] = 1;
                resArray[1] = 3;

                break;

            case 9:
                resArray[0] = 2;
                resArray[1] = 0;

                break;

            case 10:
                resArray[0] = 2;
                resArray[1] = 1;

                break;

            case 11:
                resArray[0] = 2;
                resArray[1] = 2;

                break;

            case 12:
                resArray[0] = 2;
                resArray[1] = 3;

                break;

            case 13:
                resArray[0] = 3;
                resArray[1] = 0;

                break;

            case 14:
                resArray[0] = 3;
                resArray[1] = 1;

                break;

            case 15:
                resArray[0] = 3;
                resArray[1] = 2;

                break;

            case 16:
                resArray[0] = 3;
                resArray[1] = 3;




        }
        return resArray;
    }


    public Boolean winGame(int [][] board)
    {
        int number = 1;
        for(int i =0; i<4; i++)
        {
            for(int j =0; j<4; j++)
            {
                if(i == 3 && j == 3)
                    continue;
                if(board[i][j] != number)
                    return false;
                number++;
            }
        }
        return true;
    }

    public String setMoves(int count)
    {
        String str,str2;
        long  num = 1000;
        if(count >= 1000)
        {
            str = String.valueOf(count);
        }

        num+=count;
        str = String.valueOf(num);

        str2 = str.replaceFirst("1","0");

        return str2;

    }


}
