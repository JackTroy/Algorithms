#include <stdio.h>
#include <stdlib.h>
//符号表的最大长度
#define MAXLEN 100
//符号表元素的键和值
typedef int Key;
typedef int Value;
struct Node
{
    Key key;
    Value val;
};
//符号表，用数组实现
typedef struct SymbolTable
{
    int size;
    Node nodes[MAXLEN];
} * ST;
//初始化符号表
ST initST(int size)
{
    ST st = (ST)malloc(sizeof(SymbolTable));
    st->size = size;
    for (int i = 0; i < size; i++)
    {
        printf("%d node\n", i + 1);
        printf("Please input key\n");
        scanf("%d", &st->nodes[i].key);
        printf("Please input value\n");
        scanf("%d", &st->nodes[i].val);
    }
    return st;
}
//对符号表进行顺序查找
int sequentialSearch(ST st, Key key)
{
    int i;
    for (i = 0; i < st->size; i++)
        if (st->nodes[i].key == key)
            return i;
    return -1;
}
//对符号表进行二分查找
int binarySearch(ST st, Key key)
{
    //插入排序
    for (int i = 1; i < st->size; i++)
        for (int j = i; j > 0 && st->nodes[j - 1].key > st->nodes[j].key; j--)
        {
            Node temp = st->nodes[j];
            st->nodes[j] = st->nodes[i];
            st->nodes[i] = st->nodes[j];
        }
    //二分查找
    int lo = 0, hi = st->size - 1, mid = (lo + hi) / 2;
    while (lo <= hi)
    {
        if (st->nodes[mid].key == key)
            return mid;
        if (st->nodes[mid].key < key)
        {
            lo = mid + 1;
            mid = (lo + hi) / 2;
        }
        if (st->nodes[mid].key > key)
        {
            hi = mid - 1;
            mid = (lo + hi) / 2;
        }
    }
    return -1;
}
int main()
{
    int size;
    printf("Initialize symbol table,please input size (smaller than 100).\n");
    scanf("%d", &size);
    ST st = initST(size);
    Key key;
    int choice;
    while (true)
    {
        int index;
        printf("Begin search, please input key\n");
        scanf("%d", &key);
        printf("Input 1 to use sequential search,2 to use binary search.\n");
        scanf("%d", &choice);
        if (choice == 1)
        {
            index = sequentialSearch(st, key);
            if (index != -1)
                printf("Found key %d ,value:%d .\n", st->nodes[index].key, st->nodes[index].val);
            else
                printf("Not found.\n");
        }
        else
        {
            index = binarySearch(st, key);
            if (index != -1)
                printf("Found key %d ,value:%d .\n", st->nodes[index].key, st->nodes[index].val);
            else
                printf("Not found.\n");
        }
        printf("Input 0 to quit ,1 to continue search.\n");
        scanf("%d", &choice);
        if (choice == 0)
            break;
    }
}