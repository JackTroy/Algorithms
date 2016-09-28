
typedef struct ListNode
{
    Item item;
    struct ListNode *next;
}*Node;

typedef struct Linklist
{
    Node head, tail;
    int len;
}*List;

List InitList()
{
    List L = (List)malloc(sizeof(Linklist));
    L->len = 0;
    return L;
}

void addItem(List L, Item item)
{
    Node node;
    node = (Node)malloc(sizeof(ListNode));
    node->next = NULL;
    node->item = item;
    if (L->len == 0)
    {
        L->head = node;
        L->tail = node;
    }
    else
    {
        L->tail->next = node;
        L->tail = node;
    }
    ++L->len;
}

int isEmpty(List L)
{
    return L->head == NULL ? 1 : 0;
}

int len(List L)
{
    return L->len;
}

Item GetElem(List L, int order)
{
    Node p = L->head;
    while (order-- != 1)
    {
        p = p->next;
    }
    return p->item;
}

void InsertAt(List L, int order, Item item)
{
    int i = 1;
    Node p = L->head;
    Node e = (Node)malloc(sizeof(ListNode));
    e->item = item;
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
    int i;
    Node old_p = L->head, p;
    //for(i=0;i<L->len;i++)
    while(old_p)
    {
        p = old_p->next;
        free(old_p);
        old_p = p;
    }
}

void InsertBefore(List L, int order, Item item)
{
    int i = 1;
    Node p = L->head;
    Node e = (Node)malloc(sizeof(ListNode));
    e->item = item;
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
