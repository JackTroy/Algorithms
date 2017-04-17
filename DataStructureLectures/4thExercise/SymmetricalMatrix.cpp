#define MAXSIZESM 55
typedef struct SymmetricalMatrix
{
    Item items[MAXSIZESM];
    int size, count;
} * SMatrix;
//初始化矩阵
SMatrix InitSMatrix(int size)
{
    SMatrix a = (SMatrix)malloc(sizeof(SymmetricalMatrix));
    a->size = size;
    a->count = 0;
    return a;
}
//往数组添加数据
void addSMatrixItem(SMatrix a, Item item)
{
    a->items[a->count++] = item;
}
//根据索引取值
Item getSMatrixItem(SMatrix a, int row, int col)
{
    if (row < col)  {int temp = col;col = row;row = temp;}
    int pos = (1 + row) * row / 2 + col;
    return a->items[pos];
}
//打印矩阵
void showSMatrix(SMatrix a)
{
    int i, j;
    for (i = 0; i < a->size; i++)
    {
        for (j = 0; j < a->size; j++)
            printf("%d ", getSMatrixItem(a, i, j));
        printf("\n");
    }
}