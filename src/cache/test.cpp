#include<iostream.h>
template<class T>
class stack
{
int top,size;
T *stackptr;
Public:
stack(int x);
~stack( );
int isempty( );
int isfull ( );
void push(const T&x);
T pop( );
void display( );
};
template<class T>
stack<T>::stack(int x)
{
top = -1;
size = x;
stackptr = new t[size];
}
template<class T>
stack<T>::~stack( )
{
delete[ ]stackptr;
}
template<class T>
}
