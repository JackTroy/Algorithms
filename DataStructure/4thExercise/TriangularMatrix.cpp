#define MAXSIZETM 55
typedef struct TriangularMatrix
{
    Item items[MAXSIZETM];
    int size, count, c;
} * TMatrix;
//初始化矩阵
TMatrix InitTMatrix(int size, int c)
{
    TMatrix a = (TMatrix)malloc(sizeof(TriangularMatrix));
    a->size = size;
    a->count = 0;
    a->c = c;
    return a;
}
//往数组添加数据
void addTMatrixItem(TMatrix a, Item item)
{
    a->items[a->count++] = item;
}
//根据索引取值
Item getTMatrixItem(TMatrix a, int row, int col)
{
    if (row < col)
        return a->c;
    int pos = (1 + row) * row / 2 + col;
    return a->items[pos];
}
//打印矩阵
void showTMatrix(TMatrix a)
{
    int i, j;
    for (i = 0; i < a->size; i++)
    {
        for (j = 0; j < a->size; j++)
            printf("%d ", getTMatrixItem(a, i, j));
        printf("\n");
    }
}