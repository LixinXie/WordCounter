#include<stdio.h>
int main()
{
	int a,n,i,j;int array[100];array[0]=0;int Sn=0;//定义各个变量，并赋array[0]的值为0
	scanf("%d,%d",&a,&n);//从键盘输入a和n的值
	if(n)//当n不等于0时执行以下语句
	for(i=1;i<=n;i++)//使用for循环为数组中array[1]开始的各元素赋值
	{
		array[i]=10*array[i-1]+a;
	}
	for(j=1;j<=n;j++)//使用for循环累加数组中元素的值
	Sn+=array[j];
	printf("%d",Sn);//输出Sn的值
	getchar();
	getchar();
	return 0;
}//这是注释