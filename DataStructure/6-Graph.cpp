/*
使用十字链表实现有向图
使用BFS和DFS遍历有向图
*/
#include <stdio.h>
#include <stdlib.h>
typedef int Item;
typedef int VertexType;
//队列数据结构及函数
typedef struct ListNode
{
    Item item;
    struct ListNode *next;
} * Node;

typedef struct Linklist
{
    Node head, tail;
    int len;
} * Queue;

Queue InitQueue() //Initalize Queue and return pointer
{
    Queue q = (Queue)malloc(sizeof(Linklist));
    q->len = 0;
    q->head = NULL;
    q->tail = NULL;
    return q;
}
bool isEmpty(Queue q) //return 0 if the Queue is empty ,1 if not empty
{
    return q->head == NULL;
}
void enqueue(Queue q, Item item) //Add item into the Queue
{
    Node oldTail = q->tail;
    q->tail = (Node)malloc(sizeof(ListNode));
    q->tail->next = NULL;
    q->tail->item = item;
    if (isEmpty(q))
        q->head = q->tail;
    else
        oldTail->next = q->tail;
    ++q->len;
}
Item dequeue(Queue q)
{
    if (isEmpty(q))
        return -1;
    Item item = q->head->item;
    q->head = q->head->next;
    q->len--;
    if (isEmpty(q))
        q->tail = NULL;
    return item;
}
//有向图数据结构及函数
#define MAX_VERTEX_NUM 20
typedef struct Edge
{
    int tailvex, headvex;
    struct Edge *hlink, *tlink;
} Edge;
typedef struct Vertex
{
    VertexType data;
    Edge *firstin, *firstout;
} Vertex;
typedef struct
{
    Vertex xlist[MAX_VERTEX_NUM];
    int V, E; //顶点数目和有向边的数目
} DGraph;
void CreateDGraph(DGraph &G)
{
    //创建有向图
    int V, E;
    printf("Please input the num of vertices.\n");
    scanf("%d", &V);
    printf("Please input the num of edges.\n");
    scanf("%d", &E);
    G.V = V;
    G.E = E;
    int i;
    for (i = 0; i < V; i++)
    {
        G.xlist[i].data = i;
        G.xlist[i].firstin = NULL;
        G.xlist[i].firstout = NULL;
    }

    for (i = 0; i < E; i++)
    {
        printf("Edge %d,please input two vertices.\n", i + 1);
        int v, w;
        printf("Please input tail vertex.\n");
        scanf("%d", &v);
        printf("Please input head vertex.\n");
        scanf("%d", &w);
        //e:v->w
        Edge *e = (Edge *)malloc(sizeof(Edge));
        e->tailvex = v;
        e->headvex = w;

        e->hlink = G.xlist[w].firstin;
        e->tlink = G.xlist[v].firstout;
        G.xlist[w].firstin = e;
        G.xlist[v].firstout = e;
    }
}
void dfs(DGraph &G, int v, bool marked[])
{
    //递归深度优先搜索
    marked[v] = true;
    Vertex vertex = G.xlist[v];
    printf("%d ", vertex.data);
    for (Edge *e = vertex.firstout; e != NULL; e = e->tlink)
    {
        int w = e->headvex;
        if (!marked[w])
            dfs(G, w, marked);
    }
}
void DepthFirstSearch(DGraph &G)
{
    bool marked[G.V];
    dfs(G, 0, marked);
}
void BreathFirstSearch(DGraph &G)
{
    //用队列广度优先搜索
    Queue q = InitQueue();
    bool marked[G.V];
    enqueue(q, 0);
    while (!isEmpty(q))
    {
        int v = dequeue(q);
        marked[v] = true;
        Vertex vertex = G.xlist[v];
        printf("%d ", vertex.data);
        for (Edge *e = vertex.firstout; e != NULL; e = e->tlink)
        {
            int w = e->headvex;
            if (!marked[w])
                enqueue(q, w);
        }
    }
}
int main()
{
    DGraph G;
    CreateDGraph(G);
    printf("Depth first search on directed graph.\n");
    DepthFirstSearch(G);
    printf("\n");
    printf("Breath first search on directed graph.\n");
    BreathFirstSearch(G);
    printf("\n");
    system("pause");
    return 0;
}