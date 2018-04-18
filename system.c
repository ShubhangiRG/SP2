#include <stdio.h>
#include <unistd.h>
int glob=6;

int main()
{
int ret_val,ret_code;
pid_t pid;
int var;
int argc;
char **argv;
int n=0;

do{
printf("Enter choice:1.fork\n2.Fork with multiple\n3.vfork\n4.wait\n5.execl\n6.execv");
scanf("%d",&n);
switch (n)
{
    case 1: // code to be executed if n = 1;
{
pid_t pid;
var=88;
printf("before fork\n");
if((pid=fork()) <0)
{
printf("fork error \n");
}
else if(pid==0)
{
glob++;
var++;
}
else 
{
sleep(2);
}
printf("pid=%d,glob=%d,var=%d\n",getpid(),glob,var);
} 
       break;

    case 2: // code to be executed if n = 2;
{
fork();fork();fork();
printf("Hello World!");
} 
       break;

case 3: // code to be executed if n = 3;
{
var=88;
printf("before fork\n");
if((pid=vfork()) <0)
{
printf("vfork error \n");
}
else if(pid==0)
{
glob++;
var++;
exit(0);
}

printf("pid=%d,glob=%d,var=%d\n",getpid(),glob,var);
}
        break;

case 4: // code to be executed if n = 4;
{
if((pid=fork())<0)
{
printf("child process %x\n",getpid());
exit(10);
}
ret_val=wait(&ret_code);
printf("wait ret_val %x ret_code c%x\n",ret_val,ret_code);
} 
       break;

case 5: // code to be executed if n = 5;
{
char *args[]={"cal","03","2018",NULL};
execv("/usr/bin/cal",args);
printf("Reached here\n");
}
        break;

case 6: // code to be executed if n = 6;
{
execl("/usr/bin/cal","cal","03","2018",(char*)0);
printf("Reached here\n");
        break;
}
    default: // code to be executed if n doesn't match any cases
printf("EXIT");
break;
}
//printf("Do you want to continue?");
//scanf("%d",&n);
}while(n<=7);
 return 0;
}
/*
[ccoew@localhost Downloads]$ gcc system.c
system.c: In function ‘main’:
system.c:60:1: warning: incompatible implicit declaration of built-in function ‘exit’ [enabled by default]
 exit(0);
 ^
system.c:72:1: warning: incompatible implicit declaration of built-in function ‘exit’ [enabled by default]
 exit(10);
 ^
[ccoew@localhost Downloads]$ ./a.out
Enter choice:1.fork
2.Fork with multiple
3.vfork
4.wait
5.execl
6.execv1
before fork
pid=6651,glob=7,var=89
Enter choice:1.fork
2.Fork with multiple
3.vfork
4.wait
5.execl
6.execvpid=6650,glob=6,var=88
Enter choice:1.fork
2.Fork with multiple
3.vfork
4.wait
5.execl
6.execv2
Hello World!Enter choice:1.fork
2.Fork with multiple
3.vfork
4.wait
5.execl
6.execvHello World!Enter choice:1.fork
2.Fork with multiple
3.vfork
4.wait
5.execl
Hello World!Enter choice:1.fork
2.Fork with multiple
3.vfork
4.wait
5.execl
6.execv6.execvHello World!Enter choice:1.fork
2.Fork with multiple
3.vfork
4.wait
5.execl
Hello World!Enter choice:1.fork
2.Fork with multiple
3.vfork
4.wait
5.execl
6.execv6.execvHello World!Enter choice:1.fork
2.Fork with multiple
3.vfork
4.wait
5.execl
Hello World!Enter choice:1.fork
2.Fork with multiple
3.vfork
4.wait
5.execl
6.execv6.execvHello World!Enter choice:1.fork
2.Fork with multiple
3.vfork
4.wait
5.execl
6.execv3
before fork
pid=6651,glob=8,var=89
Enter choice:1.fork
2.Fork with multiple
3.vfork
4.wait
5.execl
6.execv4
wait ret_val ffffffff ret_code c0
Enter choice:1.fork
2.Fork with multiple
3.vfork
4.wait
5.execl
6.execv5
     March 2018     
Su Mo Tu We Th Fr Sa
             1  2  3 
 4  5  6  7  8  9 10 
11 12 13 14 15 16 17 
18 19 20 21 22 23 24 
25 26 27 28 29 30 31 
                     
6
     March 2018     
Su Mo Tu We Th Fr Sa
             1  2  3 
 4  5  6  7  8  9 10 
11 12 13 14 15 16 17 
18 19 20 21 22 23 24 
25 26 27 28 29 30 31 
*/
