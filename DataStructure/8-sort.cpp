#include <iostream>
#include <cstdlib>
using namespace std;
void exch(int *a, int i, int j)
{
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
}
void shellSort(int *a, int len)
{
    int h = 1;
    while (h * 3 + 1 < len)
        h = h * 3 + 1;
    while (h >= 1)
    {
        for (int i = h; i < len; i++)
            for (int j = i; j >= h && a[j] < a[j - h]; j -= h)
                exch(a, j, j - h);
        h = (h - 1) / 3;
    }
}
int partition(int *a, int lo,int hi)
{
    int i = lo, j = hi + 1;
    int v = a[lo];
    while (true)
    {
        while (a[++i] < v)
            if (i == hi)
                break;
        while (v < a[--j])
            if (j == lo)
                break;
        if (i >= j)
            break;
        exch(a, i, j);
    }
    exch(a, lo, j);
    return j;
}
void quickSort(int *a, int lo, int hi)
{
    if(hi<=lo)  return;
    int j = partition(a, lo, hi);
    quickSort(a, lo, j - 1);
    quickSort(a, j + 1, hi);
}
void quickSort(int *a, int len)
{
    quickSort(a, 0, len - 1);
}
int main()
{
    while(true){
        int len = 0;
        cout<<"Please input length of random array,or input 0 to quit.\n";
        cin>>len;
        if(len<0){
            cout<<"Invalid length.\n";
            continue;
        }
        else if(len==0) break;
        int a[len];
        cout<<"randam array:";
        for(int i=0;i<len;i++){
            a[i]=rand();
            cout<<a[i]<<' ';
        }
        cout<<'\n';
        cout<<"input 1 to use shell sort ,2 to use quick sort.\n";
        int choice;
        cin>>choice;
        if(choice==1)       shellSort(a,len);
        else if(choice==2)  quickSort(a,len);
        for(int i=0;i<len;i++)
            cout<<a[i]<<' ';
        cout<<'\n';
    }
    return 0;
}