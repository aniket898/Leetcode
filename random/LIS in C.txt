#include<iostream>
#include<cstdio>
using namespace std;
int lis( int arr[], int n );

int main()
{
    int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
  	int n = sizeof(arr)/sizeof(arr[0]);
    cout<<"\nLength of LIS is :"<< lis( arr, n ) ;
	return 0;
}

int lis( int arr[], int n )
{
   int *lis, i, j, max = 0;
   lis = (int*) malloc ( sizeof( int ) * n );
 
   /* Initialize LIS values for all indexes */
   for ( i = 0; i < n; i++ )
      lis[i] = 1;
     10, 22, 9, 33, 21, 50, 41, 60
   /* Compute optimized LIS values in bottom up manner */
   for ( i = 1; i < n; i++ )
      for ( j = 0; j < i; j++ )
         if ( arr[i] > arr[j] && lis[i] < lis[j] + 1){
            lis[i] = lis[j] + 1;
            cout<<"\n1)) i:"<<i<<" ,j:"<<j<<" ,arr[i]:"<<arr[i]<<" ,arr[j]:"<<arr[j]<<" ,lis[i]:"<<lis[i]<<" ,lis[j]:"<<lis[j];
         } else {
         	cout<<"\n2)) i:"<<i<<" ,j:"<<j<<" ,arr[i]:"<<arr[i]<<" ,arr[j]:"<<arr[j]<<" ,lis[i]:"<<lis[i]<<" ,lis[j]:"<<lis[j];
         }
   /* Pick maximum of all LIS values */
   for ( i = 0; i < n; i++ )
      if ( max < lis[i] )
         max = lis[i];
 
   /* Free memory to avoid memory leak */
   free( lis );
 
   return max;
}