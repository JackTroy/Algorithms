#include <stdio.h>
#include <stdlib.h>

typedef int Item;
#include "list.cpp"

List InitNaturalNumberList(int size)//Initialize List as Natural Number List
{
    List L = InitList();   
    int i;
    for (i = 0; i < size; i++)
    {
        Item item = i+1;
        addItem(L,item);
    }
    return L;
}


int Find(List L, Item e)//Find element with input
{
    Node p = L->head;
    int i;
    for (i = 0; i < L->len; i++)
    {
        if (p->item == e)
        {
            return 1;
        }
        p = p->next;
    }
    return 0;
}

void show(List L)//Print all element in list.
{
    Node p = L->head;
    int i;
    for (i = 0; i < L->len; i++)
    {
        printf("%d ", p->item);
        p = p->next;
    }
    printf("\n");
}

int main()
{
    int choice;
    List L = NULL;
    while (true)
    {

        if (L == NULL)
        {
            int size;
            printf("Please input the size of List:");
            scanf("%d", &size);
            L = InitNaturalNumberList(size);
        }

        printf("1.Is list empty?\n2.Length of list?\n3.Print all element.\n4.Get specific element\n5.Does element e exist?\n6.Insert e before at i th position.\n7.Delete i th element.\n8.Destroys list.\n9.Quit.\nPlease input your choice by the order.\n");
        scanf("%d", &choice);
        switch (choice)
        {
        case 1:
        {
            if (isEmpty(L))
                printf("Yes\n");
            else
                printf("No\n");
            printf("\n");
            break;
        }

        case 2:
            printf("The length is %d.\n\n", len(L));
            break;

        case 3:
        {
            printf("Entire list is as follows.\n");
            show(L);
            break;
        }

        case 4:
        {
            printf("Please input the order of the element:\n");
            int i;
            scanf("%d", &i);
            printf("The element is %d.\n\n", GetElem(L, i));
            break;
        }

        case 5:
        {
            int e;
            printf("Please input the element:\n");
            scanf("%d", &e);
            if (Find(L, e) == 1)
                printf("%d exists.\n", e);
            else
                printf("Not exists\n");
            printf("\n");
            break;
        }

        case 6:
        {
            int order, item;
            printf("Please input the order:\n");
            scanf("%d", &order);
            printf("Please input the item:\n");
            scanf("%d", &item);
            InsertAt(L, order, item);
            printf("Inserts successfully.\n\n");
            break;
        }

        case 7:
        {
            int order;
            printf("Please input the order\n");
            scanf("%d", &order);
            Delete(L, order);
            printf("Deletes successfully.\n\n");
            break;
        }
        case 8:
        {
            cleanList(L);
            free(L);
            L = NULL;
            break;
        }
        case 9:
        {
            cleanList(L);
            free(L);
            break;
        }
        }
        if (choice == 9)
            break;
    }
    return 0;
}