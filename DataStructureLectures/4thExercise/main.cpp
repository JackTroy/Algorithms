#include <stdio.h>
#include <stdlib.h>
#include <math.h>
typedef int Item;
#include "SymmetricalMatrix.cpp"
#include "TriangularMatrix.cpp"
#include "DiagonalMatrix.cpp"
int main()
{
    //使用while循环选择特殊矩阵的种类，然后内while循环再选择功能
    int choice;
    while (true)
    {
        printf("**********************************\n");
        printf("Please choose the type of matrix.\n");
        printf("1.Symmetrical matrix\n");
        printf("2.Lower triangular matrix\n");
        printf("3.Diagonal matrix.\n");
        printf("0.Exit\n");
        printf("**********************************\n");
        scanf("%d", &choice);
        if (choice == 0)    break;        
        switch (choice)
        {
        //Symmetrical matrix
        case 1:
        {
            printf("Please input the size of matrix between 1 and 10.\n");
            int size;
            scanf("%d", &size);
            SMatrix a = InitSMatrix(size);
            int count = (1 + size) * size / 2;
            Item item;
            while (a->count < count)
            {
                printf("Please input integer as item.\n");
                scanf("%d", &item);
                addSMatrixItem(a, item);
            }
            while (true)
            {
                printf("**********************************************************\n");
                printf("1.Get item with index\n");
                printf("2.Show the matrix\n");
                printf("0.Exit\n");
                printf("Please input your choice.\n");
                printf("**********************************************************\n");
                scanf("%d", &choice);
                if (choice == 0)    break;                   
                if (choice == 1)
                {
                    int row, col;
                    printf("Please input row number.\n");
                    scanf("%d", &row);
                    printf("Please input col number.\n");
                    scanf("%d", &col);
                    printf("The item is %d\n", getSMatrixItem(a, row, col));
                }
                else    showSMatrix(a);                  
            }
            break;
        }
        //Lower triangular matrix
        case 2:
        {
            int size, c;
            printf("Please input the size of matrix between 1 and 10.\n");
            scanf("%d", &size);
            printf("Pleae input the constant integer as items of upper triangle.\n");
            scanf("%d", &c);
            TMatrix a = InitTMatrix(size, c);
            int count = (1 + size) * size / 2;
            Item item;
            while (a->count < count)
            {
                printf("Please input integer as item.\n");
                scanf("%d", &item);
                addTMatrixItem(a, item);
            }
            while (true)
            {
                printf("**********************************************************\n");
                printf("1.Get item with index\n");
                printf("2.Show the matrix\n");
                printf("0.Exit\n");
                printf("Please input your choice.\n");
                printf("**********************************************************\n");
                scanf("%d", &choice);
                if (choice == 0)    break;                  
                if (choice == 1)
                {
                    int row, col;
                    printf("Please input row number.\n");
                    scanf("%d", &row);
                    printf("Please input col number.\n");
                    scanf("%d", &col);
                    printf("The item is %d\n", getTMatrixItem(a, row, col));
                }
                else    showTMatrix(a);                   
            }
            break;
        }
        //Diagonal matrix
        case 3:
        {
            int size, width;
            printf("Please input the size of matrix between 1 and 10.\n");
            scanf("%d", &size);
            printf("Pleae input the odd number of diagonal lines.\n");
            scanf("%d", &width);
            DMatrix a = InitDMatrix(size, width);
            int count = size * width - ((width - 1) / 2) * ((width - 1) / 2 + 1);
            Item item;
            while (a->count < count)
            {
                printf("Please input integer as item.\n");
                scanf("%d", &item);
                addDMatrixItem(a, item);
            }
            while (true)
            {
                printf("**********************************************************\n");
                printf("1.Get item with index\n");
                printf("2.Show the matrix\n");
                printf("0.Exit\n");
                printf("Please input your choice.\n");
                printf("**********************************************************\n");
                scanf("%d", &choice);
                if (choice == 0)    break;                    
                if (choice == 1)
                {
                    int row, col;
                    printf("Please input row number.\n");
                    scanf("%d", &row);
                    printf("Please input col number.\n");
                    scanf("%d", &col);
                    printf("The item is %d\n", getDMatrixItem(a, row, col));
                }
                else    showDMatrix(a);
            }
            break;
        }
        }
    }
    return 0;
}