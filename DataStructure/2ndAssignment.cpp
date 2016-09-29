#include <stdio.h>
#include <stdlib.h>
#include <math.h>

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

void Derivatives(List L)
{
    int i;
    Node p = L->head;
    for (i = 0; i < L->len; i++)
    {
        if (p->item.expn > 1)
            printf("(%.3fx^%d)", p->item.coef * p->item.expn, p->item.expn - 1);
        else if (p->item.expn == 1)
            printf("(%.3f)", p->item.coef);

        if (i != L->len - 1)
        {
            printf("+");
        }
        p = p->next;
    }
    printf("\n");
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
        if (p->item.expn > 0)
        {
            printf("(%.3fx^%d)", p->item.coef, p->item.expn);
        }
        else
        {
            printf("(%.3f)", p->item.coef);
        }
        if (i != L->len - 1)
        {
            printf("+");
        }
        p = p->next;
    }
    printf("\n");
}

float value(List L, float x)
{
    float value=0;
    Node p = L->head;
    while (p)
    {
        printf("%f",value);
        value += p->item.coef * pow(x, p->item.expn);
        p = p->next;
    }
    return value;
}

void insert(List L, Item item)
{
}

List product(List poly1, List poly2)
{
    List product = InitList();
    Node p1 = poly1->head, p2 = poly2->head;
    Item item;
    while (p1)
    {
        while (p2)
        {
            item.expn = p1->item.expn + p2->item.expn;
            item.coef = p1->item.coef * p2->item.coef;
            insert(product, item);
        }
    }
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
        printf("3.Compute the value of polynomials with input x.\n");
        printf("4.Show the derivates of polynomials.\n");
        //printf("5.Show the product of 2 polynomials\n");
        printf("6.Reinitialize polynomials.\n");
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
            float x;
            printf("Input x:");
            scanf("%f", &x);
            printf("1st polynomial's value:%f\n", value(poly_1, x));
            printf("2nd polynomial's value:%f\n", value(poly_2, x));
            break;
        }
        case 4:
        {
            printf("1st polynomial's derivates:");
            Derivatives(poly_1);
            printf("2nd polynomial's derivates:");
            Derivatives(poly_2);
        }
        /*case 5:{
        	printf("Product:");showPoly(product(poly_1,poly_2));
        }*/
        case 6:
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
    return 0;
}