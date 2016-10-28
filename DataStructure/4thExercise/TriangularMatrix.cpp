#define MAXSIZETM 55
typedef struct TriangularMatrix
{
    Item items[MAXSIZETM];
    int size,count,c;
}*TMatrix;
TMatrix InitTMatrix(int size,int c){
    TMatrix a = (TMatrix)malloc(sizeof(TriangularMatrix));
    a->size=size;
    a->count=0;
    a->c=c;
    return a;
}
void addTMatrixItem(TMatrix a,Item item){
    a->items[a->count++]=item;
}
Item getTMatrixItem(TMatrix a,int row,int col){
    if(row<col) return a->c;
    int pos = (1+row)*row/2+col;
    return a->items[pos];
}
void showTMatrix(TMatrix a){
    int i;
    for(i=0;i<a->count;i++){
        printf("Index %d:%d\n",i,a->items[i]);
    }
}