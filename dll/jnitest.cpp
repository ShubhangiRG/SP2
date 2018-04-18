#include<stdio.h>
#include<jni.h>
#include "JNITest.h"

JNIEXPORT void JNICALL Java_JNITest_greet

   (JNIEnv *env, jobject obj)
{
	int op1=3;
	int op2=2;
	int ch;
	int ans=0;
	int res=0;
do
{
	printf("Following operations:\n1.Add\n2.Subtract\n3.Multiply\n4.Divide\n");
	printf("Enter the type of operation you want to perform");
	scanf("%d",&ch);
	switch(ch)
{
case 1:
res=op1+op2;
break;

case 2:
res=op1-op2;
break;

case 3:
res=op1*op2;
break;

case 4:
res=op1/op2;
break;
}
 printf("\nResult is %d\n",res);
printf("Do you want to continue?[1/0]\n");
scanf("%d",&ans);
	}while(ans==1);
     
      return ;
}
