#include <stdio.h>
#include <stdlib.h>

typedef int Item;

#include "list.cpp"
typedef List Stack;//将list重定义为stack

Stack InitStack()//利用list里的函数初始化stack
{
    Stack s = InitList();
    return s;
}

void pushItem(Stack s, Item item)//利用list里的additem往链表尾节点添加item'
{
    addItem(s, item);
}

Item popItem(Stack s)//取list的尾节点，然后删除
{
    Item item = s->tail->item;
    Delete(s, s->len);
    return item;
}

void cleanStack(Stack s)//利用cleanlist将stack清空
{
    cleanList(s);
}

int lenOfStack(Stack s)//返回stack的长度
{
    return s->len;
}

int showAll(Stack s)//利用list.cpp的GetElem从尾到头获取stack的item，然后输出
{
    if(s->len==0) printf("Stack is empty.\n");return 0;
    Item item;
    int i;
    printf("All items are as follows,from the top to the bottom.\n");
    for (i = 0; i < s->len; i++)
    {
        item = GetElem(s,s->len-i);
        printf("%d\n", item);
    }
}

int main()
{
    Stack s = NULL;
    int choice;
    while (true)//利用while循环实现菜单功能
    {
        if (!s)
        {
            s = InitStack();
            int len;
            Item item;
            printf("Initialize stack now ,please input the length of stack.\n");
            scanf("%d", &len);
            while (len-- > 0)
            {
                printf("Please input integer as item to push into the stack.\n");
                scanf("%d", &item);
                pushItem(s, item);
            }
        }
        printf("1.Push item into stack.\n");
        printf("2.Pop item from the top of stack.\n");
        printf("3.Destroy the stack.\n");
        printf("4.The length of stack.\n");
        printf("5.Output all items in the stack.\n");
        printf("6.Exit program.\n");
        printf("Please input your choice by order.\n");
        scanf("%d", &choice);
        switch (choice)
        {
        case 1:
        {
            Item item;
            printf("Please input integer as stack item.\n");
            scanf("%d", &item);
            pushItem(s, item);
            showAll(s);break;
        }
        case 2:
        {
            if(s->len==0){
                printf("Stack is empty.\n");break;
            }
            Item item = popItem(s);
            printf("Pop top item %d.\n", item);
            showAll(s);break;
        }
        case 3:
        {
            if(s->len==0){
                printf("Stack is empty.\n");break;
            }            
            cleanStack(s);
            showAll(s);break;
        }
        case 4:printf("Length of stack is %d.\n", lenOfStack(s));break;
        case 5:showAll(s);break;
        case 6:break;
        }
        if(choice==6) break;
    }
    free(s);
    return 0;
}