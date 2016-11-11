/*
this piece of code work well when debuging,
but must crash down when running its built application
could be the issue of ram leaking,but i'm tired of it 
*/
#include <stdio.h>
#include <stdlib.h>
//将字符作为二叉树的节点元素类型
typedef char Item;
//二叉树节点数据结构
typedef struct BinaryTreeNode
{
    Item item;
    struct BinaryTreeNode *left, *right;
} BinaryTreeNode, *BTNode;
//链表节点
typedef struct ListNode
{
    BinaryTreeNode *BTNode;
    struct ListNode *next;
} ListNode, *Node;
//栈
typedef struct ListStack
{
    ListNode *top;
} ListStack, *Stack;
//队列
typedef struct LinkQueue
{
    ListNode *first, *last;
} LinkQueue, *Queue;
//初始化栈
Stack InitStack()
{
    Stack s = (Stack)malloc(sizeof(Stack));
    s->top = NULL;
    return s;
}
//入栈
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
//出栈
BTNode popStack(Stack s)
{
    BTNode x = s->top->BTNode;
    Node node = s->top;
    s->top = node->next;
    return x;
}
//创建队列
Queue InitQueue()
{
    Queue s = (Queue)malloc(sizeof(Queue));
    s->first = NULL;
    s->last = NULL;
    return s;
}
//入队
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
//出队
BTNode dequeue(Queue s)
{
    BTNode x;
    Node node;
    if (s->first == s->last)
    {
        x = s->first->BTNode;
        s->first = NULL;
        s->last = NULL;
    }
    else
    {
        x = s->first->BTNode;
        node = s->first;
        s->first = s->first->next;
    }
    return x;
}
//递归先序创建二叉树，空格作为空子树
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
//先序遍历
void PreOrderTraverse(BTNode x)
{
    if (!x)
        return;
    printf("%c", x->item);
    PreOrderTraverse(x->left);
    PreOrderTraverse(x->right);
}
//先序遍历 非递归
void PreOrderTraverse_stack(BTNode bt)
{
    Stack s;
    s = InitStack();
    addStack(s, bt);
    while (s->top != NULL)
    {
        BTNode temp = popStack(s);
        printf("%c", temp->item);
        if (temp->right)
            addStack(s, temp->right);
        if (temp->left)
            addStack(s, temp->left);
    }
}
//中序遍历
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
//后序遍历
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
//层次遍历
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
    //先创建二叉树
    BTNode bt;
    printf("**********************************\n");
    printf("Please input charaters as items of binary tree,end with '0'.\n");
    printf("**********************************\n");
    createBT(bt);
    int choice;
    while (true)
    {
        //选择遍历方法
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