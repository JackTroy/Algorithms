#include <stdio.h>
#include <stdlib.h>
typedef char Item;

typedef struct BinaryTreeNode
{
    Item item;
    struct BinaryTreeNode *left, *right;
} BinaryTreeNode, *BTNode;

typedef struct ListNode
{
    BinaryTreeNode *BTNode;
    struct ListNode *next;
} ListNode, *Node;
typedef struct ListStack
{
    ListNode *top;
} ListStack, *Stack;
typedef struct LinkQueue
{
    ListNode *first, *last;
} LinkQueue, *Queue;
Stack InitStack()
{
    Stack s = (Stack)malloc(sizeof(Stack));
    s->top = NULL;
    return s;
}
void addStack(Stack s, BTNode x)
{
    Node node = (Node)malloc(sizeof(Node));
    node->BTNode = x;
    node->next = NULL;
    if (s->top == NULL)
        s->top = node;
    else
    {
        node->next = s->top;
        s->top = node;
    }
}
BTNode popStack(Stack s)
{
    BTNode x = s->top->BTNode;
    Node node = s->top;
    s->top = node->next;
    free(node);
    return x;
}
Queue InitQueue()
{
    Queue s = (Queue)malloc(sizeof(Queue));
    s->first = NULL;
    s->last = NULL;
    return s;
}
void enqueue(Queue s, BTNode x)
{
    Node node = (Node)malloc(sizeof(Node));
    node->BTNode = x;
    if (!s->first)
    {
        s->first = node;
        s->last = node;
    }
    else
    {
        s->last->next = node;
        s->last = node;
    }
}
BTNode dequeue(Queue s)
{
    BTNode x;
    Node node;
    if (s->first == s->last)
    {
        x = s->first->BTNode;
        free(s->first);
        s->first = NULL;
        s->last = NULL;
    }
    else
    {
        x = s->first->BTNode;
        node = s->first;
        s->first = s->first->next;
        free(node);
    }
    return x;
}
void createBT(BTNode &x)
{
    char c;
    scanf("%c", &c);
    fflush(stdin);
    if (c == ' ')
        x = NULL;
    else
    {
        if (!(x = (BTNode)malloc(sizeof(BTNode))))
            exit(1);
        x->item = c;
        createBT(x->left);
        createBT(x->right);
    }
}
void PreOrderTraverse(BTNode x)
{
    if (!x)
        return;
    printf("%c", x->item);
    PreOrderTraverse(x->left);
    PreOrderTraverse(x->right);
}

void PreOrderTraverse_stack(BTNode bt)
{
    Stack s;
    s = InitStack();
    addStack(s, bt);
    while (s->top->BTNode != NULL)
    {
        BTNode temp = popStack(s);
        printf("%c", temp->item);
        if (temp->right)
            addStack(s, temp->right);
        if (temp->left)
            addStack(s, temp->left);
    }
}
void InOrderTraverse(BTNode x)
{
    if (x == NULL)
        return; //exit(1);
    else
    {
        InOrderTraverse(x->left);
        printf("%c", x->item);
        InOrderTraverse(x->right);
    }
}
void PostOrderTraverse(BTNode x)
{
    if (x == NULL)
        return;
    else
    {
        InOrderTraverse(x->left);
        InOrderTraverse(x->right);
        printf("%c", x->item);
    }
}
void LevelOrderTraverse(BTNode x)
{
    Queue q;
    q = InitQueue();
    enqueue(q, x);
    while (q->first != NULL)
    {
        BTNode temp = dequeue(q);
        printf("%c", temp->item);
        if (temp->left)
            enqueue(q, temp->left);
        if (temp->right)
            enqueue(q, temp->right);
    }
}
int main()
{
    BTNode bt;
    printf("**********************************\n");
    printf("Please input charaters as items of binary tree,end with '0'.\n");
    printf("**********************************\n");
    createBT(bt);
    int choice;
    while (true)
    {
        printf("**********************************\n");
        printf("1.Pre Order Traverse BT - recursive version\n");
        printf("2.Pre Order Traverse BT - stack version\n");
        printf("3.In Order Traverse BT - recursive version\n");
        printf("4.Post Order Traverse BT - recursive version\n");
        printf("5.Level Order Traverse BT - queue version\n");
        printf("0.Exit\n");
        printf("Please input your choice.\n");
        printf("**********************************\n");
        scanf("%d", &choice);
        if (choice == 0)
            break;
        switch (choice)
        {
        case 1:
        {
            PreOrderTraverse(bt);
            printf("\n");
            break;
        }
        case 2:
        {
            PreOrderTraverse_stack(bt);
            printf("\n");
            break;
        }
        case 3:
        {
            InOrderTraverse(bt);
            printf("\n");
            break;
        }
        case 4:
        {
            PostOrderTraverse(bt);
            printf("\n");
            break;
        }
        case 5:
        {
            LevelOrderTraverse(bt);
            printf("\n");
            break;
        }
        }
    }
    return 0;
}