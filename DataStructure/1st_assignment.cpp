#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    int data;
    struct ListNode *next;
} ListNode, *Node;

typedef struct
{
    Node head, tail;
    int len;
} ListHead, *List;

typedef struct
{
    int coef, expn;
    struct ListNode *next;
} Poly, *PloyNode;

typedef struct
{
    PloyNode head;
    int len;
} PolyHead, *PolyList;

List InitList(int size)
{
    List L = (List)malloc(sizeof(ListHead));
    int i;
    Node node, newNode;
    node = (Node)malloc(sizeof(ListNode));
    L->head = node;
    node->data = 0;
    for (i = 1; i < size; i++)
    {
        newNode = (Node)malloc(sizeof(ListNode));
        newNode->data = i;
        node->next = newNode;
        node = newNode;
    }
    node->next = NULL;
    L->tail = node;
    L->len = size;

    return L;
}

int isEmpty(List L)
{
    return L->head == NULL ? 1 : 0;
}

int len(List L)
{
    return L->len;
}

int GetElem(List L, int order)
{
    Node p = L->head;
    while (order-- != 1)
    {
        p = p->next;
    }
    return p->data;
}

int Find(List L, int e)
{
    Node p = L->head;
    int i, existence = 0;
    for (i = 0; i < L->len; i++)
    {
        if (p->data == e)
        {
            existence = 1;
        }
        p = p->next;
    }
    return existence;
}

void InsertBefore(List L, int order, int data)
{
    int i = 1;
    Node p = L->head;
    Node e = (Node)malloc(sizeof(ListNode));
    e->data = data;
    if (order == 1)
    {
        e->next = p;
        L->head = e;
    }
    else
    {
        while (i++ != order - 1)
        {
            p = p->next;
        }
        e->next = p->next;
        p->next = e;
    }
    L->len += 1;
}

void Delete(List L, int order)
{
    Node p = L->head;
    Node deleted_p;
    int i = 1;
    if (order == 1)
    {
        deleted_p = p;
        p = p->next;
        L->head = p;
    }
    else
    {
        while (i++ != order - 1)
        {
            p = p->next;
        }
        if (order == L->len)
        {
            deleted_p = p->next;
            p->next = NULL;
            L->tail = p;
        }
        else
        {
            deleted_p = p->next;
            p->next = p->next->next;
        }
    }
    free(deleted_p);
    L->len -= 1;
}

void cleanList(List L)
{
    Node old_p = L->head, p;
    while (old_p)
    {
        p = old_p->next;
        free(old_p);
        old_p = p;
    }
}

PolyList InitPoly(int m)
{
    int i,coef,expn;
    PolyList L = (PolyList)malloc(sizeof(PolyHead));
    PolyNode p = (PloyNode)malloc(sizeof(Poly)),newp;
    L->head = p;
    for (i = 0;i<m,i++)
    {
        printf("Input coefficent:");
        scanf("%d",&coef);
        printf("Input exponent:");
        scanf("%d",&expn);
        p->coef = coef;
        p->expn = expn;
        newp = (PloyNode)malloc(sizeof(Poly));
        p->next = newp;
        p = newp;
    }
}
void showPoly(PolyList L)
{
    PloyNode p = L->head;
    int i;
    for (i = 0; i < L->len; i++)
    {
        printf("%dx^%d", p->coef, p->expn);
        if (i != L->len - 1)
        {
            printf("+");
        }
        p = p->next;
    }
}

PolyList sumofPoly(PolyList L,L2){

}

int main()
{

    int choice;
    printf("1.1st programming assignment\n");
    printf("2.2nd programming assignment\n");
    printf("Please input your choice by the order.\n");
    printf(" the sparse polynomial in one variable \n");
    if (getchar() - '0')
    {
        List L = NULL;
        while (true)
        {

            if (L == NULL)
            {
                int size;
                printf("Please input the size of List:");
                scanf("%d", &size);
                if (size < 1)
                    printf("Invalid size.\n");
                else
                    break;
                L = InitList(size);
            }

            printf("1.Is list empty?\n2.Length of list?\n3.Print all element.\n4.Get specific element\n5.Does element e exist?\n6.Insert e before i th element.\n7.Delete i th element.\n8.Destroys list.\n9.Quit.\nPlease input your choice by the order.\n");
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
                Node p = L->head;
                printf("Entire list is as follows.\n");
                int i;
                for (i = 0; i < L->len; i++)
                {
                    printf("%d ", p->data);
                    p = p->next;
                }
                printf("\n\n");
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
                int order, data;
                printf("Please input the order:\n");
                scanf("%d", &order);
                printf("Please input the data:\n");
                scanf("%d", &data);
                InsertBefore(L, order, data);
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
    }
    else
    {
        printf("Input the length of the 1st polynomial:\n");
        PolyList L = InitPoly(getchar() - '0');
        printf("Input the length of the 2nd polynomial:\n");
        PloyList L2 = InitPoly(getchar() - '0');
        while (true)
        {
            printf("1.show the polynomials\n");
            printf("2.show the sum of two polynomials\n");
            scanf("%d\n", &choice);
            if (choice == 1)
            {
                printf("1st polynomial:\n");
                showPoly(L);
                printf("2nd polynomial:\n");
                showPoly(L2);
            }
            else
            {
                showPoly(sumofPoly(L, L2));
            }
        }
    }
    return 0;
}