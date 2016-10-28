#define MAXSIZEDM 100
typedef struct DiagonalMatrix
{
    Item items[MAXSIZEDM];
    int size,count,width;
}*DMatrix;
DMatrix InitDMatrix(int size,int width){
    DMatrix a = (DMatrix)malloc(sizeof(DiagonalMatrix));
    a->size=size;
    a->count=0;
    a->width=width;
    return a;
}
void addDMatrixItem(DMatrix a,Item item){
    a->items[a->count++]=item;
}
Item getDMatrixItem(DMatrix a,int row,int col){
    int width=a->width,hw=(a->width-1)/2,i,pos=0;
    if(row>col+hw||col>row+hw) return 0;
    if(row<hw){
        for(i=0;i<row;i++){
            pos+=hw+1+i;
        }
        pos+=col;
    }
    else if(row<=a->size-hw){
        pos=(hw+1+width-1)*hw/2+(row-hw)*width+col-row+hw;
    }
    else{
        pos=a->count;
        for(i=0;a->size-row>i;i++){
            pos-=hw+1+i;
        }
        pos-=row-col+hw;
    }
    return a->items[pos];
}
void showDMatrix(DMatrix a){
    int i;
    for(i=0;i<a->count;i++){
        printf("Index %d:%d\n",i,a->items[i]);
    }
}