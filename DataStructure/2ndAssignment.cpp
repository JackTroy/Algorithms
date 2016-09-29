#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    float coef;
    int expn;
} Item;
#include "list.cpp"

List InitPoly()
{
    int m, i;
    Item item;
    List L = InitList();
    printf("Input the length of the polynomial:\n");
    scanf("%d", &m);
    for (i = 0; i < m; i++)
    {
        printf("No.%d term:\n", i + 1);
        printf("Input coefficient:\n");
        scanf("%f", &item.coef);
        printf("Input exponent:\n");
        scanf("%d", &item.expn);
        addItem(L, item);
    }
    return L;
}

List sum(List L, List L2)
{
    List sum = InitList();
    Node p = L->head, p2 = L2->head;
    Item item;
    while (p && p2)
    {
        if (p->item.expn == p2->item.expn)
        {
            item.expn = p->item.expn;
            item.coef = p->item.coef + p2->item.coef;
            addItem(sum, item);
            p = p->next;
            p2 = p2->next;
        }
        else if (p->item.expn < p2->item.expn)
        {
            item.expn = p->item.expn;
            item.coef = p->item.coef;
            addItem(sum, item);
            p = p->next;
        }
        else
        {
            item.expn = p2->item.expn;
            item.coef = p2->item.coef;
            addItem(sum, item);
            p2 = p2->next;
        }
    }
    while (p)
    {
        item.expn = p->item.expn;
        item.coef = p->item.coef;
        addItem(sum, item);
        p = p->next;
    }
    while (p2)
    {
        item.expn = p2->item.expn;
        item.coef = p2->item.coef;
        addItem(sum, item);
        p2 = p2->next;
    }
    return sum;
}
void showPoly(List L)
{
    Node p = L->head;
    int i;
    for (i = 0; i < L->len; i++)
    {
        printf("(%.4fx^%d)", p->item.coef, p->item.expn);
        if (i != L->len - 1)
        {
            printf("+");
        }
        p = p->next;
    }
    printf("\n");
}
int main()
{
    List poly_1 = NULL, poly_2 = NULL;
    int choice;
    while (true)
    {

        if (!poly_1 && !poly_2)
        {
            printf("Initialize the 1st polynomial.\n");
            poly_1 = InitPoly();
            printf("Initialize the 2nd polynomial.\n");
            poly_2 = InitPoly();
        }
        printf("1.Show 2 polynomials.\n");
        printf("2.Show the sum of 2 polynomials.\n");
        printf("3.Reinitialize polynomials.\n");
        scanf("%d", &choice);
        switch (choice)
        {
        case 1:
        {
            printf("Polynomial 1:");
            showPoly(poly_1);
            printf("Polynomial 2:");
            showPoly(poly_2);
            break;
        }
        case 2:
        {
            showPoly(sum(poly_1, poly_2));
            break;
        }
        case 3:
        {
            cleanList(poly_1);
            cleanList(poly_2);
            free(poly_1);
            free(poly_2);
            poly_1 = NULL;
            poly_2 = NULL;
            break;
        }
        }
    }
}