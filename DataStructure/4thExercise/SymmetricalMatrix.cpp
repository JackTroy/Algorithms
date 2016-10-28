#define MAXSIZESM 55
typedef struct SymmetricalMatrix
{
    Item items[MAXSIZESM];
    int size,count;
}*SMatrix;
SMatrix InitSMatrix(int size){
    SMatrix a = (SMatrix)malloc(sizeof(SymmetricalMatrix));
    a->size=size;
    a->count=0;
    return a;
}
void addSMatrixItem(SMatrix a,Item item){
    a->items[a->count++]=item;
}
Item getSMatrixItem(SMatrix a,int row,int col){
    if(row<col) {int temp=col;col=row;row=temp;}
    int pos = (1+row)*row/2+col;
    return a->items[pos];
}
void showSMatrix(SMatrix a){
    int i;
    for(i=0;i<a->count;i++){
        printf("Index %d:%d\n",i,a->items[i]);
    }
}